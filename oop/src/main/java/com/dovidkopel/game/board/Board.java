package com.dovidkopel.game.board;

import com.dovidkopel.game.position.Position;

import java.io.Serializable;
import java.util.Collection;

public interface Board extends Serializable {
	<T extends Position> Collection<T> getAllPositions();
}
