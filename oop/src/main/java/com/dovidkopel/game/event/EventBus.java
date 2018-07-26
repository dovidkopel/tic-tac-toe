package com.dovidkopel.game.event;

import com.dovidkopel.game.event.Event;
import com.dovidkopel.game.event.EventSubscriber;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetails;

import java.util.List;
import java.util.UUID;

public interface EventBus<E extends Event<S>, S> {
	UUID subscribe(EventSubscriber<E, S> callback);

	void unsubscribe(UUID id);

	void unsubscribeAll();

	List<EventSubscriber<E, S>> getSubscriptions();

	List<GameStatusDetails<S>> trigger(E event);
}
