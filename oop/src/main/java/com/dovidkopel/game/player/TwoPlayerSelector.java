package com.dovidkopel.game.player;

import java.util.*;
import java.util.function.Supplier;

public abstract class TwoPlayerSelector implements PlayerSelector {
	final Player playerX = Player.named("x");
	final Player playerO = Player.named("o");
	final private List<Player> ps = new ArrayList();

	final Supplier<List<Player>> defaultPlayersList = () -> {
		List<Player> ps = new ArrayList();
		ps.add(playerX);
		ps.add(playerO);
		return ps;
	};

	@Override
	public List<Player> getAllPlayers() {
		if(ps.isEmpty()) {
			ps.addAll(defaultPlayersList.get());
		}

		return ps;
	}
}