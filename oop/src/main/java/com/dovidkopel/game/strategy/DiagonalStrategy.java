package com.dovidkopel.game.strategy;

import com.dovidkopel.game.board.BoardGame;

import java.util.List;

public class DiagonalStrategy implements WinningStrategy<BoardGame> {
	@Override
	public List evaluateGame(BoardGame board) {
		return null;
	}

	@Override
	public int compareTo(WinningStrategy o) {
		return 0;
	}
}
