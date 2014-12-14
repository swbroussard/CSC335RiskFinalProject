package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		this.add(getLabel());
		this.add(doneAttacking);
		this.add(doneFortifying);
		this.add(exit);
	}
	
	public void updateLabel() {
		setLabel(new JLabel());
		String text = new String("<html>Current Players: ");
		for (int i = 0; i < gui.getPlayers().size(); i++) {
			Player p = gui.getPlayers().get(i);
			switch(i){
				case 0: // cyan
					text += String.format("<br>%s<font color=#00FFFF>%s</font>","",p.getName()); break;
				case 1: // green
					text += String.format("<br>%s<font color=#00FF00>%s</font>","",p.getName()); break;
				case 2: // orange
					text += String.format("<br>%s<font color=#FF7700>%s</font>","",p.getName()); break;
				case 3: // magenta
					text += String.format("<br>%s<font color=#FF00FF>%s</font>","",p.getName()); break;
				case 4: // yellow
					text += String.format("<br>%s<font color=#FFD700>%s</font>","",p.getName()); break;
				case 5: // purple
					text += String.format("<br>%s<font color=#8C4AFF>%s</font>","",p.getName()); break;
			}
		}
		text += "<br><br>Continent Bonuses:<br>North America - 5<br>South America - 2"
				+ "<br>Europe - 5<br>Africa - 3<br>Asia - 7<br>Australia - 2</html>";
		getLabel().setText(text);
	}
	
	public void doneAttacking() {
		gui.getHumanPlayer().setDoneAttacking(true);
		gui.setLabel("Done Attacking!");
	}
	
	public void doneFortifying() {
		gui.setLabel("Done Fortifying");
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
}
