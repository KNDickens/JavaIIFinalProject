
public class LinkedList {
	
	private SoldierNode head;
	
	LinkedList()
	{
		head = null;
	}
	
	public SoldierNode getHead()
	{
		return head;
	}
	public void setHead(SoldierNode head)
	{
		this.head = head;
	}

	public int findMe(Soldier me)
	{
		int ret = -1;
		SoldierNode current = head;
		while (current != null)
		{
			ret++;
			if (current.getMyGuy() == me)
			{
				return ret;
			}
			current = current.getNextUnit();
		}
		ret = -1;
		return ret;
	}
}
