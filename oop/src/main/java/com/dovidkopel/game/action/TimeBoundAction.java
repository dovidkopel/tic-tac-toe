package com.dovidkopel.game.action;

import java.time.LocalDateTime;

public interface TimeBoundAction extends Action {
	LocalDateTime getCreated();

	Long getDuration();

	Long getElapsed();

	Long getRemaining();

	Boolean isTimeRunOut();
}
