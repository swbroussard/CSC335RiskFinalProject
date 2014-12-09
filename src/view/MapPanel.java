package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

@SuppressWarnings({ "unused", "serial" })
public class MapPanel extends JPanel{
	private BufferedImage map;
	private ArrayList<Territory> territories;
	private RiskGUI gui;
	
	public MapPanel(RiskGUI gui) {
		super();
		this.gui = gui;
		territories = gui.getController().getTerritories();
		this.setPreferredSize(new Dimension(960, 640));
		this.setBackground(Color.RED);
		this.setVisible(true);
		
		try {
			map = ImageIO.read(new File("images/RiskMap2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.addMouseListener(new MapListener());
		repaint();
		//mapPanel.add(map, BorderLayout.CENTER);
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(map, 0, 0, 960, 640, null);

		g2.setFont(new Font("Arial", Font.BOLD, 15));
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
		}
	}
	
	private class MapListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int rgb = map.getRGB(x, y);
			//System.out.println(rgb);
			for(Territory t : territories) {
				if(t.getColor() == rgb) {
					gui.setLabel("You have selected " + t.getName());
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
