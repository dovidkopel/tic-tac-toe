package com.dovidkopel.tictactoe.oop.game.status;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface GameStatusDetails<T> extends Serializable, Comparable<GameStatusDetails<T>> {
	UUID getId();

	LocalDateTime getCreated();

	GameStatusE getStatus();

	Long getPriority();

	Optional<T> getDetails();

	@Override
	default int compareTo(GameStatusDetails<T> o) {
		return getPriority().compareTo(o.getPriority());
	}
}
