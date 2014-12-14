package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Timer;

import explosion.*;
import model.*;

/**
 * The map portion of the GUI. Displays the world map, complete with labels for each territory, 
 * and updates with numbers of armies and explosions as appropriate. Repaints every 15 milliseconds. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 *
 */
@SuppressWarnings({ "unused", "serial" })
public class MapPanel extends JPanel{
	private BufferedImage map;
	private ArrayList<Territory> territories;
	private RiskGUI gui;
	private List<SpriteObject> splosions;
	//private Territory loosingTerritory;
	private Timer animTimer;
	private BufferedImage sheet;
	
	/**
	 * Constructs a new MapPanel object. Takes a RiskGUI argument so that information 
	 * about territories' owners can be passed easily to the MapPanel. 
	 * @param gui
	 */
	public MapPanel(RiskGUI gui) {
		super();
		this.gui = gui;
		territories = gui.getController().getTerritories();
		this.setPreferredSize(new Dimension(960, 640));
		this.setBackground(Color.RED);
		this.setVisible(true);
		splosions = new LinkedList<SpriteObject>();
		
		try {
			map = ImageIO.read(new File("images/RiskMap2.jpg"));
			sheet = ImageIO.read(new File("images/explosion-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animTimer = new Timer();
		animTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, 15);
		this.addMouseListener(new MapListener());
		
		repaint();
	}
	
	/**
	 * Called by repaint whenever the map is updated. 
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, 960, 640, null);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		for (int i = 0; i < getSplosions().size(); i++) {
			SpriteObject explosion = getSplosions().get(i);
			explosion.draw(g2);
		}
		for(Territory t: territories) {
			if(t.getCurrentOwner() == null) {
				g2.setColor(Color.WHITE);
			}
			else {
				g2.setColor(t.getCurrentOwner().getColor());
			}
			g2.fillOval((int) t.getLabelPosition().getX(), (int) t.getLabelPosition().getY(), 21, 21);
			
			g2.setColor(Color.BLACK);
			if(t.getNumArmies() < 10)
				g2.drawString("" + t.getNumArmies(), (int) t.getLabelPosition().getX() + 7, (int) t.getLabelPosition().getY() + 16);
			else//armies is double digit number
				g2.drawString("" + t.getNumArmies(), (int) t.getLabelPosition().getX() + 3, (int) t.getLabelPosition().getY() + 16);
		}
	}
	
	/**
	 * getter for splosions, an instance variable used by the explosion animations
	 * @return
	 */
	public List<SpriteObject> getSplosions() {
		return splosions;
	}

	/**
	 * setter for splosions
	 * @param splosions
	 */
	public void setSplosions(List<SpriteObject> splosions) {
		this.splosions = splosions;
	}
	
	/**
	 * private inner class to listen for clicks on the map. Detects the territory
	 * by the color of the point where the mouse was clicked. 
	 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
	 *
	 */
	private class MapListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int rgb = map.getRGB(x, y);
			for(Territory t : territories) {
				if(t.getColor() == rgb) {
					gui.territorySelected(t);
				}
			}
		}
		
		/**
		 * Unimplemented MouseListener methods
		 */
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
}
