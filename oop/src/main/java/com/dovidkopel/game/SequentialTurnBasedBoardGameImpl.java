package com.dovidkopel.game;

import com.dovidkopel.game.board.BoardGame;
import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.game.event.Event;
import com.dovidkopel.game.event.EventBus;
import com.dovidkopel.game.event.SimpleEvent;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.status.Status;
import com.dovidkopel.game.status.TurnStatus;
import com.dovidkopel.game.turn.CompleteTurnImpl;
import com.dovidkopel.game.turn.IncompleteTurn;
import com.dovidkopel.game.turn.IncompleteTurnImpl;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.strategy.WinningStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public abstract class SequentialTurnBasedBoardGameImpl<T extends TicTacToeBoard> implements SequentialTurnBasedBoardGame<T> {
	final private UUID id;

	final private LocalDateTime created;

	private LocalDateTime updated;

	private EvemtBus eventBus;

	private List<? extends Status> statuses = new CopyOnWriteArrayList();

	private List<Turn> turns = new CopyOnWriteArrayList();

	private Turn currentTurn;


	public SequentialTurnBasedBoardGameImpl() {
		this.created = LocalDateTime.now();
		this.id = UUID.randomUUID();
	}

	public SequentialTurnBasedBoardGameImpl(LocalDateTime created, UUID id) {
		this.created = created;
		this.id = id;
	}

	@Autowired
	public SequentialTurnBasedBoardGameImpl<T> setEventBus(EventBus eventBus) {
		this.eventBus = eventBus;
		return this;
	}

	@PostConstruct
	public void init() {



	}

	@Override
	public Turn start() {
//		evaluate(GameStatusE.START_GAME);
		return getCurrentTurn();
	}

	@Override
	public void stop() {


	}

	/****
	 * This is where the major evaluation is performed for the game
	 * this function will be the primary place where events are triggered
	 * @return
	 */

	@Override
	public synchronized List<? extends Status> evaluate(Event event) {
		statuses = eventBus.trigger(event);
		return statuses;
	}

	@Override
	public List<? extends Status> getStatusDetails() {
		return statuses;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public LocalDateTime getUpdated() {
		return updated;
	}

	@Override
	public IncompleteTurn getNextTurn() {
		// Invoke a TurnPreSelection event
		evaluate(
			new SimpleEvent(TurnStatus.TurnStaticEntry.TURN_PRE_SELECTION)
		);

		Player np = getCurrentPlayer();
		IncompleteTurn nt = new IncompleteTurnImpl(np);

		evaluate(
			new SimpleEvent(TurnStatus.TurnStaticEntry.TURN_ACTIVE)
		);

		currentTurn = nt;
		turns.add(nt);
		return nt;
	}

	@Override
	public List<Turn> getTurns() {
		return turns;
	}

	@Override
	public Turn getCurrentTurn() {
		Assert.notNull(currentTurn);
		return currentTurn;
	}

	abstract List<WinningStrategy<BoardGame>> getWinningStrategies();

	@Override
	public Collection<? extends Status> completeTurn(IncompleteTurn turn) {
		Set<Status> statuses = new HashSet();

		if(turns.contains(turn)) {
			// Set Action to turn

			Turn t = new CompleteTurnImpl(turn);

			evaluate(
				new SimpleEvent(TurnStatus.TurnStaticEntry.TURN_EVALUATING)
			);

			// Now we want to evaluate the turn
			// what happened if anything...
			statuses.addAll(
				getWinningStrategies().stream().flatMap(s -> s.evaluateGame(this).stream())
					.collect(Collectors.toSet())
			);

			turns.add(t);
		}
		return statuses;
	}
}
