package com.dovidkopel.tictactoe.oop.board;

import com.dovidkopel.tictactoe.oop.position.Position;
import com.dovidkopel.tictactoe.oop.position.TwoDimensionalPosition;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
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
