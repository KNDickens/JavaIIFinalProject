/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

public class Ranged extends Soldier{
	//Low Attack, Low Health, High Range

	Ranged()
	{
		super(3,1,5);// sets the value of health, attack, and range to 3, 1, and 5
		setType("ranged"); // sets the unit type to ranged
	}

}
// Problems? None