package com.dovidkopel.tictactoe.oop.game.turn;

import com.dovidkopel.game.player.Player;
import com.dovidkopel.game.position.Position;

import java.util.UUID;

public interface Move<T extends Move> extends Action<T> {
	UUID getId();

	Position getPosition();

	Player getPlayer();
}