package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import model.HumanPlayer;
import model.IntermediateAIPlayer;
import model.ObserverMessages;
import model.Player;
import model.SimpleAIPlayer;
import model.Territory;
import controller.RiskController;

@SuppressWarnings("serial")
public class RiskGUI extends JFrame implements Observer{
	private RiskController controller;
	private JFrame frame;
	private JPanel labelPanel;
	private MapPanel mapPanel;
	private SidePanel sidePanel;
	private JLabel label;
	private ArrayList<Player> players;
	private HumanPlayer human;
	private TypeOfPlay typeOfPlay;

	public RiskGUI() {
		super();	
		setUpPlayers();

		typeOfPlay = TypeOfPlay.DO_NOTHING;
		
		controller = new RiskController();
		controller.addObserver(this);
		controller.setPlayers(players);
		controller.sendTerritoriesToPlayers();


		setUpFrame();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		controller.populateBoard();
		controller.playGame();
	}

	public void setUpFrame() {
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

		sidePanel = new SidePanel(this);
		basePanel.add(sidePanel, BorderLayout.EAST);

		setUpLabelPanel();
		mapAndLabelPanel.add(labelPanel, BorderLayout.SOUTH);

		basePanel.add(mapAndLabelPanel, BorderLayout.CENTER);

		frame.add(basePanel);
		frame.setVisible(true);
	}

	public void setUpPlayers() {
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
			players.get(5).setColor(new Color(140, 74, 255));
			players.get(5).addObserver(this);
		case 5:
			players.get(4).setColor(Color.YELLOW);
			players.get(4).addObserver(this);
		case 4:
			players.get(3).setColor(Color.MAGENTA);
			players.get(3).addObserver(this);
		case 3:
			players.get(2).setColor(new Color(255, 114, 0));//orange
			players.get(2).addObserver(this);
		case 2:
			players.get(1).setColor(Color.GREEN);
			players.get(1).addObserver(this);
		case 1:
			players.get(0).setColor(Color.CYAN);
			players.get(0).addObserver(this);
		}

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
		JRadioButton simple6 = new JRadioButton("6");

		simple1.setSelected(true);

		ButtonGroup simpleGroup = new ButtonGroup(); //only allows one radio button to be selected
		simpleGroup.add(simple0);
		simpleGroup.add(simple1);
		simpleGroup.add(simple2);
		simpleGroup.add(simple3);
		simpleGroup.add(simple4);
		simpleGroup.add(simple5);
		simpleGroup.add(simple6);

		//Intermediate radio buttons
		JRadioButton intermediate0 = new JRadioButton("0");
		JRadioButton intermediate1 = new JRadioButton("1");
		JRadioButton intermediate2 = new JRadioButton("2");
		JRadioButton intermediate3 = new JRadioButton("3");
		JRadioButton intermediate4 = new JRadioButton("4");
		JRadioButton intermediate5 = new JRadioButton("5");
		JRadioButton intermediate6 = new JRadioButton("6");

		intermediate1.setSelected(true);

		ButtonGroup intermediateGroup = new ButtonGroup(); //only allows one radio button to be selected
		intermediateGroup.add(intermediate0);
		intermediateGroup.add(intermediate1);
		intermediateGroup.add(intermediate2);
		intermediateGroup.add(intermediate3);
		intermediateGroup.add(intermediate4);
		intermediateGroup.add(intermediate5);
		intermediateGroup.add(intermediate6);

		//Add human radio buttons (yes/no)
		JRadioButton humanYes = new JRadioButton("Yes");
		JRadioButton humanNo = new JRadioButton("No");

		humanNo.setSelected(true);
		
		ButtonGroup humanGroup = new ButtonGroup();
		humanGroup.add(humanYes);
		humanGroup.add(humanNo);

		//Add everything to the JOptionPane message
		Object[] message = {instructions, simpleLabel, simple0, simple1, simple2, simple3, simple4,
				simple5, simple6, intermediateLabel, intermediate0, intermediate1, intermediate2, intermediate3,
				intermediate4, intermediate5, intermediate6, humanLabel, humanYes, humanNo};

