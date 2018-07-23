package com.dovidkopel.tictactoe.oop.board;

import com.dovidkopel.tictactoe.oop.position.Position;

import java.util.Collection;

public interface TicTacToeBoard extends Board {
	<T extends Position> Collection<T> getAllPositions();

	<T extends Position> Collection<T> getAvailablePositions();

	<T extends Position> Collection<T> getUnavailablePositions();

	<T extends Position> boolean isAvailablePosition(T position);
}
