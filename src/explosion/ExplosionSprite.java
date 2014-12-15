package explosion;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Represents the image portion of an Explosion. 
 * @author Gabe Kishi (Section Leaders?)
 */
public class ExplosionSprite extends Sprite {

	private static BufferedImage sheet;
	private static int width = 96, height = 96;
	private static int MAX_FRAMES = 14;
	private boolean isDead = false;

	/**
	 * Reads in the sprite sheet image
	 */
	public ExplosionSprite() {
		if (sheet == null)
			try {
				sheet = ImageIO.read(new File("images/explosion-sprite.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	/**
	 * Returns the current frame of the animation
	 * @return Image
	 */
	public Image getImage() {
		int row = frame / 5, col = frame % 5;
		if (frame == MAX_FRAMES) {
			isDead = true;
			return null;
		} else
			frame++;
		return sheet.getSubimage(col * width, row * height, width, height);
	}

	/**
	 * Getter for height
	 * @return int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Getter for width
	 * @return int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Getter for isDead
	 * @return boolean
	 */
	public boolean isFinished() {
		return isDead;
	}

	/**
	 * Resets the animation to the beginning
	 */
	public void reset() {
		isDead = false;
		super.frame = 0;
	}
}