package com.dovidkopel.tictactoe.oop.game.turn;

import com.dovidkopel.game.turn.Turn;

import java.util.List;

// Stores a games turns
public interface TurnHistory {
	List<Turn> getTurns();

	Turn getCurrentTurn();

	Turn getNextTurn();

	 completeTurn(Turn turn, Action action);
}
