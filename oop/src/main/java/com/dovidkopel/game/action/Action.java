package com.dovidkopel.game.action;

import java.io.Serializable;

// A given action may have input parameters
// it is up to the `Action` to confer with the `Game`
// and the `Board` to determine whether or not
// the `Action` is valid or not
public interface Action extends Serializable {
	Boolean isValid();
}
