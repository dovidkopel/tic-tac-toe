package com.dovidkopel.game.turn;

import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.status.TurnStatus;
import com.dovidkopel.tictactoe.oop.game.turn.Action;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * A turn is
 */
public interface Turn extends Serializable, Comparable<Turn> {
	UUID getId();

	LocalDateTime getCreated();

	LocalDateTime getUpdated();

	Set<? extends TurnStatus> getStatuses();

	Player getPlayer();

	Collection<? extends Action> getActions();
}
