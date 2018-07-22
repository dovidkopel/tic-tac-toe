package com.dovidkopel.tictactoe.oop;

import com.dovidkopel.tictactoe.oop.board.TicTacToeBoard;
import com.dovidkopel.tictactoe.oop.game.SequentialTurnBasedGame;
import com.dovidkopel.tictactoe.oop.strategy.StrategyScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TicTacToe {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private StrategyScanner strategyScanner;

	private TicTacToeBoard board;

	private SequentialTurnBasedGame game;

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

	@Autowired
	public TicTacToe setGame(SequentialTurnBasedGame game) {
		this.game = game;
		return this;
	}
}
