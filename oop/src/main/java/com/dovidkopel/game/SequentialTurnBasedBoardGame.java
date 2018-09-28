package com.dovidkopel.game;

import com.dovidkopel.game.board.Board;
import com.dovidkopel.game.board.BoardGame;
import com.dovidkopel.game.player.Player;

/**
 * A turn based game
 */
public interface SequentialTurnBasedBoardGame<T extends Board> extends BoardGame<T> {
}
