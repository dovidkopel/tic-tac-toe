package com.dovidkopel.tictactoe.oop.player;

import java.io.Serializable;
import java.util.UUID;

public interface Player extends Serializable {
	UUID getId();

	String getLabel();

	static Player of(UUID id, String label) {
		return new Player() {

			@Override
			public UUID getId() {
				return id;
			}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public int hashCode() {
				return id.hashCode() + label.hashCode();
			}

			@Override
			public boolean equals(Object obj) {
				return hashCode() == obj.hashCode();
			}

			@Override
			public String toString() {
				return "Player "+getLabel()+" ("+getId()+")";
			}
		};
	}

	static Player named(String label) {
		final UUID id = UUID.randomUUID();
		return new Player() {

			@Override
			public UUID getId() {
				return id;
			}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public int hashCode() {
				return id.hashCode() + label.hashCode();
			}

			@Override
			public boolean equals(Object obj) {
				return hashCode() == obj.hashCode();
			}

			@Override
			public String toString() {
				return "Player "+getLabel()+" ("+getId()+")";
			}
		};
	}
}
