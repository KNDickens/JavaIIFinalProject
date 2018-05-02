/*
Austin Taylor Reynolds & Kyle Dickens
Final Java 2 Poject: Turn Based Strategy Phalanx Alpha
5/2/2018
*/

import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException
	{
		
		Scanner input = new Scanner(System.in);
		
		Soldier[][] board = new Soldier[8][8];
		
		System.out.println("Melee units are high attack with high health low range.");
		System.out.println("Ranged units have low attack, low health, but high range.");
		System.out.println("Banner units have low attack, high health, and low range. They are used to " +
								"link melee units into a phalanx");
		System.out.println("Phalanx groups attack and move as one powerful entity.");
		// explanation of what the pieces are
		System.out.println("");
		System.out.println("Games last 10 rounds or until a player no longer has any units.");
		System.out.println("The player with the most total unit health at the end of the game wins.");
		System.out.println("");
		// explanation of how to play the game
		
		Player playerOne = new Player(); // initializing player one
		Player playerTwo = new Player(); // initializing player two
		
		System.out.println("Enter Player One's Name: "); // allows for the player to enter a name
		playerOne.setName(input.nextLine()); // reads in the next line and sets it to player one as a string
		System.out.println(playerOne.getName() + " please select the units you will field. You get 8 Soldiers. \n" + 
				"(0) Melee, (1) Ranged, (2) Banner"); // prompts the user for input to build the army list
		for(int i = 0; i < 8; i++) // loop 8 times
		{
			playerOne.addSoldier(input.nextInt()); // reads in an int to field the soldier
			playerOne.getMyGuys().getHead().getMyGuy().setMyPlayer(playerOne); // sets the ownership of the new unit
		}
		input.nextLine(); // garbage catcher
		System.out.println("Enter Player Two's Name: "); // prompts the user to input a name
		playerTwo.setName(input.nextLine()); // reads in the next line as a string and sets it to player two's name
		System.out.println(playerTwo.getName() + " please select the units you will field. You get 8 Soldiers. \n" +
				"(0) Melee, (1) Ranged, (2) Banner"); // prompts the user for input to build the army list
		for(int i = 0; i < 8; i++) // loop 8 times
		{
			playerTwo.addSoldier(input.nextInt()); // reads in an int to field a soldier
			playerTwo.getMyGuys().getHead().getMyGuy().setMyPlayer(playerTwo); // sets the ownership of the new unit
		}
		
		SoldierNode curNode = playerOne.getMyGuys().getHead(); // grabs player one's army list
		for (int i = 0; i < 8; i++) // loop 8 times
		{
			board[0][i] = curNode.getMyGuy(); // fills the board with the current unit
			curNode = curNode.getNextUnit(); // goes from the current unit to the next one in the list
		}
		curNode = playerTwo.getMyGuys().getHead(); // grabs player two's army list
		for (int i = 0; i < 8; i++) // loop 8 times
		{
			board[7][i] = curNode.getMyGuy(); // fills the board with the current unit
			curNode = curNode.getNextUnit(); // changes from the current unit to the next one in the list
		}
		
		boolean end = true; // boolean end initialized
		
		Player current = playerOne; // sets current player to player one
		
		do { 
			if(current == playerOne) // if the current player is player one
			{
				playTurn(current, board); // player one plays their turn
				current.roundIncremenet(); // increment the round counter
				current = playerTwo; // sets the player to player two

			}
			else
			{
				playTurn(current, board); // player two plays their turn
				current.roundIncremenet(); // increment the round counter
				current = playerOne; // sets the player to player two
			}
			end = checkWin(playerOne, playerTwo); // checks to see if the round counter has hit 10 rounds, 
													//if a player has lost all of their
		}while (end != true); // cycles between player one and player two until 10 turns pass
	}

	public static Player playTurn(Player current, Soldier[][] board) // play turn
	{
		Scanner turnInput = new Scanner(System.in); // initialize an input used for the turn called turnInput
		showBoard(board); // displays the board
		System.out.println(current.getName() + "'s turn: "); // prompts the user to take their turn
		System.out.println("Please select the unit you wish move");
		int soldierSelect = turnInput.nextInt();
		System.out.println("Select the direction you would like to move them.");
		System.out.println("0 is North, 1 is East, 2 is South, 3 is West.");
		int moveSelect = turnInput.nextInt();
		moveSoldier(current.getMyGuys().getByIndex(soldierSelect), moveSelect, board);
		return null;
	}
	

	
	public static void moveSoldier(Soldier move, int compass, Soldier[][] board)	
	{
		if (move != null)			//ensures we were passed a soldier, banners broke this early
		{
			int x = -1,y = -1; 		//declare and initialize array locations
			for(int i = 0; i < 8; i++)			//find the array location of the soldier
			{
				for(int j = 0; j < 8; j++)				//its a double loop
				{
					if(board[i][j] == move)					//found the location
					{
						y=i;			//Record the location
						x=j;
					}
				}
			}
			switch (compass)			//What direction were we moving
			{
			case 0: //north
				if (y > 0)						//not at board edge
				{
					if (board[y-1][x] == null)	//empty space	
					{
						board[y-1][x] = move;	//Move the soldier
						board[y][x] = null;		//Empty the previous spot
					}
				}
				break;
			case 1: //east
				if (x < 7)						//not at board edge
				{
					if (board[y][x+1] == null)	//empty space	
					{
						board[y][x+1] = move;	//move the soldier
						board[y][x] = null;		//empty the previous spot
					}
				}
				break;
			case 2: //south
				if ( y < 7 )					//etc
				{
					if (board[y+1][x] == null)	//etc				
					{
						board[y+1][x] = move;	//etc
						board[y][x] = null;		//etc
					}
				}
				break;
			case 3: // west
				if (x > 0)						//etc
				{
					if (board[y][x-1] == null)	//etc					
					{
						board[y][x-1] = move;	//etc
						board[y][x] = null;		//etc
					}
				}
				break;
			} //NEW THING
			if (move.getType() == "banner")			//Did you move a banner
			{
				if(x<7 && board[y][x+1] != null && board[y][x+1].getType() == "melee" && 
						board[y][x+1].getMyPlayer() == move.getMyPlayer())	//not at board edge. space not empty.
				{															//soldier is melee. soldier belongs to you
					moveSoldier(board[y][x+1], compass, board);	//Move him too. (Recursion)
				}
				if(y<7 && board[y+1][x] != null && board[y+1][x].getType() == "melee" && 
						board[y+1][x].getMyPlayer() == move.getMyPlayer())	//Etc. Etc. Etc. Etc.
				{
					moveSoldier(board[y+1][x], compass, board);	//Etc.
				}
				if(x>0 && board[y][x-1] != null && board[y][x-1].getType() == "melee" && 
						board[y][x-1].getMyPlayer() == move.getMyPlayer())	//Etc. Etc. Etc. Etc.
				{	
					moveSoldier(board[y][x-1], compass, board);	//Etc.
				}
				if(y>0 && board[y-1][x] != null && board[y-1][x].getType() == "melee"  && 
						board[y-1][x].getMyPlayer() == move.getMyPlayer())	//Etc. Etc. Etc. Etc.
				{
					moveSoldier(board[y-1][x], compass, board);	//Etc.
				}
			}
			attack(x,y,board,compass,move); //After movement, Attack
		}
	}
	
	public static void attack(int x, int y, Soldier[][] board, int compass, Soldier attack)
	{
		for (int i = 1; i <= attack.getRange(); i++) //Range checker
		{
			switch (compass)
			{
			case 0: //[y-1][x]
				if (y > 0+i) //Board edge
				{
					if(board[y-i][x] != null && board[y-i][x].getMyPlayer()!=attack.getMyPlayer()) 	//isn't empty or ally
					{
						board[y-i][x].setHealth(board[y-i][x].getHealth() - attack.getAttack());	//Subtraction of health
						if (board[y-i][x].getHealth() < 1)	//Did you kill him?
						{
							board[y-i][x].getMyPlayer().getMyGuys().killByIndex(		//Run the kill function
								board[y-i][x].getMyPlayer().getMyGuys().findMe(board[y-i][x]));
							board[y-i][x] = null; //Empty the location
						}
					}
				}
				break;
			case 1: //[y][x+1]
				if (x < 7-i) //etc.
				{
					if(board[y][x+i] != null && board[y][x+i].getMyPlayer()!=attack.getMyPlayer()) //etc etc
					{
						board[y][x+i].setHealth(board[y][x+i].getHealth() - attack.getAttack());	//etc
						if (board[y][x+i].getHealth() < 1)	//etc
						{
							board[y][x+i].getMyPlayer().getMyGuys().killByIndex(		//etc
								board[y][x+i].getMyPlayer().getMyGuys().findMe(board[y][x+i]));
							board[y][x+i] = null;	//etc
						}
					}
				}	
				break;
			case 2: //[y+1][x]
				if (y < 7-i)	//etc
				{
					if(board[y+i][x] != null && board[y+i][x].getMyPlayer()!=attack.getMyPlayer())	//etc etc
					{	
						board[y+i][x].setHealth(board[y+i][x].getHealth() - attack.getAttack());	//etc
						if (board[y+i][x].getHealth() < 1)		//etc
						{
							board[y+i][x].getMyPlayer().getMyGuys().killByIndex(	//etc
								board[y+i][x].getMyPlayer().getMyGuys().findMe(board[y+i][x]));
							board[y+i][x] = null;	//etc
						}						
					}
				}
				break;
			case 3: //[y][x-1]
				if (x > 0+i)	//etc
				{
					if(board[y][x-i] != null && board[y][x-i].getMyPlayer()!=attack.getMyPlayer())	//etc etc
					{
						board[y][x-i].setHealth(board[y][x-i].getHealth() - attack.getAttack());	//etc
						if (board[y][x-i].getHealth() < 1)		//etc
						{
							board[y][x-i].getMyPlayer().getMyGuys().killByIndex(	//etc
								board[y][x-i].getMyPlayer().getMyGuys().findMe(board[y][x-i]));
							board[y][x-i] = null;	//etc
						}
					}
				}
				break;
			}
		}
	}
	
	
	public static boolean checkWin(Player playerOne, Player playerTwo) // checks to see if the player has won
	{
		if(playerTwo.getRoundCounter() >= 10 || playerOne.getMyGuys().getTotalHealth() == 0 || 
				playerTwo.getMyGuys().getTotalHealth() == 0)
		{ // The game will end if the players play for 10 rounds or if a player runs out of units
			if(playerOne.getMyGuys().getTotalHealth()>playerTwo.getMyGuys().getTotalHealth())
			{ // if player one has more health than player two player one wins
				System.out.println(playerOne.getName() + " Wins!");
				return true; // return true to end the game
			}
			else if(playerTwo.getMyGuys().getTotalHealth()>playerOne.getMyGuys().getTotalHealth())
			{ // if player two has more health than player one player two wins
				System.out.println(playerTwo.getName() + "Wins!");
				return true; // return true to end the game
			}
			else // if neither player has more health than the other and they have gone through all of their units it is a draw
			{
				System.out.println("Draw!");
				return true; // return true to end the game
			}
		}
		return false; // return false to continue the game
	}
	
	

	
	//Kyle's Printf game is the strongest in the land.
	public static void showBoard(Soldier[][] board)
	{
		for(int row = 0; row < board.length;row++)
		{
			for(int line = 0; line < 6; line++)
			{
				for(int col = 0; col < board[row].length; col++)
				{
					switch(line)
					{
					case 0: System.out.printf("%15s", "***************");
						break;

					case 1: System.out.printf("*%13s", (board[row][col]==null ? "" : 
						board[row][col].getMyPlayer().getName().length()<14 ? 
						board[row][col].getMyPlayer().getName() : 
						board[row][col].getMyPlayer().getName().substring(0, 13)));
						System.out.print(" ");
						break;
					case 2: System.out.printf("*%13s", (board[row][col]==null ? "" : "Health: " + 
						board[row][col].getHealth()));
						System.out.print(" ");
						break;
					case 3: System.out.printf("*%13s", (board[row][col]==null ? "" : "Attack: " + 
						board[row][col].getAttack()));
						System.out.print(" ");
						break;
					case 4: System.out.printf("*%13s", (board[row][col]==null ? "" : "Range:  " + 
						board[row][col].getRange()));
						System.out.print(" ");
						break;
					case 5: System.out.printf("*%13s", (board[row][col]==null ? "" : "Unit #: " + 
						board[row][col].getMyPlayer().findMe(board[row][col])));
						System.out.print(" ");
						break; //if -1 returns from findMe, something is busted.
					}
				}
				System.out.println("*");
			}
		}
		for(int col = 0; col < board[0].length; col++)
		{
			System.out.printf("%15s", "***************");
		}
		System.out.println("*");
	}
	
}
//Problems: Week long vacation. Array Out of Bounds errors, Null Pointer Errors, Infinite Movement bugs, stack overflow error.
