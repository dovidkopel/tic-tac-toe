package com.dovidkopel.tictactoe.oop.game;

import com.dovidkopel.tictactoe.oop.player.Player;

import java.util.Map;

public interface ScoreHistory {
	Map<Player, Integer> getScores();
}
