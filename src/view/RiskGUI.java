package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.RiskController;

public class RiskGUI extends JFrame{
	private RiskController controller;
	private JFrame frame;
	private JPanel buttonPanel, labelPanel;
	private MapPanel mapPanel;
	
	public RiskGUI() {
		super();
		controller = new RiskController();
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1160, 680);
		frame.setTitle("RISK");
		
		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());
		
		JPanel mapAndLabelPanel = new JPanel();
		mapAndLabelPanel.setLayout(new BorderLayout());
		
		mapPanel = new MapPanel(controller.getTerritories());
	
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
		buttonPanel.setPreferredSize(new Dimension(200, 600));
		buttonPanel.setBackground(Color.GREEN);
		buttonPanel.setVisible(true);
	}
	
	public void setUpLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(960, 40));
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
