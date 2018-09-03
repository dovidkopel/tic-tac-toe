package com.dovidkopel.game.turn;


import com.dovidkopel.game.action.Action;

public interface IncompleteTurn extends Turn {
	void addAction(Action action);
}
