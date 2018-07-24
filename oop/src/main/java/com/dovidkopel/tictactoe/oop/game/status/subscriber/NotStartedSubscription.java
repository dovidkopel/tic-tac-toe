package com.dovidkopel.tictactoe.oop.game.status.subscriber;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatus;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetailsImpl;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusSubscriberImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NotStartedSubscription<GE extends GameEvent, S> extends GameStatusSubscriberImpl<GE, S> {
	public NotStartedSubscription() {
		super(
			0L,
			ge -> Arrays.asList(
				new GameStatusDetailsImpl(
					GameStatus.NOT_STARTED
				)
			),
			ge -> !ge.getGame().getStatuses().contains(GameStatus.STARTED) &&
				!ge.getStatus().equals(GameStatus.START_GAME)
		);
	}
}
