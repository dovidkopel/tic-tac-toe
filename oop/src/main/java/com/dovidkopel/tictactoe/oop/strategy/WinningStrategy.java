package com.dovidkopel.tictactoe.oop.strategy;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;

import java.util.List;

public interface WinningStrategy extends Comparable<WinningStrategy> {
	<T extends GameEvent, S extends TicTacToeBoard> List<T> evaluateGame(S board);
}
