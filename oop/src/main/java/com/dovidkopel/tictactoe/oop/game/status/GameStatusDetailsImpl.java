package com.dovidkopel.tictactoe.oop.game.status;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class GameStatusDetailsImpl<T> implements GameStatusDetails<T> {
	final private UUID id;
	final private LocalDateTime created;
	final private Long priority;
	final private GameStatus status;
	final private Optional<T> details;

	public GameStatusDetailsImpl(UUID id, LocalDateTime created, Long priority, GameStatus status, Optional<T> details) {
		this.id = id;
		this.created = created;
		this.priority = priority;
		this.status = status;
		this.details = details;
	}

	public GameStatusDetailsImpl(GameStatus status, T details) {
		this(UUID.randomUUID(), LocalDateTime.now(), 0L, status, Optional.of(details));
	}

	public GameStatusDetailsImpl(GameStatus status) {
		this(UUID.randomUUID(), LocalDateTime.now(), 0L, status, Optional.empty());
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
	public GameStatus getStatus() {
		return status;
	}

	@Override
	public Long getPriority() {
		return priority;
	}

	@Override
	public Optional<T> getDetails() {
		return details;
	}

	@Override
	public int compareTo(GameStatusDetails<T> o) {
		return getPriority().compareTo(o.getPriority());
	}
}
