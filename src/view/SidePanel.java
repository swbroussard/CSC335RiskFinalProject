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
import javax.swing.JPanel;

import model.Player;

/**
 * The side panel in the GUI. Displays the players in the game, designating the player
 * whose turn it is with a star by their name. Has buttons for the human player to end
 * their turn or end the game. Lists army bonuses for owning a complete continent. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 *
 */
@SuppressWarnings("serial")
public class SidePanel extends JPanel{
	private JButton doneAttacking, exit;
	private RiskGUI gui;
	private BufferedImage star;
	
	/**
	 * Constructs a new SidePanel object. Sets up all buttons and images. Takes a RiskGUI
	 * argument so that information about territories' owners can be passed easily to the SidePanel. 
	 * @param gui
	 */
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
		exit = new JButton("Exit Game");
		
		doneAttacking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doneAttacking();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		this.add(doneAttacking);
		this.add(exit);
		repaint();
	}
	
	/**
	 * Called when the doneAttacking button is pressed. Displays the message, 
	 * "Done Attacking!" in the RiskGUI and sets the human player's doneAttacking
	 * variable to true. 
	 */
	public void doneAttacking() {
		gui.getHumanPlayer().setDoneAttacking(true);
		gui.setLabel("Done Attacking!");
	}
	
	/**
	 * Called when the doneFortifying button is pressed. Displays the message,
	 * "Done Fortifying" in the RiskGUI. 
	 */
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}

	/**
	 * Used whenever repaint is called. Updates the text label with the list of players
	 * and moves the star depending on whose turn it is. 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//update the text label
		g2.setFont(new Font("Arial", Font.BOLD, 14));
		int yPos = 80;
		g2.drawString("Current Players:", 25, yPos);
		yPos += 20;
		for(Player p: gui.getPlayers()) {
			g2.setColor(p.getColor());
			g2.drawString(p.getName(), 41, yPos);
			yPos += 20;
		}
		yPos = 86;
		
		switch(gui.getPlayers().indexOf(gui.getCurrentPlayer())) {
			case 0: // player 1
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
			case 1: // player 2
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
			case 2: // player 3
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
			case 3: // player 4
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
			case 4: // player 5
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
			case 5: // player 6
				g2.drawImage(star, 25, yPos, 15, 15, null); 
				yPos += 20;
				break;
		}
		yPos += 40;
		g2.setColor(Color.BLACK);
		g2.drawString("Continent Bonuses:", 25, yPos);
		yPos += 20;
		g2.drawString("North America - 5", 40, yPos);
		yPos += 20;
		g2.drawString("South America - 2", 40, yPos);
		yPos += 20;
		g2.drawString("Europe - 5", 40, yPos);
		yPos += 20;
		g2.drawString("Africa - 3", 40, yPos);
		yPos += 20;
		g2.drawString("Asia - 7", 40, yPos);
		yPos += 20;
		g2.drawString("Australia - 2", 40, yPos);
		yPos += 40;
		
		g2.drawString("If you do not want to", 10, yPos);
		yPos += 15;
		g2.drawString("reinforce at the end of", 10, yPos);
		yPos += 15;
		g2.drawString("your turn, select a to ", 10, yPos);
		yPos += 15;
		g2.drawString("and from territory, then", 10, yPos);
		yPos += 15;
		g2.drawString("enter 0 for the number of armies", 10, yPos);
	}
}
