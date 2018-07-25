package com.dovidkopel.game.position;

import java.util.Objects;
import java.util.Optional;

public class TwoDimensionalPosition implements Position {
	final private Number x;

	final private Number y;

	private Optional<Object> occupant = Optional.empty();

	public TwoDimensionalPosition(Number x, Number y) {
		this.x = x;
		this.y = y;
	}

	public Number getX() {
		return x;
	}

	public Number getY() {
		return y;
	}

	@Override
	public boolean isEmpty() {
		return !occupant.isPresent();
	}

	@Override
	public boolean isOccupied() {
		return occupant.isPresent();
	}

	@Override
	public <T> T getOccupant() {
		return (T) occupant.get();
	}

	@Override
	public <T> void setOccupant(T occupant) {
		this.occupant = Optional.of(occupant);
	}

	@Override
	public void clearOccupant() {
		this.occupant = Optional.empty();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TwoDimensionalPosition that = (TwoDimensionalPosition) o;
		return Objects.equals(x, that.x) &&
			Objects.equals(y, that.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		if(isOccupied()) {
			return String.format(
				"(%s, %s): %s",
				getX().toString(),
				getY().toString(),
				occupant.get().toString()
			);
		} else {
			return String.format(
				"(%s, %s)",
				getX().toString(),
				getY().toString()
			);
		}
	}
}
