package com.dovidkopel.tictactoe.oop.game.status;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GameStatusEventBusImpl<GE extends GameEvent, S> implements GameStatusEventBus<GE, S> {
	final private List<GameStatusSubscriber<GE, S>> subscribers = new ArrayList();

	private List<GameStatusSubscriber<GE, S>> cloneSubscribers() {
		return new ArrayList(subscribers);
	}

	@Override
	public synchronized UUID subscribe(GameStatusSubscriber callback) {
		List<GameStatusSubscriber<GE, S>> temp = cloneSubscribers();
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
		List<GameStatusSubscriber<GE, S>> temp = cloneSubscribers();

		subscribers.clear();
		subscribers.addAll(
			temp.stream()
				.filter(s -> !s.getId().equals(id))
				.sorted()
				.collect(Collectors.toList())
		);
	}

	@Override
	public List<GameStatusDetails<S>> trigger(GE event) {
		// Already pre-sorted
		return subscribers
			.stream()
			.filter(s -> s.test(event))
			.map(s -> s.apply(event))
			.filter(s -> s != null)
			.sorted()
			.collect(Collectors.toList());
	}
}
