
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
	
	public void addToHead(SoldierNode nNode)
	{
		nNode.setNextUnit(head);
		head = nNode;
	}
	
	public int getTotalHealth()
	{
		int ret = 0;
		SoldierNode current = head;
		while (current != null)
		{
			ret += current.getMyGuy().getHealth();
			current = current.getNextUnit();
		}
		return ret;
	}
	
	public Soldier getByIndex(int index)
	{
		SoldierNode current = head;
		for(int i = 0; i < index && current != null; i++)
		{
			if(current.getNextUnit() == null)
			{
				System.out.println("Index not found for LinkedList.java function killByIndex.");
				i = 100;
			}
			else
			{
				current = current.getNextUnit();
			}
		}
		return current.getMyGuy();
	}
	
	public void killByIndex(int index)
	{
		SoldierNode current = head;
		SoldierNode previous = null;
		for(int i = 0; i < index && current != null; i++)
		{
			if(current.getNextUnit() == null)
			{
				System.out.println("Index not found for LinkedList.java function killByIndex.");
			}
			else
			{
				i = 100;
			}
			previous = current;
			current = current.getNextUnit();
		}
		if (previous == null)
		{
			head = current.getNextUnit();
		}
		else
		{
			previous.setNextUnit(current.getNextUnit());
		}
	}
}
