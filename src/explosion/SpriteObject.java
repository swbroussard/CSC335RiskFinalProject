package explosion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import explosion.Sprite.State;

public abstract class SpriteObject {
	public Point position;
	private Sprite sprite;
	private Image frame;
	private Timer t;
	
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
	
	public Sprite getSprite(){
		return sprite;
	}
	
	public void setPosition(int x, int y){
		position.setLocation(x, y);
	}
	
	public void draw(Graphics2D g){
		if (sprite != null && !sprite.isFinished())
			g.drawImage(frame, position.x  - sprite.getWidth()/2, position.y - sprite.getHeight()/2, null);
	}
	
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
	
	public void start(){
		if (!t.isRunning())
			t.start();
		sprite.reset();
	}
	
	public void stop(){
		t.stop();
	}
	
	public void nextFrame(){
		frame = sprite.getImage();
	}
	
	public boolean isFinished(){
		return sprite.isFinished();
	}
}
