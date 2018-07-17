package com.dovidkopel.tictactoe.oop;

import java.util.Collection;

public interface TicTacToeBoard {
	<T extends Position> Collection<T> getAllPositions();

	<T extends Position> Collection<T> getAvailablePositions();

	<T extends Position> Collection<T> getUnavailablePositions();

	<T extends Position> boolean isAvailablePosition(T position);
}