		//Create the JOptionPane
		int option = JOptionPane.showConfirmDialog(null, message, "Select your opponents",
				JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
		else {
			if(simple6.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 6"));		
				players.add(new SimpleAIPlayer("Simple Player 5"));	
				players.add(new SimpleAIPlayer("Simple Player 4"));		
				players.add(new SimpleAIPlayer("Simple Player 3"));	
				players.add(new SimpleAIPlayer("Simple Player 2"));
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else if(simple5.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 5"));	
				players.add(new SimpleAIPlayer("Simple Player 4"));		
				players.add(new SimpleAIPlayer("Simple Player 3"));	
				players.add(new SimpleAIPlayer("Simple Player 2"));
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else if(simple4.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 4"));		
				players.add(new SimpleAIPlayer("Simple Player 3"));	
				players.add(new SimpleAIPlayer("Simple Player 2"));
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else if(simple3.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 3"));	
				players.add(new SimpleAIPlayer("Simple Player 2"));
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else if(simple2.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 2"));
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else if(simple1.getSelectedObjects() != null) {
				players.add(new SimpleAIPlayer("Simple Player 1"));
			}
			else {}

			if(intermediate6.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 6"));		
				players.add(new IntermediateAIPlayer("Intermediate Player 5"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 4"));		
				players.add(new IntermediateAIPlayer("Intermediate Player 3"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else if(intermediate5.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 5"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 4"));		
				players.add(new IntermediateAIPlayer("Intermediate Player 3"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else if(intermediate4.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 4"));		
				players.add(new IntermediateAIPlayer("Intermediate Player 3"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else if(intermediate3.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 3"));	
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else if(intermediate2.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 2"));
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else if(intermediate1.getSelectedObjects() != null) {
				players.add(new IntermediateAIPlayer("Intermediate Player 1"));
			}
			else {}

			if(humanYes.getSelectedObjects() != null) {
				human = new HumanPlayer("Human");
				players.add(human);
			}
		}

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

	public void territorySelected(Territory t) {
		if(typeOfPlay == TypeOfPlay.SELECT_TERRITORY) {
			if(t.getCurrentOwner() != null) {
				label.setText("That territory is already occupied, please try again");
			}
			else {
				//System.out.println("NumArmies = " + human.getNumArmies());
				t.setCurrentOwner(human);
				t.setNumArmies(1);
				human.setNumArmies(human.getNumArmies() - 1);
				human.addTerritory(t);
				mapPanel.repaint();
				label.setText("You have claimed the territory " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
				human.setTerritoryChosen(true);
				//System.out.println("NumArmies = " + human.getNumArmies());
			}
		}
		else if(typeOfPlay == TypeOfPlay.PLACE_ARMY) {
			if(t.getCurrentOwner() != human) {
				label.setText("You dont own that territory...");
			}
			else {
				t.setNumArmies(t.getNumArmies() + 1);
				human.setNumArmies(human.getNumArmies() - 1);
				label.setText("You have added an army to " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
			}
		}
		else if (typeOfPlay == TypeOfPlay.ATTACK_FROM) {
			if (t.getCurrentOwner() != human) {
				label.setText("You don't own that territory!");
			}
			else {
				human.setTerritoryChosen(true);
				human.setCurrentTerritory(t);
				label.setText("You are attacking from " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
			}
		}
		else if (typeOfPlay == TypeOfPlay.ATTACK_TO){
			if (t.getCurrentOwner() == human) {
				label.setText("You can't attack yourself!");
			}
			if(!human.getCurrentTerritory().getAdjacent().contains(t)) {
				label.setText("You must select a territory adjacent to the one you are attacking from.\n"
						+ "You are attacking from " + t.getName());
			}
			else {
				human.setTerritoryChosen(true);
				human.setCurrentTerritory(t);
				label.setText("You are attacking " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
			}
		}
		else {
			label.setText("It is not your turn");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			label.setText((String) arg); 
		}
		else if(arg == ObserverMessages.HUMAN_PLACE_ARMY) {
			boolean allTaken = true;
			for(Territory t: controller.getTerritories()) {
				if(t.getCurrentOwner() == null)
					allTaken = false;
			}
			if(!allTaken) {
				typeOfPlay = TypeOfPlay.SELECT_TERRITORY;
				label.setText("Please select a Territory to claim");
				
			}

			else {
				typeOfPlay = TypeOfPlay.PLACE_ARMY;
				label.setText("You have " + human.getNumArmies() + " armies left to place.  "
						+ "Please select a territory to place an army.");
				
			}
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_ATTACK_FROM) {
			label.setText("Please select one of your territories to attack from.");
			typeOfPlay = TypeOfPlay.ATTACK_FROM;
			//((HumanPlayer) o).setTerritoryChosen(true); 
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_ATTACK_TO) {
			//((HumanPlayer) o).setTerritoryChosen(true);
			label.setText("Please select an enemy territory to attack.");
			typeOfPlay = TypeOfPlay.ATTACK_TO;
		}

		mapPanel.repaint();
	}

	private enum TypeOfPlay {
		SELECT_TERRITORY, PLACE_ARMY, ATTACK_FROM, ATTACK_TO, DO_NOTHING
	}

}
