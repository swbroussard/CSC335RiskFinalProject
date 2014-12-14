package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class SidePanel extends JPanel{
	private JButton doneAttacking, doneFortifying, exit;
	private RiskGUI gui;
	private BufferedImage star;
	
	public SidePanel(RiskGUI gui) {
		super();
		this.gui = gui;
		
		try {
			star = ImageIO.read(new File("images/star.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		this.setPreferredSize(new Dimension(200, 600));
		this.setVisible(true);
		
		doneAttacking = new JButton("Done Attacking");
		doneFortifying = new JButton("Done Fortifying");
		exit = new JButton("Exit Game");
		
		doneAttacking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doneAttacking();
			}
		});
		
		doneFortifying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doneFortifying();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		this.add(doneAttacking);
		this.add(doneFortifying);
		this.add(exit);
		repaint();
	}
	
	public void doneAttacking() {
		gui.getHumanPlayer().setDoneAttacking(true);
		gui.setLabel("Done Attacking!");
	}
	
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//update the text label
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		g2.drawString("Current Players:", 25, 150);
		int yPos = 170;
		for(Player p: gui.getPlayers()) {
			g2.setColor(p.getColor());
			g2.drawString(p.getName(), 41, yPos);
			yPos += 20;
		}
		
		
		switch(gui.getPlayers().indexOf(gui.getCurrentPlayer())) {
			case 0: // player 1
				g2.drawImage(star, 25, 156, 15, 15, null); 
				break;
			case 1: // player 2
				g2.drawImage(star, 25, 176, 15, 15, null); 
				break;
			case 2: // player 3
				g2.drawImage(star, 25, 196, 15, 15, null); 
				break;
			case 3: // player 4
				g2.drawImage(star, 25, 216, 15, 15, null); 
				break;
			case 4: // player 5
				g2.drawImage(star, 25, 236, 15, 15, null); 
				break;
			case 5: // player 6
				g2.drawImage(star, 25, 256, 15, 15, null); 
				break;
		}
		g2.setColor(Color.BLACK);
		g2.drawString("Continent Bonuses:", 25, 300);
		g2.drawString("North America - 5", 40, 320);
		g2.drawString("South America - 2", 40, 340);
		g2.drawString("Europe - 5", 40, 360);
		g2.drawString("Africa - 3", 40, 380);
		g2.drawString("Asia - 7", 40, 400);
		g2.drawString("Australia - 2", 40, 420);
	}
}
