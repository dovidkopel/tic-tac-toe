package com.dovidkopel.game.action;

import com.dovidkopel.game.Game;
import com.dovidkopel.game.board.BoardGame;

import java.io.Serializable;

// A given action may have input parameters
// it is up to the `Action` to confer with the `Game`
// and the `Board` to determine whether or not
// the `Action` is valid or not
public interface Action<T extends Game> extends Serializable {
	// The action will know what it is doing
	Boolean isValid(T game);
}
