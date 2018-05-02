/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

public class SoldierNode {
	
	private Soldier myGuy; // myGuy is the data in the current node
	private SoldierNode nextUnit; // nextUnit is the next soldier in the list
	
	SoldierNode()
	{
		setMyGuy(null);
		setNextUnit(null);
	}

	SoldierNode(Soldier guy)
	{
		myGuy = guy;
		nextUnit = null;
	}
	
	// Auto Generated Getters and Setters from Eclipse Source
	
	public Soldier getMyGuy() {
		return myGuy;
	}

	public void setMyGuy(Soldier myGuy) {
		this.myGuy = myGuy;
	}

	public SoldierNode getNextUnit() {
		return nextUnit;
	}

	public void setNextUnit(SoldierNode nextUnit) {
		this.nextUnit = nextUnit;
	}

}
//Problems? None