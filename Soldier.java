
public abstract class Soldier implements Movable <Soldier>{

	private int health;
	private int attack;
	private int range;
	
	Soldier()
	{
		health = -1;
		attack = -1;
		range = -1;
	}
	
	Soldier(int health, int attack, int range)
	{
		this.health = health;
		this.attack = attack;
		this.range = range;
	}
	
	public void Move(int compass) {
		// TODO Auto-generated method stub
		
	}

	public Soldier Linked() {
		// TODO Auto-generated method stub
		return null;
	}

	public void MoveAll(int compass) {
		// TODO Auto-generated method stub
		
	}
}
