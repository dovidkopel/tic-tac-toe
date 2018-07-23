package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatus;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface GameOutcome {
	List<GameStatusDetails> evaluate(GameEvent event);

	List<GameStatusDetails> getStatusDetails();

	default List<GameStatus> getStatuses() {
		return getStatusDetails()
			.stream()
			.map(sd -> sd.getStatus())
			.distinct()
			.collect(Collectors.toList());
	}
}