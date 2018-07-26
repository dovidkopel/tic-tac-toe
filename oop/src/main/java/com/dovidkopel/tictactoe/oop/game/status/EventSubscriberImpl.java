package com.dovidkopel.tictactoe.oop.game.status;

import com.dovidkopel.game.event.EventSubscriber;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventSubscriberImpl<GE extends GameEvent, S> implements EventSubscriber<GE, S> {
	final private UUID id;

	final private Long priority;

	final private Predicate<GE> predicate;

	final private Function<GE, Collection<GameStatusDetails<S>>> func;

	public EventSubscriberImpl(Long priority, Function<GE, Collection<GameStatusDetails<S>>> func, Predicate<GE> predicate) {
		this(UUID.randomUUID(), priority, func, predicate);
	}

	public EventSubscriberImpl(Function<GE, Collection<GameStatusDetails<S>>> func, Predicate<GE> predicate) {
		this(UUID.randomUUID(), Long.MAX_VALUE, func, predicate);
	}

	public EventSubscriberImpl(UUID id, Long priority, Function<GE, Collection<GameStatusDetails<S>>> func, Predicate<GE> predicate) {
		this.id = id;
		this.priority = priority;
		this.predicate = predicate;
		this.func = func;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public Long getPriority() {
		return priority;
	}

	@Override
	public Collection<GameStatusDetails<S>> apply(GE ge) {
		return func.apply(ge);
	}

	@Override
	public boolean test(GE event) {
		return predicate.test(event);
	}
}
