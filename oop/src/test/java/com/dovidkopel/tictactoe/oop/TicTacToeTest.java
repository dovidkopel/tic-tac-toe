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

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicTacToeTest {
	@Autowired
	TicTacToe ticTacToe;

	@Autowired
	GameStatusEventBus eventBus;

	@Autowired
	ApplicationContext context;

	@Test
	public void simpleEventTest() {
		eventBus.unsubscribeAll();
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.NOT_STARTED)),
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
		eventBus.unsubscribeAll();
		final Queue<GameStatus> d = new ArrayBlockingQueue(3);

		// Checking that the priority will
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				2L,
				// Apply the status of aborted
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.ABORTED)),
				ge -> {
					Assert.assertEquals(GameStatus.ABORTED, d.remove());
					// If the current action is to STOP_GAME
					return ge.getStatus() == GameStatus.STOP_GAME &&
					// And the game has already been started
					ge.getGame().getStatuses().contains(GameStatus.STARTED);
				}
			)
		);
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				0L,
				// Apply the NOT_STARTED status
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.NOT_STARTED)),
				ge -> {
					Assert.assertEquals(GameStatus.NOT_STARTED, d.remove());
					// If the action is not to START_GAME
					return ge.getStatus() != GameStatus.START_GAME &&
					// And the game has not no STARTED status
					!ge.getGame().getStatuses().contains(GameStatus.STARTED);
				}
			)
		);
		eventBus.subscribe(
			new GameStatusSubscriberImpl<>(
				1L,
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatus.STARTED)),
				ge -> {
					Assert.assertEquals(GameStatus.STARTED, d.remove());
					return ge.getStatus() == GameStatus.START_GAME ||
						ge.getGame().getStatuses().contains(GameStatus.STARTED);
				}
			)
		);

		d.add(GameStatus.NOT_STARTED);
		d.add(GameStatus.STARTED);
		d.add(GameStatus.ABORTED);
		List<GameStatusDetails> std = ticTacToe.evaluate(GameStatus.NOOP);
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatus.NOT_STARTED, std.get(0).getStatus());

		d.add(GameStatus.NOT_STARTED);
		d.add(GameStatus.STARTED);
		d.add(GameStatus.ABORTED);
		ticTacToe.start();

		std = ticTacToe.getStatusDetails();
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatus.STARTED, std.get(0).getStatus());

		d.add(GameStatus.NOT_STARTED);
		d.add(GameStatus.STARTED);
		d.add(GameStatus.ABORTED);
		ticTacToe.stop();

		std = ticTacToe.getStatusDetails();
		Assert.assertEquals(2, std.size());
		Assert.assertEquals(GameStatus.STARTED, std.get(0).getStatus());
		Assert.assertEquals(GameStatus.ABORTED, std.get(1).getStatus());
	}

	@Test
	public void test3() {
		GameStatusEventBus bus = context.getBean(GameStatusEventBus.class);
		TicTacToe t = context.getBean(TicTacToe.class);
		Assert.assertEquals(3, bus.getSubscriptions().size());
		List<GameStatus> statuses = t.getStatuses();
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatus.NOT_STARTED));

		t.start();
		statuses = t.getStatuses();
		System.out.println("Started...");
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatus.STARTED));

		t.stop();
		System.out.println("Stopped...");
		statuses = t.getStatuses();
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatus.ABORTED));
	}
}