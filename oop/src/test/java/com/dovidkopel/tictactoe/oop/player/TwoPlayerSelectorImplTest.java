package com.dovidkopel.tictactoe.oop.player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TwoPlayerSelectorImplTest {

	@Test
	public void currentPlayer() {
	}

	@Test
	public void nextPlayerRandom() {
		TwoPlayerSelectorImpl selector = new TwoPlayerSelectorImpl();
		Assert.assertTrue(selector.getCurrentPlayer() == null);
		selector.init();
		int s = selector.getStarting();
		System.out.println("Starting: "+s);
		Assert.assertTrue(selector.getCurrentPlayer() != null);
		List<Player> playerList = new ArrayList();
		for(int x=0;x<100;x++) {
			Player p = selector.nextPlayer();
			Assert.assertTrue(p.equals(selector.getAllPlayers().get(selector.getCurrent())));
			playerList.add(p);
			System.out.println(p);
		}

		long xCount = playerList.stream()
			.filter(p -> p.getLabel().equalsIgnoreCase("x"))
			.count();
		Assert.assertEquals(50, xCount);

		long oCount = playerList.stream()
			.filter(p -> p.getLabel().equalsIgnoreCase("o"))
			.count();
		Assert.assertEquals(50, oCount);
	}

	@Test
	public void nextPlayerX() {
		TwoPlayerSelectorImpl selector = new TwoPlayerSelectorImpl(0);
		Assert.assertTrue(selector.getCurrentPlayer() == null);
		selector.init();
		int s = selector.getStarting();
		System.out.println("Starting: "+s);
		Assert.assertTrue(selector.getCurrentPlayer() != null);
		Assert.assertEquals(selector.playerX, selector.getCurrentPlayer());
	}

	@Test
	public void nextPlayerO() {
		TwoPlayerSelectorImpl selector = new TwoPlayerSelectorImpl(1);
		Assert.assertTrue(selector.getCurrentPlayer() == null);
		selector.init();
		int s = selector.getStarting();
		System.out.println("Starting: "+s);
		Assert.assertTrue(selector.getCurrentPlayer() != null);
		Assert.assertEquals(selector.playerO, selector.getCurrentPlayer());
	}
}