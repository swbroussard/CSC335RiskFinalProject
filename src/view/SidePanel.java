package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Player;

@SuppressWarnings("serial")
public class SidePanel extends JPanel{
	private JButton doneAttacking, doneFortifying, exit;
	private RiskGUI gui;
	private JLabel label;
	
	public SidePanel(RiskGUI gui) {
		super();
		this.gui = gui;
		
		updateLabel();
		
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
		
		this.add(label);
		this.add(doneAttacking);
		this.add(doneFortifying);
		this.add(exit);
	}
	
	public void updateLabel() {
		label = new JLabel();
		String text = new String("<html>Current Players: ");
		for(Player p: gui.getPlayers()) {
			text += "<br>\t " + p.getName();
		}
		//text += "<br><br>" + gui.getCurrentPlayer().getName() + "\'s turn.";
		
		text += "<br><br>Continent Bonuses:<br>North America - 5<br>South America - 2"
				+ "<br>Europe - 5<br>Africa - 3<br>Asia - 7<br>Australia - 2";
		label.setText(text);
	}
	
	public void doneAttacking() {
		gui.getHumanPlayer().setDoneAttacking(true);
		gui.setLabel("Done Attacking!");
	}
	
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}
}
