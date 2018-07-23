package com.dovidkopel.tictactoe.oop.game.status;

public enum

GameStatus {
	// No operation
	NOOP,
	// Game exists but has not been started
	NOT_STARTED,
	// Event to start the game
	START_GAME,
	// Event to stop the game
	STOP_GAME,
	// Game has been started
	STARTED,
	// Game is currently active (not ABORTED, not NOT_STARTED)
	ACTIVE,
	// Game has started but is paused
	PAUSED,
	// Game has started and paused and is now resumed (not ABORTED)
	RESUMED,
	// Game has been aborted (not ACTIVE)
	ABORTED,

	// A turn is being created, the player for the turn has not been
	// selected yet
	TURN_PRE_SELECTION,
	// A turn has been created and its player has been selected
	// and the nature of the turn decided
	TURN_ACTIVE,
	// A player has just acted, their turn is being evaluated
	TURN_EVALUATING,
	// A players turns has been evaluated, right before the game
	// will get the player for the next turn
	TURN_POST_EVALUATION,

	// Waiting for the player to act for their turn
	TURN_WAITING_FOR_PLAYER,
	// Waiting for some sort of timer
	TURN_WAITING_FOR_TIMER,

	// A countdown has been reached
	COUNTDOWN_TIMER,
	// A duration of time has elapsed
	TIME_ELAPSED,

	// A player resigns
	RESIGNED,
	// The game is aborted, there is no winner
	DRAW,
	// A player(s) win
	WIN,
	// A player(s) lose
	LOSE

}
