
public class Player {
	private String name = "";
	private LinkedList myGuys;

	
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
}
