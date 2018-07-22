package com.dovidkopel.tictactoe.oop.game;

import java.util.Collection;

public interface GameOutcome {
	Collection<GameStatusContainer<?>> getStatuses();
}
