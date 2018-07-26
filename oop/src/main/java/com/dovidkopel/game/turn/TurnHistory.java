package com.dovidkopel.game.turn;

import com.dovidkopel.game.status.TurnStatus;
import com.dovidkopel.game.turn.IncompleteTurn;
import com.dovidkopel.game.turn.Turn;

import java.util.Collection;
import java.util.List;

// Stores a games turns
//
public interface TurnHistory {
	List<Turn> getTurns();

	<T extends IncompleteTurn> T getCurrentTurn();

	<T extends IncompleteTurn> T getNextTurn();

	Collection<? extends TurnStatus> completeTurn(IncompleteTurn turn);
}
