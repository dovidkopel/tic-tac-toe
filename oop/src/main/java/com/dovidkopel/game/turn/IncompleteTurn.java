package com.dovidkopel.game.turn;

import com.dovidkopel.tictactoe.oop.game.turn.Action;


public interface IncompleteTurn extends Turn {
	void addAction(Action action);
}
