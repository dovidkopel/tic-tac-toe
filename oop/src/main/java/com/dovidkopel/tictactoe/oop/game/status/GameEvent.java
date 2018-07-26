package com.dovidkopel.tictactoe.oop.game.status;

import com.dovidkopel.game.Game;

public interface GameEvent<G extends Game, T> extends GameStatusDetails<T> {
	G getGame();
}