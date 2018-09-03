package com.dovidkopel.game;

import com.dovidkopel.game.player.Player;

import java.util.Map;

public interface ScoreHistory {
	Map<Player, Integer> getScores();
}
