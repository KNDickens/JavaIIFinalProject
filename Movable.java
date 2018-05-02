/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

public interface Movable <E>{

	public void Move(int compass);
	public E Linked();
	public void MoveAll(int compass);
}
// Problems? None