package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.board.Board;
import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.turn.Action;
import com.dovidkopel.tictactoe.oop.game.turn.Turn;
import com.dovidkopel.tictactoe.oop.player.Player;

import java.util.Collection;

/**
 * A turn based game
 */
public interface SequentialTurnBasedBoardGame<T extends Board> extends BoardGame<T> {
	Player getCurrentPlayer();

	Turn getCurrentTurn();

	Collection<GameEvent> completeTurn(Turn turn, Action action);
}
