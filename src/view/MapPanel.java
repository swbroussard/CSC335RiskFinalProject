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
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import explosion.SpriteObject;
import explosion.*;
import model.*;

@SuppressWarnings({ "unused", "serial" })
public class MapPanel extends JPanel{
	private BufferedImage map;
	private ArrayList<Territory> territories;
	private RiskGUI gui;
	private List<Object> splosions;
	private Territory loosingTerritory;
	private Timer animTimer;
	private BufferedImage sheet;
	
	public MapPanel(RiskGUI gui) {
		super();
		this.gui = gui;
		territories = gui.getController().getTerritories();
		this.setPreferredSize(new Dimension(960, 640));
		this.setBackground(Color.RED);
		this.setVisible(true);
		splosions = new LinkedList<Object>();
		
		try {
			map = ImageIO.read(new File("images/RiskMap2.jpg"));
			sheet = ImageIO.read(new File("images/explosion-sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//manually add in the 14 different frames
		//splosions.add(sheet.getSubimage(col * width, row * height, width, height))
		
		this.addMouseListener(new MapListener());
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, 960, 640, null);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		//System.out.println(loosingTerritory + " exploded");
		if(loosingTerritory != null) {
			//System.out.println("explosion activated. BOOM!!!!");
			//createExplosion((int) loosingTerritory.getLabelPosition().getX(), (int) loosingTerritory.getLabelPosition().getY());
			//System.out.println(splosions.size() + " of sprite frames");
			//change the for loop to loop through the splosions with a sleeper delay
			//and draws the frame.
			for (int i = 0; 1 < splosions.size(); i++){
				
			}
			loosingTerritory = null;
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
				g2.drawString("" + t.getNumArmies(), (int) t.getLabelPosition().getX() + 2, (int) t.getLabelPosition().getY() + 15);
//			if(loosingTerritory != null) {
//				System.out.println("explosion activated. BOOM!!!!");
//				createExplosion(g2, (int) loosingTerritory.getLabelPosition().getX(), (int) loosingTerritory.getLabelPosition().getY());
//			}
		}
	}
	
	/**
	 * method to make explosion when a territory is conquerer by another player
	 * @param graphs, x, y
	 * @return none
	 */
//	public void createExplosion(int x, int y){
//		Explosion e = new Explosion(0, 0);
//		e.setPosition(x, y);
//		splosions.add(e);
//		e.start();
//		e.draw(g2);
//		for (SpriteObject explosion : splosions){
//			System.out.println("A sprite frame");
//			explosion.draw(g2);
//		}
//	}
	
//	public void setExplosion(Territory t) {
//		loosingTerritory = t;
//	}
	
	private class MapListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int rgb = map.getRGB(x, y);
			//System.out.println(rgb);
			for(Territory t : territories) {
				if(t.getColor() == rgb) {
//					gui.setLabel("You have selected " + t.getName());
					gui.territorySelected(t);
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}
	
	
	
}
