package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.board.TwoDimensionalTicTacToeBoard;
import com.dovidkopel.tictactoe.oop.game.status.*;
import com.dovidkopel.tictactoe.oop.game.turn.Action;
import com.dovidkopel.tictactoe.oop.game.turn.Turn;
import com.dovidkopel.tictactoe.oop.game.turn.TurnImpl;
import com.dovidkopel.tictactoe.oop.player.Player;
import com.dovidkopel.tictactoe.oop.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public abstract class SequentialTurnBasedBoardGameImpl<T extends TicTacToeBoard> implements SequentialTurnBasedBoardGame<T> {
	final private UUID id;

	final private LocalDateTime created;

	private PlayerSelector playerSelector;

	private LocalDateTime updated;

	private List<Turn> turns = new CopyOnWriteArrayList();

	private Turn currentTurn;

	private GameStatusEventBus eventBus;

	private List<GameStatusDetails> statuses = new CopyOnWriteArrayList();

	public SequentialTurnBasedBoardGameImpl() {
		this.created = LocalDateTime.now();
		this.id = UUID.randomUUID();
	}

	public SequentialTurnBasedBoardGameImpl(LocalDateTime created, UUID id) {
		this.created = created;
		this.id = id;
	}

	@Autowired
	public SequentialTurnBasedBoardGameImpl setPlayerSelector(PlayerSelector playerSelector) {
		this.playerSelector = playerSelector;
		return this;
	}

	@Autowired
	public SequentialTurnBasedBoardGameImpl<T> setEventBus(GameStatusEventBus eventBus) {
		this.eventBus = eventBus;
		return this;
	}

	@PostConstruct
	public void init() {
	}

	@Override
	public Turn start() {
		evaluate(GameStatus.START_GAME);
		currentTurn = new TurnImpl(playerSelector.getCurrentPlayer());
		turns.add(currentTurn);
		return currentTurn;
	}

	@Override
	public void stop() {
		evaluate(GameStatus.STOP_GAME);
	}

	public List<GameStatusDetails> evaluate(GameStatus gameStatus) {
		return evaluate(new GameEventImpl(gameStatus, this));
	}

	/****
	 * This is where the major evaluation is performed for the game
	 * this function will be the primary place where events are triggered
	 * @return
	 */

	@Override
	public synchronized List<GameStatusDetails> evaluate(GameEvent event) {
		statuses = eventBus.trigger(event);
		return statuses;
	}

	@Override
	public List<GameStatusDetails> getStatusDetails() {
		return statuses;
	}

	@Override
	public Player getCurrentPlayer() {
		return playerSelector.getCurrentPlayer();
	}

	@Override
	public Turn getCurrentTurn() {
		return currentTurn;
	}

	@Override
	public synchronized Collection<GameEvent> completeTurn(Turn turn, Action action) {
		Set<GameEvent> statuses = new HashSet();

		// Set Action to turn
		Turn t = new TurnImpl(turn, action);
		turns.add(t);

		Player np = playerSelector.nextPlayer();
		Turn nt = new TurnImpl(np);
		currentTurn = nt;

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
	public List<Turn> getTurns() {
		return turns.subList(0, turns.size());
	}
}
