package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.game.turn.Action;
import com.dovidkopel.tictactoe.oop.game.turn.Move;
import com.dovidkopel.tictactoe.oop.game.turn.Turn;
import com.dovidkopel.tictactoe.oop.player.Player;

import java.util.Collection;

/**
 * A turn based game
 */
public interface SequentialTurnBasedGame extends Game {
	Collection<GameStatusContainer<?>> getStatuses();

	Player getCurrentPlayer();

	Turn getCurrentTurn();

	//
	Collection<GameStatusContainer<?>> completeTurn(Turn turn, Action action);
}
