
public class LinkedList {
	
	private SoldierNode head; //Top of the list
	
	LinkedList() //Default constructor
	{
		head = null;
	}
	
	public SoldierNode getHead() //getter
	{
		return head;
	}
	public void setHead(SoldierNode head)// setter
	{
		this.head = head;
	}

	public int findMe(Soldier me) // finds the index of the node that the current soldier belongs in
	{
		int ret = -1; //declare and initialize the return value
		SoldierNode current = head; //start at top of list
		while (current != null) //still on list
		{
			ret++; //count index up
			if (current.getMyGuy() == me) //is this me
			{
				return ret; //return the index
			}
			current = current.getNextUnit(); //else get next on list
		}
		ret = -1; //error code
		return ret; //return error code
	}
	
	public void addToHead(SoldierNode nNode) //adds to the list
	{
		nNode.setNextUnit(head); //New node points to current head
		head = nNode; //becomes top of the list
	}
	
	public int getTotalHealth() //counts the total health in the list
	{
		int ret = 0; //declare and initialize the return value
		SoldierNode current = head; //start at top of list
		while (current != null) //still on list
		{
			ret += current.getMyGuy().getHealth(); //add current soldiers health to the total
			current = current.getNextUnit(); // get the next soldier
		}
		return ret; //return the total
	}
	
	public Soldier getByIndex(int index) //get the index'th soldier
	{
		SoldierNode current = head; // start at top of list
		for(int i = 0; i < index && current != null; i++) //stay on the list and count to the index
		{
			if(current.getNextUnit() == null) // prompts for error message
			{
				System.out.println("Index not found for LinkedList.java function getByIndex."); //cout's an error
				i = 100; //force exit loop
			}
			else
			{
				current = current.getNextUnit(); //get the next guy
			}
		}
		return current.getMyGuy(); //return the guy
	}
	
	public void killByIndex(int index) //kills the index'th soldier
	{
		SoldierNode current = head; //start at top of list
		SoldierNode previous = null;
		for(int i = 0; i < index && current != null; i++) //stay on the list and count to the index
		{
			if(current.getNextUnit() == null) //prompts error message
			{
				System.out.println("Index not found for LinkedList.java function killByIndex."); //cout's an error
				i = 100;  //force exit loop
			}
			previous = current; //last guy is this guy
			current = current.getNextUnit(); //this guy is the next guy
		}
		if (previous == null) //if head
		{
			head = current.getNextUnit(); //resets head
		}
		else
		{
			previous.setNextUnit(current.getNextUnit());//otherwise, bypass this guy, let the garbage collector take care of it
		}
	}
}
