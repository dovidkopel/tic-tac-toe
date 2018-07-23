package com.dovidkopel.tictactoe.oop.game.status;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface GameStatusSubscriber<GE extends GameEvent, S> extends
	Predicate<GE>,
	Function<GE, GameStatusDetails<S>>,
	Comparable<GameStatusSubscriber<GE, S>> {

	UUID getId();

	Long getPriority();

	@Override
	default int compareTo(GameStatusSubscriber<GE, S> o) {
		return getPriority().compareTo(o.getPriority());
	}
}
