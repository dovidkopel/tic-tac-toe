package com.dovidkopel.game.event;

import com.dovidkopel.game.status.Status;

import java.util.List;
import java.util.UUID;

public interface EventBus<E extends Event<S>, S> {
	UUID subscribe(EventSubscriber<E, S> callback);

	void unsubscribe(UUID id);

	void unsubscribeAll();

	List<EventSubscriber<E, S>> getSubscriptions();

	List<? extends Status> trigger(E event);
}
