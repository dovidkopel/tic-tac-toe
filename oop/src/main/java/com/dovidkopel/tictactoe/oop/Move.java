package com.dovidkopel.tictactoe.oop;

import java.time.LocalDateTime;

public interface Move {
	Position getPosition();

	Player getPlayer();

	LocalDateTime getTime();
}
