package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.RiskController;

@SuppressWarnings("serial")
public class RiskGUI extends JFrame{
	private RiskController controller;
	private JFrame frame;
	private JPanel labelPanel;
	private MapPanel mapPanel;
	private SidePanel startingSidePanel;
	private JLabel label;
	
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
		
		mapPanel = new MapPanel(this);
	
		mapAndLabelPanel.add(mapPanel, BorderLayout.CENTER);
		
		startingSidePanel = new SidePanel(this);
		basePanel.add(startingSidePanel, BorderLayout.EAST);
		
		setUpLabelPanel();
		mapAndLabelPanel.add(labelPanel, BorderLayout.SOUTH);
		
		basePanel.add(mapAndLabelPanel, BorderLayout.CENTER);
		
		frame.add(basePanel);
		frame.setVisible(true);
	}
	
	public void setUpLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(960, 40));
		labelPanel.setVisible(true);
		
		label = new JLabel();
		label.setSize(900, 50);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setText("You have started a new game!");
		
		labelPanel.add(label);
	}
	
	public void setLabel(String message) {
		label.setText(message);
	}
	
	public RiskController getController() {
		return controller;
	}
	
	public static void main(String[] args) {
		new RiskGUI();
	}
	
}
