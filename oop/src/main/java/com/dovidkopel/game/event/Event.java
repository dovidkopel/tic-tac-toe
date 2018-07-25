package com.dovidkopel.game.event;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Event<T> extends Serializable {
	UUID getId();

	LocalDateTime getTime();

	T getPayload();
}
