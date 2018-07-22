package com.dovidkopel.tictactoe.oop.strategy;

import com.dovidkopel.tictactoe.oop.game.GameStatusContainer;
import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;

import java.util.List;

public class HorizontalStrategy implements WinningStrategy {
	@Override
	public <T extends GameStatusContainer, S extends TicTacToeBoard> List<T> evaluateGame(S board) {
		return null;
	}

	@Override
	public int compareTo(WinningStrategy o) {
		return 0;
	}
}
