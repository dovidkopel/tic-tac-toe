package com.dovidkopel.tictactoe.oop;

import java.util.List;

public interface WinningStrategy extends Comparable<WinningStrategy> {
	<T extends GameStatusContainer, S extends TicTacToeBoard> List<T> evaluateGame(S board);
}
