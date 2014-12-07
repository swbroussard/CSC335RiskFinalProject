package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import model.HumanPlayer;
import model.IntermediateAIPlayer;
import model.Player;
import model.SimpleAIPlayer;
import controller.RiskController;

@SuppressWarnings("serial")
public class RiskGUI extends JFrame{
	private RiskController controller;
	private JFrame frame;
	private JPanel labelPanel;
	private MapPanel mapPanel;
	private SidePanel startingSidePanel;
	private JLabel label;
	private ArrayList<Player> players;

	public RiskGUI() {
		super();
		players = new ArrayList<Player>();
		determineNumberOfPlayers();
		while(players.size() > 6 || players.size() < 2) {
			JOptionPane.showMessageDialog(null, "You must select between 2 and 6 players", "Error!",
					JOptionPane.ERROR_MESSAGE);
			players = new ArrayList<Player>();
			determineNumberOfPlayers();
		}
		switch(players.size()) {
		case 6:
			players.get(5).setColor(Color.BLUE);
		case 5:
			players.get(4).setColor(Color.GREEN);
		case 4:
			players.get(3).setColor(Color.MAGENTA);
		case 3:
			players.get(2).setColor(Color.ORANGE);
		case 2:
			players.get(1).setColor(Color.RED);
		case 1:
			players.get(0).setColor(Color.BLACK);
		}
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

	public void determineNumberOfPlayers() {
		JLabel instructions = new JLabel("Instructions: Select your players.  You must have between 2 and 6 players");
		JLabel simpleLabel = new JLabel("Select the number of Simple Players: ");
		JLabel intermediateLabel = new JLabel("Select the number of Intermediate Players: ");
		JLabel humanLabel = new JLabel("Do you want to play?");

		//simpleAI radio buttons
		JRadioButton simple0 = new JRadioButton("0");
		JRadioButton simple1 = new JRadioButton("1");
		JRadioButton simple2 = new JRadioButton("2");
		JRadioButton simple3 = new JRadioButton("3");
		JRadioButton simple4 = new JRadioButton("4");
		JRadioButton simple5 = new JRadioButton("5");

		simple0.setSelected(true);

		simple0.setActionCommand("S0");
		simple1.setActionCommand("S1");
		simple2.setActionCommand("S2");
		simple3.setActionCommand("S3");
		simple4.setActionCommand("S4");
		simple5.setActionCommand("S5");

		simple0.addActionListener(new RadioButtonListener());
		simple1.addActionListener(new RadioButtonListener());
		simple2.addActionListener(new RadioButtonListener());
		simple3.addActionListener(new RadioButtonListener());
		simple4.addActionListener(new RadioButtonListener());
		simple5.addActionListener(new RadioButtonListener());

		ButtonGroup simpleGroup = new ButtonGroup(); //only allows one radio button to be selected
		simpleGroup.add(simple0);
		simpleGroup.add(simple1);
		simpleGroup.add(simple2);
		simpleGroup.add(simple3);
		simpleGroup.add(simple4);
		simpleGroup.add(simple5);

		//Intermediate radio buttons
		JRadioButton intermediate0 = new JRadioButton("0");
		JRadioButton intermediate1 = new JRadioButton("1");
		JRadioButton intermediate2 = new JRadioButton("2");
		JRadioButton intermediate3 = new JRadioButton("3");
		JRadioButton intermediate4 = new JRadioButton("4");
		JRadioButton intermediate5 = new JRadioButton("5");

		intermediate0.setSelected(true);

		intermediate0.setActionCommand("I0");
		intermediate1.setActionCommand("I1");
		intermediate2.setActionCommand("I2");
		intermediate3.setActionCommand("I3");
		intermediate4.setActionCommand("I4");
		intermediate5.setActionCommand("I5");

		intermediate0.addActionListener(new RadioButtonListener());
		intermediate1.addActionListener(new RadioButtonListener());
		intermediate2.addActionListener(new RadioButtonListener());
		intermediate3.addActionListener(new RadioButtonListener());
		intermediate4.addActionListener(new RadioButtonListener());
		intermediate5.addActionListener(new RadioButtonListener());

		ButtonGroup intermediateGroup = new ButtonGroup(); //only allows one radio button to be selected
		intermediateGroup.add(intermediate0);
		intermediateGroup.add(intermediate1);
		intermediateGroup.add(intermediate2);
		intermediateGroup.add(intermediate3);
		intermediateGroup.add(intermediate4);
		intermediateGroup.add(intermediate5);

		//Add human radio buttons (yes/no)
		JRadioButton humanYes = new JRadioButton("Yes");
		JRadioButton humanNo = new JRadioButton("No");

		humanYes.setSelected(true);

		humanYes.setActionCommand("YES");
		humanNo.setActionCommand("NO");

		humanYes.addActionListener(new RadioButtonListener());
		humanNo.addActionListener(new RadioButtonListener());

		ButtonGroup humanGroup = new ButtonGroup();
		humanGroup.add(humanYes);
		humanGroup.add(humanNo);

		//Add everything to the JOptionPane message
		Object[] message = {instructions, simpleLabel, simple0, simple1, simple2, simple3, simple4,
				simple5, intermediateLabel, intermediate0, intermediate1, intermediate2, intermediate3,
				intermediate4, intermediate5, humanLabel, humanYes, humanNo};
		//Create the JOptionPane
		int option = JOptionPane.showConfirmDialog(null, message, "Select your opponents",
				JOptionPane.OK_CANCEL_OPTION);


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

	private class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "S6" : 
				players.add(new SimpleAIPlayer("Simple Player 6"));
			case "S5" : 
				players.add(new SimpleAIPlayer("Simple Player 5"));
			case "S4" : 
				players.add(new SimpleAIPlayer("Simple Player 4"));
			case "S3" :
				players.add(new SimpleAIPlayer("Simple Player 3"));
			case "S2":
				players.add(new SimpleAIPlayer("Simple Player 2"));
			case "S1":
				players.add(new SimpleAIPlayer("Simple Player 1"));
				break;
			case "I6" : 
				players.add(new IntermediateAIPlayer("Intermediate Player 6"));
			case "I5" : 
				players.add(new IntermediateAIPlayer("Intermediate Player 5"));
			case "I4" : 
				players.add(new IntermediateAIPlayer("Intermediate Player 4"));
			case "I3" :
				players.add(new IntermediateAIPlayer("Intermediate Player 3"));
			case "I2":
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
			case "I1":
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
				break;
			case "YES":
				players.add(new HumanPlayer("Human"));
				break;
			default:
				break;
			
				
				
			}
			//canvas.setColor(colorChooser.getColor());
			//canvas.setVisible(true);
		}
	}



}
