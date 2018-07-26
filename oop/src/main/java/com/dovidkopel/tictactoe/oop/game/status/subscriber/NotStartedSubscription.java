package com.dovidkopel.tictactoe.oop.game.status.subscriber;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusE;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetailsImpl;
import com.dovidkopel.tictactoe.oop.game.status.EventSubscriberImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NotStartedSubscription<GE extends GameEvent, S> extends EventSubscriberImpl<GE, S> {
	public NotStartedSubscription() {
		super(
			0L,
			ge -> Arrays.asList(
				new GameStatusDetailsImpl(
					GameStatusE.NOT_STARTED
				)
			),
			ge -> !ge.getGame().getStatuses().contains(GameStatusE.STARTED) &&
				!ge.getStatus().equals(GameStatusE.START_GAME)
		);
	}
}
