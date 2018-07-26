package com.dovidkopel.game.board;

import com.dovidkopel.game.Game;
import com.dovidkopel.game.board.Board;

public interface BoardGame<T extends Board> extends Game {
	T getBoard();
}
