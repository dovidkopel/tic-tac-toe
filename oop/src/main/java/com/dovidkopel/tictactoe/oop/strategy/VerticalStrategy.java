package com.dovidkopel.tictactoe.oop.strategy;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.game.board.TicTacToeBoard;

import java.util.List;

public class VerticalStrategy implements WinningStrategy {
	@Override
	public <T extends GameEvent, S extends TicTacToeBoard> List<T> evaluateGame(S board) {
		return null;
	}

	@Override
	public int compareTo(WinningStrategy o) {
		return 0;
	}
}
