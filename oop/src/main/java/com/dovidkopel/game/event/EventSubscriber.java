package com.dovidkopel.game.event;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public interface EventSubscriber<E extends Event<S>, S> extends
	// Am I interested in this event?
	Predicate<E>,
	// I am interested in this event, and this is how I will
	// handle it.
	Function<E, Collection<?>>,
	// Priority
	Comparable<EventSubscriber<E, S>> {

	UUID getId();

	Long getPriority();

	@Override
	default int compareTo(EventSubscriber<E, S> o) {
		return getPriority().compareTo(o.getPriority());
	}
}
