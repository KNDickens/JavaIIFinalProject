/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

public class Banner extends Soldier{
	//Low Attack, High Health, Low Range, Adjacent Units move as phalanx

	Banner()
	{
		super(5,1,1); // sets the value of health, attack, and range to 5, 1, and 1
		setType("banner"); // sets the type of the unit to banner
	}

}
// Problems? None