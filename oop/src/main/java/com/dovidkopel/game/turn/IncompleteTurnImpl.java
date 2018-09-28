package com.dovidkopel.game.turn;

import com.dovidkopel.game.action.Action;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.status.TurnStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class IncompleteTurnImpl extends TurnImpl implements IncompleteTurn {

	public IncompleteTurnImpl(Turn turn) {
		super(turn);
	}

	public IncompleteTurnImpl(UUID id, LocalDateTime created, Player player) {
		super(id, created, player);
	}

	public IncompleteTurnImpl(Player player) {
		super(player);
	}

	@Override
	public void addAction(Action action) {

	}

	@Override
	public Set<? extends TurnStatus> getStatuses() {
		return null;
	}

	@Override
	public Collection<? extends Action> getActions() {
		return null;
	}
}
