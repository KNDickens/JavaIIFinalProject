
public class SoldierNode {
	
	private Soldier myGuy;
	private SoldierNode nextUnit;
	
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
