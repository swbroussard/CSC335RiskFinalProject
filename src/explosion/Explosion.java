package explosion;


public class Explosion extends SpriteObject{
	
	public Explosion(int x, int y){
		super(new ExplosionSprite(), x, y, 50);
	}
}