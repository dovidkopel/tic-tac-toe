package com.dovidkopel.tictactoe.oop.game.status.subscriber;

import com.dovidkopel.tictactoe.oop.game.status.GameEvent;
import com.dovidkopel.tictactoe.oop.game.status.GameStatus;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusDetailsImpl;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusSubscriberImpl;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GameStartSubscription<GE extends GameEvent, S> extends GameStatusSubscriberImpl<GE, S> {
	public GameStartSubscription() {
		super(
			0L,
			// Apply the NOT_STARTED status
			ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.STARTED)),
			ge -> {
				// If the action is not to START_GAME
				return ge.getStatus() == GameStatus.START_GAME ||
					// And the game has not no STARTED status
					ge.getGame().getStatuses().contains(GameStatus.STARTED);
			}
		);
	}
}
