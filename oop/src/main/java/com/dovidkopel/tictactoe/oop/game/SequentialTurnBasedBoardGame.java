package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.game.board.Board;
import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.turn.Action;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.player.Player;

import java.util.Collection;

/**
 * A turn based game
 */
public interface SequentialTurnBasedBoardGame<T extends Board> extends BoardGame<T> {
	Player getCurrentPlayer();

	Turn getCurrentTurn();

	Turn getNextTurn();

	Collection<GameEvent> completeTurn(Turn turn, Action action);
}
