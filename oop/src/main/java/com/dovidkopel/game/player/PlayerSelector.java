package com.dovidkopel.game.player;

import java.util.Collection;

public interface PlayerSelector {
	void reset();

	void init();

	// Who are all of the players
	Collection<Player> getAllPlayers();

	Player getCurrentPlayer();

	Player nextPlayer();
}
