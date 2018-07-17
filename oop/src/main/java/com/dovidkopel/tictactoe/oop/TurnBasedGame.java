package com.dovidkopel.tictactoe.oop;

import java.util.Collection;

public interface TurnBasedGame extends Game {
	Collection<GameStatusContainer<?>> getStatuses();

	Player getCurrentPlayer();
}
