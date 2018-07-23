package com.dovidkopel.tictactoe.oop.player;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface PlayerSelector {
	void reset();

	void init();

	// Who are all of the players
	Collection<Player> getAllPlayers();

	Player getCurrentPlayer();

	Player nextPlayer();
}
