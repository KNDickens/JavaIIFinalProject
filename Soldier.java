/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

public abstract class Soldier implements Movable <Soldier>{

	private int health; // private variable used to keep track of soldier health value
	private int attack; // private variable used to keep track of soldier attack value
	private int range; // private variable used to keep track of soldier range value
	private Player myPlayer; // grabs the current player
	private String type; // private variable used to keep track of the soldier type
	
	Soldier() // default constructor, if you come here you broke something
	{
		setHealth(-1);
		setAttack(-1);
		setRange(-1);
		setMyPlayer(null);
	}
	
	Soldier(int health, int attack, int range) // actual constructor for soldier which brings in health, attack, and range values
	{
		this.setHealth(health); // sets this unit's health
		this.setAttack(attack); // sets this unit's attack
		this.setRange(range); // sets this unit's range
		setMyPlayer(null);
	}
  
	//Part of the Movable vestigial code.
	//public void Move(int compass) {
	//}
	//public Soldier Linked() {
		//return null;
	//}
	//public void MoveAll(int compass) {
	//}
  
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
//Problems? None