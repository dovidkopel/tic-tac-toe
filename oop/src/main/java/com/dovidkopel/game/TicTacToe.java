package com.dovidkopel.game;

import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.turn.TurnImpl;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.tictactoe.oop.strategy.WinningStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class TicTacToe extends SequentialTurnBasedBoardGameImpl<TicTacToeBoard> {
	private List<WinningStrategy> strategies = new ArrayList();

	private List<Turn> turns = new CopyOnWriteArrayList();

	private Turn currentTurn;

	@Autowired
	public TicTacToe setStrategies(List<WinningStrategy> strategies) {
		this.strategies = strategies;
		return this;
	}

	@Override
	public Turn getNextTurn() {
		// Invoke a TurnPreSelection event
		evaluate(
			new GameEventImpl(
				GameStatusE.TURN_PRE_SELECTION,
				this
			)
		);

		Player np = playerSelector.nextPlayer();
		Turn nt = new TurnImpl(np);

		evaluate(
			new GameEventImpl(
				GameStatusE.TURN_ACTIVE,
				nt,
				this
			)
		);

		currentTurn = nt;
		turns.add(currentTurn);
		return currentTurn;
	}

	@Override
	public synchronized Collection<GameEvent> completeTurn(Turn turn, Action action) {
		Set<GameEvent> statuses = new HashSet();

		if(turns.contains(turn)) {
			// Set Action to turn
			turns.remove(turn);
			Turn t = new TurnImpl(turn, action);

			evaluate(
				new GameEventImpl(
					GameStatusE.TURN_EVALUATING,
					t,
					this
				)
			);

			// Now we want to evaluate the turn
			// what happened if anything...
			strategies.stream().map(s -> s.evaluateGame(getBoard()));

			turns.add(t);
		}
		return statuses;
	}
}
