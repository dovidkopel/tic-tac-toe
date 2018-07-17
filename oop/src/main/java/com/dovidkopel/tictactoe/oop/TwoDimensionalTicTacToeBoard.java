package com.dovidkopel.tictactoe.oop;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TwoDimensionalTicTacToeBoard implements TicTacToeBoard {
	Set<TwoDimensionalPosition> positions = new HashSet();

	@Override
	public Collection<TwoDimensionalPosition> getAllPositions() {
		return positions;
	}

	@Override
	public Collection<TwoDimensionalPosition> getAvailablePositions() {
		return positions
			.stream()
			.filter(p -> p.isEmpty())
			.collect(Collectors.toSet());
	}

	@Override
	public Collection<TwoDimensionalPosition> getUnavailablePositions() {
		return positions
			.stream()
			.filter(p -> p.isOccupied())
			.collect(Collectors.toSet());
	}

	@Override
	public <T extends Position> boolean isAvailablePosition(T position) {
		return getAvailablePositions().contains(position);
	}
}
