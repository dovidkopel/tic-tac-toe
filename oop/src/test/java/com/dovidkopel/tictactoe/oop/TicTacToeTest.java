package com.dovidkopel.tictactoe.oop;

import com.dovidkopel.tictactoe.oop.game.Game;
import com.dovidkopel.tictactoe.oop.game.SequentialTurnBasedBoardGameImpl;
import com.dovidkopel.tictactoe.oop.game.status.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicTacToeTest {
	@Autowired
	TicTacToe ticTacToe;

	@Autowired
	GameStatusEventBus eventBus;

	@Test
	public void simpleEventTest() {
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				ge -> new GameStatusDetailsImpl(GameStatus.NOT_STARTED),
				ge -> !ge.getGame().getStatuses().contains(GameStatus.STARTED)
			)
		);
		List<GameStatusDetails> std = ticTacToe.evaluate(
			new GameEventImpl(GameStatus.NOOP, ticTacToe)
		);
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatus.NOT_STARTED, std.get(0).getStatus());
	}

	@Test
	public void simpleEventTest1() {
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				ge -> new GameStatusDetailsImpl(GameStatus.NOT_STARTED),
				ge ->
					ge.getStatus() != GameStatus.START_GAME &&
					!ge.getGame().getStatuses().contains(GameStatus.STARTED)
			)
		);
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				ge -> new GameStatusDetailsImpl(GameStatus.STARTED),
				ge -> ge.getStatus() == GameStatus.START_GAME ||
					  ge.getGame().getStatuses().contains(GameStatus.STARTED)
			)
		);
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				ge -> new GameStatusDetailsImpl(GameStatus.ABORTED),
				ge -> ge.getStatus() == GameStatus.STOP_GAME &&
					  ge.getGame().getStatuses().contains(GameStatus.STARTED)
			)
		);

		List<GameStatusDetails> std = ticTacToe.evaluate(GameStatus.NOOP);
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatus.NOT_STARTED, std.get(0).getStatus());

		ticTacToe.start();

		std = ticTacToe.getStatusDetails();
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatus.STARTED, std.get(0).getStatus());

		ticTacToe.stop();

		std = ticTacToe.getStatusDetails();
		Assert.assertEquals(2, std.size());
		Assert.assertEquals(GameStatus.STARTED, std.get(0).getStatus());
		Assert.assertEquals(GameStatus.ABORTED, std.get(1).getStatus());
	}


}