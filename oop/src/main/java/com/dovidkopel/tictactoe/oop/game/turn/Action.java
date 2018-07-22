package com.dovidkopel.tictactoe.oop.game.turn;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Action<T extends Action> extends Serializable, Comparable<T> {
	UUID getId();

	LocalDateTime getCreated();
}
