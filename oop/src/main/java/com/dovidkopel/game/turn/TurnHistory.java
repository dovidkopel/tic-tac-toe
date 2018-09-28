package com.dovidkopel.game.turn;

import com.dovidkopel.game.status.Status;
import com.dovidkopel.game.status.TurnStatus;
import com.dovidkopel.game.turn.IncompleteTurn;
import com.dovidkopel.game.turn.Turn;

import java.util.Collection;
import java.util.List;

// Stores a games turns
// A Turn should be more complex than a simple Action
// For instance in the game of monopoly a Turn may include
// rolling the dice, then moving the piece
// then the player me be able to offer another player to purchase
// some property. All this must be contained within a Turn.
// Starting from the initial game state + board
// applying each Turn in order should always yield the same
// state.
// A Turn may have numerious Actions within it
// The TurnHistory is not responsible for
public interface TurnHistory {
	List<Turn> getTurns();

	// If this is not populated an error will be thrown
	Turn getCurrentTurn();

	// If there are no more Turns an error will be thrown
	Turn getNextTurn();

	Collection<? extends Status> completeTurn(IncompleteTurn turn);
}
