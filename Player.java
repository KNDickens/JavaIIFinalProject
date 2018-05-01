
public class Player {
	private String name = ""; // initialize name variable for player names
	private LinkedList myGuys; // initialize the linked list for the army called myGuys
	private int roundCounter = 0; // each player has a round counter

	
	Player()
	{
		myGuys = new LinkedList();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList getMyGuys()
	{
		return myGuys;
	}

	public int findMe(Soldier me)
	{
		return myGuys.findMe(me);
	}

	public int roundIncremenet()
	{
		return roundCounter++;
	}

	public int getRoundCounter() {
		return roundCounter;
	}

	public void setRoundCounter(int roundCounter) {
		this.roundCounter = roundCounter;
	}

	public void setMyGuys(LinkedList myGuys)
	{
		this.myGuys = myGuys;
	}
	
	public void addSoldier(int choice)
	{
		switch (choice)
		{
		case 0: myGuys.addToHead(new SoldierNode(new Melee()));
			break;
		case 1: myGuys.addToHead(new SoldierNode(new Ranged()));
			break;
		case 2: myGuys.addToHead(new SoldierNode(new Banner()));
			break;
		}
	}
}
