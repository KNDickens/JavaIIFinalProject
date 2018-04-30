
public class Player {
	private String name = "";
	private LinkedList myGuys;
	
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
