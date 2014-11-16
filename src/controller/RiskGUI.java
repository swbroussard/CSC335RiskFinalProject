package controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RiskGUI extends JFrame{
	private RiskController controller;
	private JFrame frame;
	
	public RiskGUI() {
		controller = new RiskController();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setTitle("RISK");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		
		panel2.add(panel3, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new RiskGUI();
	}
}
