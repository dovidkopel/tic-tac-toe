package com.dovidkopel.game.strategy;

import com.dovidkopel.game.board.BoardGame;
import com.dovidkopel.game.status.Status;

import java.util.List;

public class VerticalStrategy implements WinningStrategy<BoardGame> {
	@Override
	public <T extends Status> List<T> evaluateGame(BoardGame board) {
		return null;
	}

	@Override
	public int compareTo(WinningStrategy o) {
		return 0;
	}
}
