package com.dovidkopel.game.event;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventSubscriberImpl<E extends Event<S>, S> implements EventSubscriber<E, S> {
	final private UUID id;

	final private Long priority;

	final private Predicate<E> predicate;

	final private Function<E, Collection<?>> func;

	public EventSubscriberImpl(Long priority, Function<E, Collection<?>> func, Predicate<E> predicate) {
		this(UUID.randomUUID(), priority, func, predicate);
	}

	public EventSubscriberImpl(Function<E, Collection<?>> func, Predicate<E> predicate) {
		this(UUID.randomUUID(), Long.MAX_VALUE, func, predicate);
	}

	public EventSubscriberImpl(UUID id, Long priority, Function<E, Collection<?>> func, Predicate<E> predicate) {
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
	public Collection<?> apply(E ge) {
		return func.apply(ge);
	}

	@Override
	public boolean test(E event) {
		return predicate.test(event);
	}
}
