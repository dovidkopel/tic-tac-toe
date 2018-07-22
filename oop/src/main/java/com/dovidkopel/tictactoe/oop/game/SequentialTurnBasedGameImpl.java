package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.game.turn.Action;
import com.dovidkopel.tictactoe.oop.game.turn.Move;
import com.dovidkopel.tictactoe.oop.game.turn.Turn;
import com.dovidkopel.tictactoe.oop.game.turn.TurnImpl;
import com.dovidkopel.tictactoe.oop.player.Player;
import com.dovidkopel.tictactoe.oop.player.PlayerSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SequentialTurnBasedGameImpl implements SequentialTurnBasedGame {
	final private LocalDateTime created;

	final private UUID id;

	private PlayerSelector playerSelector;

	private LocalDateTime updated;

	private List<Turn> turns = new CopyOnWriteArrayList();

	private Turn currentTurn;

	public SequentialTurnBasedGameImpl(LocalDateTime created, UUID id) {
		this.created = created;
		this.id = id;
	}

	public SequentialTurnBasedGameImpl() {
		this.created = LocalDateTime.now();
		this.id = UUID.randomUUID();
	}

	@Autowired
	public SequentialTurnBasedGameImpl setPlayerSelector(PlayerSelector playerSelector) {
		this.playerSelector = playerSelector;
		return this;
	}

	@PostConstruct
	public void init() {
		currentTurn = new TurnImpl(playerSelector.getCurrentPlayer());
		turns.add(currentTurn);
	}

	@Override
	public Collection<GameStatusContainer<?>> getStatuses() {
		return null;
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
	public synchronized void completeTurn(Turn turn, Action action) {
		// Set Action to turn
		Turn t = new TurnImpl(turn, action);
		turns.add(t);

		Player np = playerSelector.nextPlayer();
		Turn nt = new TurnImpl(np);
		currentTurn = nt;
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
