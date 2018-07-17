package com.dovidkopel.tictactoe.oop;

import java.util.Collection;

public interface GameOutcome extends Game {
	Collection<GameStatusContainer<?>> getStatuses();
}
