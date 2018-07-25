package com.dovidkopel.game.trigger;

import com.dovidkopel.game.event.Event;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Trigger<P, R> extends Function<P, Event<R>>, Predicate<P> {
}
