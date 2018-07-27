package com.dovidkopel.game.event;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public interface EventSubscriber<E extends Event<S>, S> extends
	Predicate<E>,
	Function<E, Collection<?>>,
	Comparable<EventSubscriber<E, S>> {

	UUID getId();

	Long getPriority();

	@Override
	default int compareTo(EventSubscriber<E, S> o) {
		return getPriority().compareTo(o.getPriority());
	}
}
