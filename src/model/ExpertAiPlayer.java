package model;

import java.util.ArrayList;
import java.util.Random;

public class ExpertAiPlayer extends Player {

	private ArrayList<Territory> NA;
	private ArrayList<Territory> SA;
	private ArrayList<Territory> EU;
	private ArrayList<Territory> AF;
	private ArrayList<Territory> ASI;
	private ArrayList<Territory> AUS;
	private int count = 0;
	private String ChoosenContinent = "";

	private void setNA() {
		NA = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			// North America

			if (t.getName() == "Alaska") {
				NA.add(t);
			}
			if (t.getName() == "Alberta") {
				NA.add(t);
			}
			if (t.getName() == "Central America") {
				NA.add(t);
			}
			if (t.getName() == "Eastern United States") {
				NA.add(t);
			}
			if (t.getName() == "Greenland") {
				NA.add(t);
			}
			if (t.getName() == "Northwest Territory") {
				NA.add(t);
			}
			if (t.getName() == "Ontario") {
				NA.add(t);
			}
			if (t.getName() == "Quebec") {
				NA.add(t);
			}
			if (t.getName() == "Western United States") {
				NA.add(t);
			}
			System.out.println("NA: " + NA.size());
		}
		
	}

	private void setSA() {
		SA = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			if (t.getName() == "Argentina") {
				SA.add(t);
			}
			if (t.getName() == "Brazil") {
				SA.add(t);
			}
			if (t.getName() == "Peru") {
				SA.add(t);
			}
			if (t.getName() == "Venezuela") {
				SA.add(t);
			}
			System.out.println("SA: " + SA.size());

		}
		
	}

	private void setEU() {
		EU = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			if (t.getName() == "Great Britain") {
				EU.add(t);
			}
			if (t.getName() == "Iceland") {
				EU.add(t);
			}
			if (t.getName() == "Northern Europe") {
				EU.add(t);
			}
			if (t.getName() == "Scandinavia") {
				EU.add(t);
			}
			if (t.getName() == "Southern Europe") {
				EU.add(t);
			}
			if (t.getName() == "Ukraine") {
				EU.add(t);
			}
			if (t.getName() == "Western Europe") {
				EU.add(t);
			}
			System.out.println("EU: " + EU.size());
		}
		
	}

	private void setAF() {
		AF = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			if (t.getName() == "Congo") {
				AF.add(t);
			}
			if (t.getName() == "East Africa") {
				AF.add(t);
			}
			if (t.getName() == "Egypt") {
				AF.add(t);
			}
			if (t.getName() == "Madagascar") {
				AF.add(t);
			}
			if (t.getName() == "North Africa") {
				AF.add(t);
			}
			if (t.getName() == "South Africa") {
				AF.add(t);
			}
		}
		System.out.println("AF: " + AF.size());
		
	}

	private void setASI() {
		ASI = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			if (t.getName() == "Afghanistan") {
				ASI.add(t);
			}
			if (t.getName() == "China") {
				ASI.add(t);
			}
			if (t.getName() == "India") {
				ASI.add(t);
			}
			if (t.getName() == "Irkutsk") {
				ASI.add(t);
			}
			if (t.getName() == "Japan") {
				ASI.add(t);
			}
			if (t.getName() == "Kamchatka") {
				ASI.add(t);
			}
			if (t.getName() == "Middle East") {
				ASI.add(t);
			}
			if (t.getName() == "Mongolia") {
				ASI.add(t);
			}
			if (t.getName() == "Siam") {
				ASI.add(t);
			}
			if (t.getName() == "Siberia") {
				ASI.add(t);
			}
			if (t.getName() == "Ural") {
				ASI.add(t);
			}
			if (t.getName() == "Yakutsk") {
				ASI.add(t);
			}

			System.out.println("ASI: " + ASI.size());
		}
		
	}

	private void setAUS() {
		AUS = new ArrayList<Territory>();
		for (Territory t : getAllTerritories()) {
			if (t.getName() == "Eastern Australia") {
				AUS.add(t);
			}
			if (t.getName() == "Indonesia") {
				AUS.add(t);
			}
			if (t.getName() == "New Guinea") {
				AUS.add(t);
			}
			if (t.getName() == "Western Australia") {
				AUS.add(t);
			}

			System.out.println("AUS: " + AUS.size());
		}
		
	}
		
	// private Random genRan;
	public ExpertAiPlayer() {
		super();
	}

	public ExpertAiPlayer(String name) {
		super(name);
		if (debug)
			System.out.println("New ExpertAIPlayer created: " + name);
	}

	// TODO: work on this!
	@Override
	public void placeArmy() {
		if(count == 0){
			setNA();
			setSA();
			setEU();
			setAF();
			setASI();
			setAUS();
			count++;
			
		}
		
		ArrayList<String> ChooseContinent = new ArrayList<String>();
		Territory selected = null;
		if (debug)
			System.out.println("placeArmy called by " + getName());
		// Semi-intelligently chooses a territory with two or more armies to
		// attack from
		Random genRan = new Random();

		// determines if you are still in territory selecting mode or if in army
		// placing mode
		boolean allSelected = true;
		for (Territory t : getAllTerritories()) {
			if (t.getCurrentOwner() == null) {
				allSelected = false;
			}
		}

		if (allSelected == false) {
			// if it is the first territory to select
			if (getTerritoriesOwned().size() == 0) {
				// check continent to see if there is another player with land
				// here, empty or less than 2 other territories taken

				System.out.println(AUS.toString());

				Boolean NACompatible = false;
				Boolean SACompatible = false;
				Boolean EUCompatible = false;
				Boolean AFCompatible = false;
				Boolean ASICompatible = false;
				Boolean AUSCompatible = false;
				Boolean isTaken = false;
				int NumNA = 0;
				int NumSA = 0;
				int NumEU = 0;
				int NumAF = 0;
				int NumASI = 0;
				int NumAUS = 0;

				// North America Availability
				for (int n = 0; n < NA.size(); n++) {
					if (NA.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumNA++;
					}
				}
				if (isTaken == true) {
					NACompatible = false;
				} else {
					NACompatible = true;
				}
				isTaken = false;

				// South America Compatible
				for (int n = 0; n < SA.size(); n++) {
					if (SA.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumSA++;
					}
				}
				if (isTaken == true) {
					SACompatible = false;
				} else {
					SACompatible = true;
				}
				isTaken = false;

				// Europe Compatible
				for (int n = 0; n < EU.size(); n++) {
					if (EU.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumEU++;
					}
				}
				if (isTaken == true) {
					EUCompatible = false;
				} else {
					EUCompatible = true;
				}
				isTaken = false;
				// Africa Compatible
				for (int n = 0; n < AF.size(); n++) {
					if (AF.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumAF++;
					}
				}
				if (isTaken == true) {
					AFCompatible = false;
				} else {
					AFCompatible = true;
				}
				isTaken = false;

				// Asia Compatible
				for (int n = 0; n < ASI.size(); n++) {
					if (ASI.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumASI++;
					}
				}
				if (isTaken == true) {
					ASICompatible = false;
				} else {
					ASICompatible = true;
				}
				isTaken = false;

				// Australia Compatible
				for (int n = 0; n < AUS.size(); n++) {
					if (AUS.get(n).getCurrentOwner() != null) {
						isTaken = true;
						NumAUS++;
					}
				}
				if (isTaken == true) {
					AUSCompatible = false;
				} else {
					AUSCompatible = true;
				}
				isTaken = false;

				if (NACompatible) {
					ChooseContinent.add("North America");
				}
				if (SACompatible) {
					ChooseContinent.add("South America");
				}
				if (EUCompatible) {
					ChooseContinent.add("Europe");
				}
				if (AFCompatible) {
					ChooseContinent.add("Africa");
				}
				if (ASICompatible) {
					ChooseContinent.add("Asia");
				}
				if (AUSCompatible) {
					ChooseContinent.add("Australia");
				}
				int rc = genRan.nextInt(ChooseContinent.size());
				System.out.println(ChooseContinent.get(rc).toString());
				
				if (ChooseContinent.get(rc) == "North America") {
					for (int t = 0; t < NA.size(); t++) {
						if (NA.get(t).getCurrentOwner() == null) {
							selected = NA.get(t);
							ChoosenContinent = "North America";
							System.out.println("Selected: " + selected.toString());
							break;
						}
					}

				}

				if (ChooseContinent.get(rc) == "South America") {
					for (int t = 0; t < SA.size(); t++) {
						if (SA.get(t).getCurrentOwner() == null) {
							selected = SA.get(t);
							ChoosenContinent = "South America";
							System.out.println("Selected: " + selected.toString());
							break;
						}
					}

				}

				if (ChooseContinent.get(rc) == "Europe") {
					for (int t = 0; t < EU.size(); t++) {
						if (EU.get(t).getCurrentOwner() == null) {
							selected = EU.get(t);
							ChoosenContinent = "Europe";
							System.out.println("Selected: " + selected.toString());
							break;
						}
					}

				}

				if (ChooseContinent.get(rc) == "Africa") {
					for (int t = 0; t < AF.size(); t++) {
						if (AF.get(t).getCurrentOwner() == null) {
							selected = AF.get(t);
							ChoosenContinent = "Africa";
							System.out.println("Selected: " + selected.toString());
							break;
						}
					}

				}
				if (ChooseContinent.get(rc) == "Asia") {
					for (int t = 0; t < ASI.size(); t++) {
						if (ASI.get(t).getCurrentOwner() == null) {
							selected = ASI.get(t);
							ChoosenContinent = "Asia";
							System.out.println("Selected: "+ selected.toString());
							break;
						}
					}

				}

				if (ChooseContinent.get(rc) == "Australia") {
					for (int t = 0; t < AUS.size(); t++) {
						if (AUS.get(t).getCurrentOwner() == null) {
							selected = AUS.get(t);
							ChoosenContinent = "Australia";
							System.out.println("Selected: "+ selected.toString());
							break;
						}
					}

				}

				// check for empty continents.
				// pick one territory there and grab the adjacent
				// when full look at adjacent continent for empty territories or
				// grab available territory

				// int r = genRan.nextInt(42);
				// boolean territorySelected = false;
				// while(!territorySelected) {
				// Territory selected = getAllTerritories().get(r);
				// if(selected.getCurrentOwner() == null) {
				selected.setCurrentOwner(this);
				selected.setNumArmies(1);
				// territorySelected = true;
				addTerritory(selected);
				setNumArmies(getNumArmies() - 1);
				if (debug)
					System.out.println("Army successfully placed in "
							+ selected.getName() + " by " + getName());
				// }//second if statement

				// used to be else statement with random number generator.
				// .nextInt
			}// getTerritoriesOwned.size() if statement
			else {
				selected = null;
//				setNA();
//				setSA();
//				setEU();
//				setAF();
//				setASI();
//				setAUS();

			if (ChoosenContinent.equals("North America")) {
				int i = 0;
				while (i < NA.size()+1) {
					if(i!=NA.size()){
					if (NA.get(i).getCurrentOwner() == null) {
						selected = NA.get(i);
						break;
					}}

					else {
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
										
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			if (ChoosenContinent.equals("South America")) {
				int i = 0;
				while (i < SA.size()+1) {
					if(i!=SA.size()){
					if (SA.get(i).getCurrentOwner() == null) {
						selected = SA.get(i);
						break;
					}}
					else{

					
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			if (ChoosenContinent.equals("Europe")) {
				int i = 0;
				while (i < EU.size()+1) {
					if(i != EU.size()){
					if (EU.get(i).getCurrentOwner() == null) {
						selected = EU.get(i);
						break;
					}}

					else {
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			if (ChoosenContinent.equals("Africa")) {
				int i = 0;
				while (i < AF.size()+1) {
					if(i != AF.size()){
					if (AF.get(i).getCurrentOwner() == null) {
						selected = AF.get(i);
						break;
					}}

					else{
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			if (ChoosenContinent.equals("Asia")) {
				int i = 0;
				while (i < ASI.size()+1) {
					if(i!=ASI.size()){
					if (ASI.get(i).getCurrentOwner() == null) {
						selected = ASI.get(i);
						break;
					}
					}
					else{
					
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			if (ChoosenContinent.equals("Australia")) {
				int i = 0;
				while (i < AUS.size()+1) {
					if(i != AUS.size()){
					if (AUS.get(i).getCurrentOwner() == null) {
						selected = AUS.get(i);
						break;
					}}
					
					else{
						
						int n = 0;
						while (n < getAllTerritories().size()) {
							if (getAllTerritories().get(n).getCurrentOwner() == null) {
								selected = getAllTerritories().get(n);
								break;
							} else {
								n++;
							}
						}
					}
					i++;
				}
			}
			
			System.out.println("SelectedPlace: " + selected.toString());
			selected.setCurrentOwner(this);
			selected.setNumArmies(1);
			// territorySelected = true;
			addTerritory(selected);
			setNumArmies(getNumArmies() - 1);
			
		}// allSelected if statement
		}
		// }//end of choosing empty territories
		else {
			int r = genRan.nextInt(getTerritoriesOwned().size());
			getTerritoriesOwned().get(r).setNumArmies(getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
			if (debug)
				System.out
						.println("Army successfully placed in owned territory by "
								+ getName());
		}
	}

	// TODO: work on this!
	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		if (debug)
			System.out.println("reinforceArmies called by " + getName());
		if (numTimesCalled < 1) {
			reinforce();
			numTimesCalled++;
		} else {
			int x = takeArmy.getNumArmies();
			int y = reinforceThis.getNumArmies();
			takeArmy.setNumArmies(x -= numTroopsTake);
			reinforceThis.setNumArmies(y += numTroopsTake);
			numTimesCalled = 0;
		}

	}

	private int numTroopsTake = 0;
	private int numTimesCalled = 0;

	public void reinforce() { // helper method for reinforce armies method
		if (debug)
			System.out.println("reinforce called by " + getName());

		Territory high = null;
		Territory low = null;
		int highTroop = 0;
		int lowTroop = 1000;
		boolean take = false;
		boolean adjacentNotOwned = false;

		for (int i = 0; i <= getTerritoriesOwned().size(); i++) {
			for (int j = 0; j <= getTerritoriesOwned().get(i).getAdjacent()
					.size(); j++) {// check all territories look through all
									// armies find lowest with competitors or
									// ones with no competitors and give to
									// territories more in need

				if (getTerritoriesOwned().get(i).getAdjacent().get(j)
						.getCurrentOwner() != this) { // checks for un-owned
														// neighbors
					if (getTerritoriesOwned().get(i).getAdjacent().get(j)
							.getNumArmies() >= getTerritoriesOwned().get(i)
							.getNumArmies()) {// checks to see if neigbor has
												// more armies than us
						if (getTerritoriesOwned().get(i).getNumArmies() < lowTroop) {// checks
																						// to
																						// see
																						// if
																						// we
																						// are
																						// more
																						// screwed
																						// than
																						// other
																						// territories
							low = getTerritoriesOwned().get(i);// sets this as
																// the low
																// territory
							lowTroop = getTerritoriesOwned().get(i)
									.getNumArmies(); // sets how many current
														// armies there are here
						}// if statement
					}// if statement
				}// if statement
			}// for loop
			for (int e = 0; e <= getTerritoriesOwned().get(i).getAdjacent()
					.size(); e++) {// loops through adjacent territories
				if (getTerritoriesOwned().get(i).getAdjacent().get(e)
						.getCurrentOwner() != this) {// checks if we don't own
														// an adjacent land
					adjacentNotOwned = true;// if we don't own something we
											// can't automatically take from
											// this territory
					if (getTerritoriesOwned().get(i).getNumArmies() >= highTroop) {// checks
																					// to
																					// see
																					// if
																					// this
																					// is
																					// the
																					// highest
																					// available
																					// troop
																					// count
						if (getTerritoriesOwned().get(i).getAdjacent().get(e)
								.getNumArmies() <= (getTerritoriesOwned()
								.get(i).getNumArmies() * 2) / 3) { // checks to
																	// see if
																	// they
																	// should
																	// take from
																	// here for
																	// sure
							take = true;
							highTroop = getTerritoriesOwned().get(i)
									.getNumArmies();
							high = getTerritoriesOwned().get(i);
						}
					} else {
						take = false;
					}
				}// if statement
			}// for loop

			if (adjacentNotOwned == false) {
				take = true;
				highTroop = getTerritoriesOwned().get(i).getNumArmies();
				high = getTerritoriesOwned().get(i);
				break;
			}// if statement
			if (take) {
				numTroopsTake = (highTroop * 2) / 3;
			}
		}// master for loop

		reinforceArmies(high, low);

	}// close helper method

	@Override
	public Territory attackFrom() {
		if (debug)
			System.out.println("attackFrom called by " + getName());
		Territory choosenTerritory = null;
		while (choosenTerritory == null) {
			// int r = genRan.nextInt(getTerritoriesOwned().size());
			for (int i = 0; i < getTerritoriesOwned().size(); i++) {
				// if(getTerritoriesOwned().get(i).getNumArmies() > 1) {
				if (getTerritoriesOwned().get(i).getNumArmies() == maxArmy()) {
					for (Territory t : getTerritoriesOwned().get(i)
							.getAdjacent()) {
						if (t.getCurrentOwner() != this) {
							choosenTerritory = getTerritoriesOwned().get(i);
						}
					}
				}
			}
		}
		return choosenTerritory;
	}

	// helper method for max number armies
	private int maxArmy() {
		int maxNumArmy = 0;
		for (int i = 0; i < getTerritoriesOwned().size(); i++) {
			if(i+1 == getTerritoriesOwned().size()){
			if (getTerritoriesOwned().get(i).getNumArmies() < getTerritoriesOwned()
					.get(i + 1).getNumArmies()
					&& 1 + i != getTerritoriesOwned().size()) {
				
				maxNumArmy = getTerritoriesOwned().get(i + 1).getNumArmies();
			}
		}else{
			break;}
		}
		return maxNumArmy;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		if (debug)
			System.out.println("attackTo called by " + getName());
		// Semi-intelligently chooses a territory and places one army there
		int lowTroop = 1000;
		System.out.println("attackTo called by " + getName());
		Territory attack = null;
		for (int i = 0; i < attackFrom.getAdjacent().size(); i++) {
			if (attackFrom.getAdjacent().get(i).getCurrentOwner() != this) {
				if (attackFrom.getAdjacent().get(i).getNumArmies() < lowTroop) {
					lowTroop = attackFrom.getAdjacent().get(i).getNumArmies();
					attack = attackFrom.getAdjacent().get(i);
				}
			}
		}
		return attack;
	}

}