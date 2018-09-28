package com.dovidkopel.game.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class SimpleEvent<T> implements Event<T> {
	private final UUID id = UUID.randomUUID();
	private final LocalDateTime time = LocalDateTime.now();

	private final T payload;

	public SimpleEvent(T payload) {
		this.payload = payload;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public T getPayload() {
		return payload;
	}
}
