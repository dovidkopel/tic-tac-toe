package com.dovidkopel.tictactoe.oop;

import com.dovidkopel.game.event.EventBus;
import com.dovidkopel.tictactoe.oop.game.TicTacToeImpl;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicTacToeImplTest {
	@Autowired
	TicTacToeImpl ticTacToeImpl;

	@Autowired
	EventBus eventBus;

	@Autowired
	ApplicationContext context;

	@Test
	public void simpleEventTest() {
		eventBus.unsubscribeAll();
		eventBus.subscribe(
			new EventSubscriberImpl<>(
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatusE.NOT_STARTED)),
				ge -> !ge.getGame().getStatuses().contains(GameStatusE.STARTED)
			)
		);
		List<GameStatusDetails> std = ticTacToeImpl.evaluate(
			new GameEventImpl(GameStatusE.NOOP, ticTacToeImpl)
		);
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatusE.NOT_STARTED, std.get(0).getStatus());
	}

	@Test
	public void simpleEventTest1() {
		eventBus.unsubscribeAll();
		final Queue<GameStatusE> d = new ArrayBlockingQueue(3);

		// Checking that the priority will
		eventBus.subscribe(
			new EventSubscriberImpl<>(
				2L,
				// Apply the status of aborted
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatusE.ABORTED)),
				ge -> {
					Assert.assertEquals(GameStatusE.ABORTED, d.remove());
					// If the current action is to STOP_GAME
					return ge.getStatus() == GameStatusE.STOP_GAME &&
					// And the game has already been started
					ge.getGame().getStatuses().contains(GameStatusE.STARTED);
				}
			)
		);
		eventBus.subscribe(
			new EventSubscriberImpl<>(
				0L,
				// Apply the NOT_STARTED status
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatusE.NOT_STARTED)),
				ge -> {
					Assert.assertEquals(GameStatusE.NOT_STARTED, d.remove());
					// If the action is not to START_GAME
					return ge.getStatus() != GameStatusE.START_GAME &&
					// And the game has not no STARTED status
					!ge.getGame().getStatuses().contains(GameStatusE.STARTED);
				}
			)
		);
		eventBus.subscribe(
			new EventSubscriberImpl<>(
				1L,
				ge -> Arrays.asList(new GameStatusDetailsImpl(GameStatusE.STARTED)),
				ge -> {
					Assert.assertEquals(GameStatusE.STARTED, d.remove());
					return ge.getStatus() == GameStatusE.START_GAME ||
						ge.getGame().getStatuses().contains(GameStatusE.STARTED);
				}
			)
		);

		d.add(GameStatusE.NOT_STARTED);
		d.add(GameStatusE.STARTED);
		d.add(GameStatusE.ABORTED);
		List<GameStatusDetails> std = ticTacToeImpl.evaluate(GameStatusE.NOOP);
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatusE.NOT_STARTED, std.get(0).getStatus());

		d.add(GameStatusE.NOT_STARTED);
		d.add(GameStatusE.STARTED);
		d.add(GameStatusE.ABORTED);
		ticTacToeImpl.start();

		std = ticTacToeImpl.getStatusDetails();
		Assert.assertEquals(1, std.size());
		Assert.assertEquals(GameStatusE.STARTED, std.get(0).getStatus());

		d.add(GameStatusE.NOT_STARTED);
		d.add(GameStatusE.STARTED);
		d.add(GameStatusE.ABORTED);
		ticTacToeImpl.stop();

		std = ticTacToeImpl.getStatusDetails();
		Assert.assertEquals(2, std.size());
		Assert.assertEquals(GameStatusE.STARTED, std.get(0).getStatus());
		Assert.assertEquals(GameStatusE.ABORTED, std.get(1).getStatus());
	}

	@Test
	public void test3() {
		EventBus bus = context.getBean(EventBus.class);
		TicTacToeImpl t = context.getBean(TicTacToeImpl.class);
		Assert.assertEquals(3, bus.getSubscriptions().size());
		List<GameStatusE> statuses = t.getStatuses();
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatusE.NOT_STARTED));

		t.start();
		statuses = t.getStatuses();
		System.out.println("Started...");
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatusE.STARTED));

		t.stop();
		System.out.println("Stopped...");
		statuses = t.getStatuses();
		statuses.forEach(sd -> System.out.println(sd));
		Assert.assertTrue(statuses.contains(GameStatusE.ABORTED));
	}
}