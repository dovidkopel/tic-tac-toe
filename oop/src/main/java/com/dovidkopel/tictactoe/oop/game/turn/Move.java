package com.dovidkopel.tictactoe.oop.game.turn;

import com.dovidkopel.tictactoe.oop.player.Player;
import com.dovidkopel.tictactoe.oop.position.Position;

import java.io.Serializable;
import java.util.UUID;

public interface Move<T extends Move> extends Action<T> {
	UUID getId();

	Position getPosition();

	Player getPlayer();
}