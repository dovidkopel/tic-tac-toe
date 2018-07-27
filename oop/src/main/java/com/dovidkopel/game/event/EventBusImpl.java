package com.dovidkopel.game.event;

import com.dovidkopel.game.event.EventBus;
import com.dovidkopel.game.event.EventSubscriber;
import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventBusImpl<E extends Event<S>, S> implements EventBus<E, S> {
	final private List<EventSubscriber<E, S>> subscribers = new ArrayList();

	final private List<EventSubscriber<E, S>> autowiredSubscribers = new ArrayList();

	@Autowired
	public void setAutowiredSubscribers(List<EventSubscriber<E, S>> subs) {
		this.autowiredSubscribers.addAll(subs);
		subs.forEach(this::subscribe);
	}

	private List<EventSubscriber<E, S>> cloneSubscribers() {
		return new ArrayList(subscribers);
	}

	@Override
	public synchronized UUID subscribe(EventSubscriber callback) {
		List<EventSubscriber<E, S>> temp = cloneSubscribers();
		temp.add(callback);

		subscribers.clear();
		subscribers.addAll(
			temp.stream()
				.sorted()
				.collect(Collectors.toList())
		);

		return callback.getId();
	}

	@Override
	public synchronized void unsubscribe(UUID id) {
		List<EventSubscriber<E, S>> temp = cloneSubscribers();

		subscribers.clear();
		subscribers.addAll(
			temp.stream()
				.filter(s -> !s.getId().equals(id))
				.sorted()
				.collect(Collectors.toList())
		);
	}

	@Override
	public void unsubscribeAll() {
		subscribers.clear();
	}

	@Override
	public List<EventSubscriber<E, S>> getSubscriptions() {
		return subscribers;
	}

	@Override
	public List<GameStatusDetails<S>> trigger(E event) {
		// Already pre-sorted
		return subscribers
			.stream()
			.filter(s -> s.test(event))
			.flatMap(s -> s.apply(event).stream())
			.filter(s -> s != null)
			.sorted()
			.collect(Collectors.toList());
	}
}
