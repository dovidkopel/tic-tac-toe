package com.dovidkopel.tictactoe.oop.game.status;

import java.util.List;
import java.util.UUID;

public interface GameStatusEventBus<GE extends GameEvent, S> {
	UUID subscribe(GameStatusSubscriber<GE, S> callback);

	void unsubscribe(UUID id);

	void unsubscribeAll();

	List<GameStatusSubscriber<GE, S>> getSubscriptions();

	List<GameStatusDetails<S>> trigger(GE event);
}
