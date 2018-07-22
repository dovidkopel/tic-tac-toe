package com.dovidkopel.tictactoe.oop.strategy;

import com.dovidkopel.tictactoe.oop.game.GameStatusContainer;
import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;

import java.util.List;

public interface WinningStrategy extends Comparable<WinningStrategy> {
	<T extends GameStatusContainer, S extends TicTacToeBoard> List<T> evaluateGame(S board);
}
