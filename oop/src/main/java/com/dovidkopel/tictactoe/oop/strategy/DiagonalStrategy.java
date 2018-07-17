package com.dovidkopel.tictactoe.oop.strategy;

import com.dovidkopel.tictactoe.oop.GameStatusContainer;
import com.dovidkopel.tictactoe.oop.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.WinningStrategy;

import java.util.List;

public class DiagonalStrategy implements WinningStrategy {
	@Override
	public <T extends GameStatusContainer, S extends TicTacToeBoard> List<T> evaluateGame(S board) {
		return null;
	}

	@Override
	public int compareTo(WinningStrategy o) {
		return 0;
	}
}
