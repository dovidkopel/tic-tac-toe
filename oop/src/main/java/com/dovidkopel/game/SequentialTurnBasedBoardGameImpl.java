package com.dovidkopel.game;

import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.game.event.Event;
import com.dovidkopel.game.event.EventBus;
import com.dovidkopel.game.status.Status;
import com.dovidkopel.game.turn.Turn;
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

	private LocalDateTime updated;

	private EventBus eventBus;

	private List<? extends Status> statuses = new CopyOnWriteArrayList();


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
}
