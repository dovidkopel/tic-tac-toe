package com.dovidkopel.game.strategy;

import com.dovidkopel.game.board.BoardGame;
import com.dovidkopel.game.status.Status;

import java.util.List;

public interface WinningStrategy<S extends BoardGame> extends Comparable<WinningStrategy> {
	<T extends Status> List<T> evaluateGame(S board);
}
