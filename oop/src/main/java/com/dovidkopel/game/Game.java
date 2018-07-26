package com.dovidkopel.game;

import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.turn.TurnHistory;
import com.dovidkopel.tictactoe.oop.game.GameOutcome;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Game extends
	Serializable,
	TurnHistory,
	GameOutcome {

	UUID getId();

	LocalDateTime getCreated();

	LocalDateTime getUpdated();

	Turn start();

	void stop();
}
