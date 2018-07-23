package com.dovidkopel.tictactoe.oop;

import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.board.TwoDimensionalTicTacToeBoard;
import com.dovidkopel.tictactoe.oop.game.SequentialTurnBasedBoardGame;
import com.dovidkopel.tictactoe.oop.game.SequentialTurnBasedBoardGameImpl;
import com.dovidkopel.tictactoe.oop.strategy.StrategyScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TicTacToe extends SequentialTurnBasedBoardGameImpl<TicTacToeBoard> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private StrategyScanner strategyScanner;

	private TicTacToeBoard board;

	@Autowired
	public TicTacToe setStrategyScanner(StrategyScanner strategyScanner) {
		this.strategyScanner = strategyScanner;
		return this;
	}

	@Autowired
	public TicTacToe setBoard(TicTacToeBoard board) {
		this.board = board;
		return this;
	}

	@Override
	public TicTacToeBoard getBoard() {
		return board;
	}
}
