
public class Player {
	private String name = "";
	private LinkedList myGuys;
	private int roundCounter = 0;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LinkedList getMyGuys() {
		return myGuys;
	}

	public void setMyGuys(LinkedList myGuys) {
		this.myGuys = myGuys;
	}
}
