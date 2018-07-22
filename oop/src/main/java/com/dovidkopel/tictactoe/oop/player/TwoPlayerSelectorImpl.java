package com.dovidkopel.tictactoe.oop.player;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TwoPlayerSelectorImpl extends TwoPlayerSelector {
	final private int upperBound = getAllPlayers().size();

	final private int starting;

	private int current;

	private Player player;

	public TwoPlayerSelectorImpl(int starting) {
		this.starting = starting;
		reset();
	}

	public TwoPlayerSelectorImpl() {
		this.starting = ThreadLocalRandom.current().nextInt(0, upperBound);
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
