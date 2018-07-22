package com.dovidkopel.tictactoe.oop.game;

public interface GameStatusContainer<T> {
	GameStatus getStatus();

	T getDetails();
}