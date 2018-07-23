package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.game.turn.Turn;
import com.dovidkopel.tictactoe.oop.game.turn.TurnHistory;

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
