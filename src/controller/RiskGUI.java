package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RiskGUI extends JFrame{
	private RiskController controller;
	private JFrame frame;
	private JPanel buttonPanel, mapPanel, labelPanel;
	
	
	public RiskGUI() {
		//controller = new RiskController();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1100, 800);
		frame.setTitle("RISK");
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		
		JPanel mapAndLabelPanel = new JPanel();
		mapAndLabelPanel.setLayout(new BorderLayout());
		
		setUpMapPanel();
		mapAndLabelPanel.add(mapPanel, BorderLayout.CENTER);
		
		setUpButtonSidePanel();
		basePanel.add(buttonPanel, BorderLayout.EAST);
		
		setUpLabelPanel();
		mapAndLabelPanel.add(labelPanel, BorderLayout.SOUTH);
		
		basePanel.add(mapAndLabelPanel, BorderLayout.CENTER);
		
		frame.add(basePanel);
		frame.setVisible(true);
	}
	
	public void setUpButtonSidePanel() {
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(200, 800));
		buttonPanel.setBackground(Color.GREEN);
		buttonPanel.setVisible(true);
	}
	
	public void setUpMapPanel() {
		mapPanel = new JPanel();
		mapPanel.setPreferredSize(new Dimension(300, 700));
		mapPanel.setBackground(Color.RED);
		mapPanel.setVisible(true);
	}
	
	public void setUpLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(900, 50));
		//labelPanel.setBackground(Color.BLUE);
		labelPanel.setVisible(true);
		
		JLabel label = new JLabel();
		label.setSize(900, 50);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setText("You have started a new game!");
		
		labelPanel.add(label);
	}
	
	public static void main(String[] args) {
		new RiskGUI();
	}
}
