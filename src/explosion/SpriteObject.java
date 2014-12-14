package explosion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Abstract class representing a sprite animation
 * @author Gabe Kishi (Section Leaders?)
 */
public abstract class SpriteObject {
	public Point position;
	private Sprite sprite;
	private Image frame;
	private Timer t;
	
	/**
	 * constructs a new SpriteObject with a particular delay and position
	 * @param sprite
	 * @param x
	 * @param y
	 * @param delay
	 */
	protected SpriteObject(Sprite sprite, int x, int y, int delay){
		this.sprite = sprite;
		this.position = new Point(x, y);
		frame = sprite.getImage();
		
		t = new Timer(delay, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextFrame();
			}
		});
	}
	
	/**
	 * getter for Sprite
	 * @return
	 */
	public Sprite getSprite(){
		return sprite;
	}
	
	/**
	 * setter for position
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		position.setLocation(x, y);
	}
	
	/**
	 * draws sprite at the correct position
	 * @param g
	 */
	public void draw(Graphics2D g){
		if (sprite != null && !sprite.isFinished())
			g.drawImage(frame, position.x  - sprite.getWidth()/2, position.y - sprite.getHeight()/2, null);
	}
	
	/**
	 * Frog methods moveLeft, moveRight, moveUp, moveDown, moveStop
	 */
	public void moveLeft(){
	//	if (sprite.getState() != Sprite.State.MOVING_LEFT){
			sprite.setState(Sprite.State.MOVING_LEFT);
			nextFrame();
	//	}	
		position.translate(-5, 0);
	}
	
	public void moveRight(){
	//	if (sprite.getState() != Sprite.State.MOVING_RIGHT){
			sprite.setState(Sprite.State.MOVING_RIGHT);
			nextFrame();
	//	}
		position.translate(5, 0);
	}
	
	public void moveUp(){
///		if (sprite.getState() != Sprite.State.MOVING_UP){
			sprite.setState(Sprite.State.MOVING_UP);
			nextFrame();
//		}
		
		position.translate(0, -5);
	}
	
	public void moveDown(){
	//	if (sprite.getState() != Sprite.State.MOVING_DOWN){
			sprite.setState(Sprite.State.MOVING_DOWN);
			nextFrame();
//		}
		
		position.translate(0, 5);
	}
	
	public void moveStop(){
	//	if (sprite.getState() != Sprite.State.IDLE){
			sprite.setState(Sprite.State.IDLE);
			nextFrame();
//		}
	}
	
	/**
	 * Starts the sprite if it isn't running
	 */
	public void start(){
		if (!t.isRunning())
			t.start();
		sprite.reset();
	}
	
	/**
	 * Stops the sprite (but you already knew that)
	 */
	public void stop(){
		t.stop();
	}
	
	/**
	 * Gets the next frame of the animation from the sprite
	 */
	public void nextFrame(){
		frame = sprite.getImage();
	}
	
	/**
	 * getter for isFinished
	 * @return
	 */
	public boolean isFinished(){
		return sprite.isFinished();
	}
}
