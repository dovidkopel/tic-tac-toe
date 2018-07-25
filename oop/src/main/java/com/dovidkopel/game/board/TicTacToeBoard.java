package com.dovidkopel.game.board;

import com.dovidkopel.game.position.Position;

import java.util.Collection;

public interface TicTacToeBoard extends Board {
	<T extends Position> Collection<T> getAvailablePositions();

	<T extends Position> Collection<T> getUnavailablePositions();

	<T extends Position> boolean isAvailablePosition(T position);
}
