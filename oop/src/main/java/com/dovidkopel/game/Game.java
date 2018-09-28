package com.dovidkopel.game;

import com.dovidkopel.game.player.PlayerSelector;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.turn.TurnHistory;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Game extends
	Serializable,
	TurnHistory,
	PlayerSelector,
	GameActivity {

	UUID getId();

	LocalDateTime getCreated();

	LocalDateTime getUpdated();

	Turn start();

	void stop();
}
