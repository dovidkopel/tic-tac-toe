package com.dovidkopel.game.strategy;

import com.dovidkopel.game.board.BoardGame;

import java.util.List;

public interface StrategyScanner {
	List<WinningStrategy<BoardGame>> getWinningStrategies();
}
