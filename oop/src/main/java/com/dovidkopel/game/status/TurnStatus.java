package com.dovidkopel.game.status;

public interface TurnStatus extends Status {

	public static enum TurnStaticEntry implements TurnStatus {
		TURN_PRE_SELECTION,
		TURN_ACTIVE,
		TURN_EVALUATING
	}
}
