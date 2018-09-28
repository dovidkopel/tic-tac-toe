package com.dovidkopel.game.player;

import java.util.Collection;

// Can not have more than a single active player
// per game. For most board games and card games that
// is fine. Not sure if there are games where this would not
// work.
// There are a few important tasks for this interface
// The first being deciding who should be the first
// player. The second being who is the next player.

// Do all players remain active in the game?
// Is the order random?
// To handle exclusions of players the Game
// should
public interface PlayerSelector {
	void reset();

	void init();

	// Who are all of the players
	Collection<Player> getAllPlayers();

	Player getCurrentPlayer();

	Player nextPlayer();
}
