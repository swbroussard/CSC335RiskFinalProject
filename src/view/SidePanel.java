package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SidePanel extends JPanel{
	private JButton attack, doneAttacking, doneFortifying, exit;
	private RiskGUI gui;
	
	public SidePanel(RiskGUI gui) {
		super();
		this.gui = gui;
		
		this.setPreferredSize(new Dimension(200, 600));
		//this.setBackground(Color.BLACK);
		this.setVisible(true);
		//this.setLayout(new GridLayout(4, 1));
		
		attack = new JButton("Attack");
		doneAttacking = new JButton("Done Attacking");
		doneFortifying = new JButton("Done Fortifying");
		exit = new JButton("Exit Game");
		
		attack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attack();
			}
		});
		
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
		
		this.add(new JPanel().add(attack));
		this.add(doneAttacking);
		this.add(doneFortifying);
		this.add(exit);
	}
	
	public void attack() {
		gui.setLabel("Attacking!");
	}
	
	public void doneAttacking() {
		gui.setLabel("Done Attacking!");
	}
	
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}
}
