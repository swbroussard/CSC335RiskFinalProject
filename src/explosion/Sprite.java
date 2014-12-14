package explosion;

import java.awt.Image;

/**
 * Abstract class representing the image portion of a sprite.
 * @author Gabe Kishi (Section Leaders?)
 */
public abstract class Sprite {
	/**
	 * Enum for the states of a frog sprite
	 */
	public static enum State {
		IDLE, MOVING_LEFT, MOVING_RIGHT, MOVING_DOWN, MOVING_UP
	};

	protected State state;
	protected int frame;

	/**
	 * constructor
	 */
	public Sprite() {
		state = State.IDLE;
		frame = 0;
	}

	/**
	 * setter for state
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * getter for state
	 * @return
	 */
	public State getState() {
		return state;
	}

	/**
	 * By default, all sprites never finish
	 * @return false
	 */
	public boolean isFinished() {
		return false;
	}

	/**
	 * By default, reset does nothing
	 */
	public void reset() {}

	public abstract Image getImage();

	public abstract int getHeight();

	public abstract int getWidth();
}
