package model;

import java.awt.Image;

public abstract class Sprite {
  public static enum State {
    IDLE, MOVING_LEFT, MOVING_RIGHT, MOVING_DOWN, MOVING_UP
  };

  protected State state;
  protected int frame;

  public Sprite() {
    state = State.IDLE;
    frame = 0;
  }

  public void setState(State state) {
    this.state = state;
  }

  public State getState() {
    return state;
  }

  // by default, all sprites never finish
  public boolean isFinished() {
    return false;
  }

  // by default, reset does nothing
  public void reset() {
  }

  // Abstract methods, vary from sprite to sprite
  public abstract Image getImage();

  public abstract int getHeight();

  public abstract int getWidth();
}
