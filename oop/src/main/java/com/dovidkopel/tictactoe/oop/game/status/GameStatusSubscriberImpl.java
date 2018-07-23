package com.dovidkopel.tictactoe.oop.game.status;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public class GameStatusSubscriberImpl<GE extends GameEvent, S> implements GameStatusSubscriber<GE, S> {
	final private UUID id;

	final private Long priority;

	final private Predicate<GE> predicate;

	final private Function<GE, GameStatusDetails<S>> func;

	public GameStatusSubscriberImpl(Function<GE, GameStatusDetails<S>> func) {
		this(UUID.randomUUID(), Long.MAX_VALUE, func, ge -> true);
	}

	public GameStatusSubscriberImpl(Function<GE, GameStatusDetails<S>> func, Predicate<GE> predicate) {
		this(UUID.randomUUID(), Long.MAX_VALUE, func, predicate);
	}

	public GameStatusSubscriberImpl(UUID id, Long priority, Function<GE, GameStatusDetails<S>> func, Predicate<GE> predicate) {
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
	public GameStatusDetails<S> apply(GE ge) {
		return func.apply(ge);
	}

	@Override
	public boolean test(GE event) {
		return predicate.test(event);
	}
}
