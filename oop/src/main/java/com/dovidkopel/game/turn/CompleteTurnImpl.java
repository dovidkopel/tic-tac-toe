package com.dovidkopel.game.turn;

import com.dovidkopel.game.action.Action;
import com.dovidkopel.game.status.TurnStatus;

import java.util.Collection;
import java.util.Set;

public class CompleteTurnImpl extends TurnImpl implements CompleteTurn {
	private IncompleteTurn turn;

	public CompleteTurnImpl(IncompleteTurn turn) {
		super(turn);
		this.turn = turn;
	}

	@Override
	public Set<? extends TurnStatus> getStatuses() {
		return turn.getStatuses();
	}

	@Override
	public Collection<? extends Action> getActions() {
		return turn.getActions();
	}
}
