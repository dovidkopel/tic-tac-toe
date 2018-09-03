package com.dovidkopel.game;

import com.dovidkopel.game.event.Event;
import com.dovidkopel.game.status.GameStatus;
import com.dovidkopel.game.status.Status;

import java.util.List;
import java.util.stream.Collectors;

public interface GameActivity {
	// An `Event` may be a timeout
	// An `Event` may be as a result of an `Action`
	List<? extends Status> evaluate(Event event);

	List<? extends Status> getStatusDetails();
}