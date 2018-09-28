package com.dovidkopel.game.player;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomTwoPlayerSelectorImpl extends TwoPlayerSelector {
	final private int upperBound = getAllPlayers().size();

	final private int starting;

	private int current;

	private Player player;

	public RandomTwoPlayerSelectorImpl(int starting) {
		this.starting = starting;
	}

	public RandomTwoPlayerSelectorImpl() {
		this.starting = ThreadLocalRandom.current().nextInt(0, upperBound);
	}

	@Override
	public void init() {
		reset();
	}

	@Override
	public void reset() {
		this.current = starting;
		this.player = getAllPlayers().get(current);
	}

	public int getUpperBound() {
		return upperBound;
	}

	public int getStarting() {
		return starting;
	}

	public int getCurrent() {
		return current;
	}

	@Override
	public Player getCurrentPlayer() {
		return player;
	}

	@Override
	public Player nextPlayer() {
		if(current == getAllPlayers().size()-1) {
			current = 0;
		} else {
			current++;
		}

		return getAllPlayers().get(current);
	}
}
