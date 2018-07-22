package com.dovidkopel.tictactoe.oop.game.turn;

import com.dovidkopel.tictactoe.oop.player.Player;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TurnImpl<T extends Action> implements Turn<T> {
	final private UUID id;
	final private LocalDateTime created;
	final private Player player;
	private LocalDateTime updated;
	private Optional<T> action = Optional.empty();

	public TurnImpl(Turn turn, T action) {
		this(turn.getId(), turn.getCreated(), turn.getPlayer());
		this.updated = LocalDateTime.now();
		this.action = Optional.of(action);
	}

	public TurnImpl(UUID id, LocalDateTime created, Player player) {
		this.id = id;
		this.created = created;
		this.player = player;
	}

	public TurnImpl(Player player) {
		this(UUID.randomUUID(), LocalDateTime.now(), player);
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
	public Optional<T> getAction() {
		return action;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TurnImpl turn = (TurnImpl) o;
		return Objects.equals(id, turn.id) &&
			Objects.equals(created, turn.created) &&
			Objects.equals(player, turn.player);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, created, player);
	}

	@Override
	public int compareTo(Turn o) {
		return getCreated().compareTo(o.getCreated());
	}
}
