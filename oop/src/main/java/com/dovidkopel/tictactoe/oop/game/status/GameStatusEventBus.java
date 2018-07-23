package com.dovidkopel.tictactoe.oop.game.status;

import java.util.List;
import java.util.UUID;

public interface GameStatusEventBus<GE extends GameEvent, S> {
	UUID subscribe(GameStatusSubscriber callback);

	void unsubscribe(UUID id);

	List<GameStatusDetails<S>> trigger(GE event);
}
