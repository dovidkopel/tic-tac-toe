package com.dovidkopel.tictactoe.oop;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Game extends Serializable {
	UUID getId();

	LocalDateTime getCreated();

	LocalDateTime getUpdated();
}
