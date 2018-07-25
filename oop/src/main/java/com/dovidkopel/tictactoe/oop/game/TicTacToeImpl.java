package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.game.status.GameEventImpl;
import com.dovidkopel.tictactoe.oop.game.status.GameStatusE;
import com.dovidkopel.game.turn.Turn;
import com.dovidkopel.game.turn.TurnImpl;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.tictactoe.oop.strategy.StrategyScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeImpl extends TicTacToe {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private StrategyScanner strategyScanner;

	private TicTacToeBoard board;

	private Turn currentTurn;

	@Autowired
	public TicTacToeImpl setStrategyScanner(StrategyScanner strategyScanner) {
		this.strategyScanner = strategyScanner;
		return this;
	}

	@Autowired
	public TicTacToeImpl setBoard(TicTacToeBoard board) {
		this.board = board;
		return this;
	}

	@Override
	public TicTacToeBoard getBoard() {
		return board;
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


}
