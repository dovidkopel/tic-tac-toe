package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.board.Board;

public interface BoardGame<T extends Board> extends Game {
	T getBoard();
}
