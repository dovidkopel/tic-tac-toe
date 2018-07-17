package com.dovidkopel.tictactoe.oop;

import java.io.Serializable;

public interface Position extends Serializable {
	boolean isEmpty();

	boolean isOccupied();

	<T> T getOccupant();

	<T> void setOccupant(T occupant);

	void clearOccupant();
}
