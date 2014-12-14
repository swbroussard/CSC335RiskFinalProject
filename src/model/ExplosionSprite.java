package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ExplosionSprite extends Sprite {
  
  private static BufferedImage sheet;
  private static int width = 96, height = 96;
  private static int MAX_FRAMES = 14;
  private boolean isDead = false;

  public ExplosionSprite() {
    if (sheet == null)
      try {
        sheet = ImageIO.read(new File("images/explosion-sprite.png"));
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  @Override
  public Image getImage() {
    int row = frame / 5, col = frame % 5;
    if (frame == MAX_FRAMES) {
      isDead = true;
      return null;
    } else
      frame++;
    return sheet.getSubimage(col * width, row * height, width, height);
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public boolean isFinished() {
    return isDead;
  }

  public void reset() {
    isDead = false;
    super.frame = 0;
  }
}