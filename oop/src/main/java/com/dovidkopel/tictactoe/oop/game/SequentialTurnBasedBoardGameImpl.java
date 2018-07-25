package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.game.status.*;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.player.PlayerSelector;
import com.dovidkopel.tictactoe.oop.strategy.WinningStrategy;
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
	public SequentialTurnBasedBoardGameImpl<T> setStrategies(List<WinningStrategy> strategies) {
		this.strategies = strategies;
		return this;
	}

	@Autowired
	public SequentialTurnBasedBoardGameImpl<T> setEventBus(GameStatusEventBus eventBus) {
		this.eventBus = eventBus;
		return this;
	}

	@PostConstruct
	public void init() {
		evaluate(GameStatusE.NOOP);
	}

	@Override
	public Turn start() {
		evaluate(GameStatusE.START_GAME);
		return getCurrentTurn();
	}



	@Override
	public void stop() {
		evaluate(GameStatusE.STOP_GAME);
	}

	public List<GameStatusDetails> evaluate(GameStatusE gameStatusE) {
		return evaluate(new GameEventImpl(gameStatusE, this));
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
