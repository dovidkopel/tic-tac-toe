package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.game.board.Board;

public interface BoardGame<T extends Board> extends Game {
	T getBoard();
}
