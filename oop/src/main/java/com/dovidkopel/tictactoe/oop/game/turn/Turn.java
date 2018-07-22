package com.dovidkopel.tictactoe.oop.game.turn;

import com.dovidkopel.tictactoe.oop.player.Player;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * A turn is
 */
public interface Turn<T extends Action> extends Serializable, Comparable<Turn> {
	UUID getId();

	LocalDateTime getCreated();

	LocalDateTime getUpdated();

	Optional<T> getAction();

	Player getPlayer();

	default boolean turnCompleted() {
		return getAction().isPresent();
	}
}
