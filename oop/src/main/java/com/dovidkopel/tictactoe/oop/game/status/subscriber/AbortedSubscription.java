package com.dovidkopel.tictactoe.oop.game.status.subscriber;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatus;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetailsImpl;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusSubscriberImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class AbortedSubscription<GE extends GameEvent, S> extends GameStatusSubscriberImpl<GE, S> {
	public AbortedSubscription() {
		super(
			2L,
			// Apply the status of aborted
			ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.ABORTED)),
			ge -> {
				// If the current action is to STOP_GAME
				return ge.getStatus() == GameStatus.STOP_GAME &&
					// And the game has already been started
					ge.getGame().getStatuses().contains(GameStatus.STARTED);
			}
		);
	}
}
