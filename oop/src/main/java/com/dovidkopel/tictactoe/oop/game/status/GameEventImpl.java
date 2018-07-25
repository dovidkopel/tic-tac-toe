package com.dovidkopel.tictactoe.oop.game.status;

import com.dovidkopel.tictactoe.oop.game.Game;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class GameEventImpl<G extends Game, T> extends GameStatusDetailsImpl<T> implements GameEvent<G, T> {
	final private G game;

	public GameEventImpl(UUID id, LocalDateTime created, Long priority, GameStatusE status, Optional details, G game) {
		super(id, created, priority, status, details);
		this.game = game;
	}

	public GameEventImpl(GameStatusE status, T details, G game) {
		super(status, details);
		this.game = game;
	}

	public GameEventImpl(GameStatusE status, G game) {
		super(status);
		this.game = game;
	}

	@Override
	public G getGame() {
		return game;
	}
}
