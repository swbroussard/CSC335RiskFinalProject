package explosion;

/**
 * Represents an Explosion object with a 50-millisecond delay between frames. 
 * @author Gabe Kishi (Section Leaders?)
 */
public class Explosion extends SpriteObject{
	
	public Explosion(int x, int y){
		super(new ExplosionSprite(), x, y, 50);
	}
}