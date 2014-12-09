package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SidePanel extends JPanel{
	private JButton doneAttacking, doneFortifying, exit;
	private RiskGUI gui;
	
	public SidePanel(RiskGUI gui) {
		super();
		this.gui = gui;
		
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
	}
	
	public void doneAttacking() {
		gui.getHumanPlayer().setDoneAttacking(true);
		gui.setLabel("Done Attacking!");
	}
	
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}
}
