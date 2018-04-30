
public abstract class Soldier implements Movable <Soldier>{

	private int health;
	private int attack;
	private int range;
	private Player myPlayer;
	
	Soldier()
	{
		setHealth(-1);
		setAttack(-1);
		setRange(-1);
		setMyPlayer(null);
	}
	
	Soldier(int health, int attack, int range)
	{
		this.setHealth(health);
		this.setAttack(attack);
		this.setRange(range);
		setMyPlayer(null);
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public Player getMyPlayer() {
		return myPlayer;
	}

	public void setMyPlayer(Player myPlayer) {
		this.myPlayer = myPlayer;
	}
}
