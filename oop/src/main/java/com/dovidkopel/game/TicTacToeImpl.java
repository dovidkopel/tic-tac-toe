package com.dovidkopel.game;

import com.dovidkopel.game.board.BoardGame;
import com.dovidkopel.game.board.TicTacToeBoard;
import com.dovidkopel.game.player.PlayerSelector;
import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.strategy.StrategyScanner;
import com.dovidkopel.game.strategy.WinningStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class TicTacToeImpl extends TicTacToe {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private StrategyScanner strategyScanner;

	private PlayerSelector playerSelector;

	private TicTacToeBoard board;

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

	@Autowired
	public TicTacToeImpl setPlayerSelector(PlayerSelector playerSelector) {
		this.playerSelector = playerSelector;
		return this;
	}

	@Override
	public TicTacToeBoard getBoard() {
		return board;
	}

	@Override
	public void reset() {

	}

	@Override
	public Collection<Player> getAllPlayers() {
		return playerSelector.getAllPlayers();
	}

	@Override
	public Player getCurrentPlayer() {
		return playerSelector.getCurrentPlayer();
	}

	@Override
	public Player nextPlayer() {
		return playerSelector.nextPlayer();
	}

	@Override
	List<WinningStrategy<BoardGame>> getWinningStrategies() {
		return strategyScanner.getWinningStrategies();
	}
}
