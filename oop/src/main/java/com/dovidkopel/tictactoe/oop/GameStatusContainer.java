package com.dovidkopel.tictactoe.oop;

import java.util.Collection;

public interface GameStatusContainer<T> {
	GameStatus getStatus();

	T getDetails();
}
