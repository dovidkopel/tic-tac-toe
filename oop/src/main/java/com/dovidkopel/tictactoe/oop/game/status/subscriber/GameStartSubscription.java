package com.dovidkopel.tictactoe.oop.game.status.subscriber;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusE;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetailsImpl;
import com.dovidkopel.tictactoe.oop.game.status.EventSubscriberImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GameStartSubscription<GE extends GameEvent, S> extends EventSubscriberImpl<GE, S> {
	public GameStartSubscription() {
		super(
			0L,
			// Apply the NOT_STARTED status
			ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatusE.STARTED)),
			ge -> {
				// If the action is not to START_GAME
				return ge.getStatus() == GameStatusE.START_GAME ||
					// And the game has not no STARTED status
					ge.getGame().getStatuses().contains(GameStatusE.STARTED);
			}
		);
	}
}
