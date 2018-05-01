import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException
	{
		
		Scanner input = new Scanner(System.in);
		
		Soldier[][] board = new Soldier[8][8];
		
		System.out.println("Melee units are high attack with high health low range.");
		System.out.println("Ranged units have low attack, low health, but high range.");
		System.out.println("Banner units have low attack, high health, and low range. They are used to link melee units into a phalanx");
		System.out.println("Phalanx groups attack and move as one powerful entity.");
		// explanation of what the pieces are
		System.out.println("The player with the most health at the end of the game wins.");
		
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
		System.out.println(playerOne.getName() + " please select the units you will field. You get 8 Soldiers. \n" +
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
		
		System.out.println(current.getName() + "'s turn. Please select a unit."); // prompts the user to select a unit
		
		showBoard(board); // shows the board
		
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
			end = checkWin(playerOne, playerTwo); // checks to see if the round counter has hit 10 rounds, if a player has lost all of their
		}while (end != true); // cycles between player one and player two until 10 turns pass
	}

	public static Player playTurn(Player current, Soldier[][] board) // play turn
	{
		Scanner turnInput = new Scanner(System.in); // initialize an input used for the turn called turnInput
		System.out.println(current.getName() + "'s turn: "); // prompts the user to take their turn
		showBoard(board); // displays the board
		System.out.println("Please select the unit you wish move");
		int soldierSelect = turnInput.nextInt();
		System.out.println("Select the direction you would like to move them.");
		int moveSelect = turnInput.nextInt();
		moveSoldier(current.getMyGuys().getByIndex(soldierSelect), moveSelect, board);
		return null;
	}
	

	
	public static void moveSoldier(Soldier move, int compass, Soldier[][] board)	
	{
		if (move != null)			
		{
			int x = -1,y = -1; 
			for(int i = 0; i < 8; i++)			
			{
				for(int j = 0; j < 8; j++)				
				{
					if(board[i][j] == move)					
					{
						y=i;
						x=j;
					}
				}
			}
			switch (compass)			
			{
			case 0: //north
				if (y > 0)				
				{
					if (board[y-1][x] == null)					
					{
						board[y-1][x] = move;
						board[y][x] = null;
					}
				}
				break;
			case 1: //east
				if (x < 7)				
				{
					if (board[y][x+1] == null)					
					{
						board[y][x+1] = move;
						board[y][x] = null;
					}
				}
				break;
			case 2: //south
				if ( y < 7 )				
				{
					if (board[y+1][x] == null)					
					{
						board[y+1][x] = move;
						board[y][x] = null;
					}
				}
				break;
			case 3: // west
				if (x > 0)				
				{
					if (board[y][x-1] == null)					
					{
						board[y][x-1] = move;
						board[y][x] = null;
					}
				}
				break;
			}
			if (move.getType() == "banner")			
			{
				if(x<7)				
				{
					moveSoldier(board[y][x+1], compass, board);
				}
				if(y<7)				
				{
					moveSoldier(board[y+1][x], compass, board);
				}
				if(x>0)
				{	
					moveSoldier(board[y][x-1], compass, board);
				}
				if(y>0)				
				{
					moveSoldier(board[y-1][x], compass, board);
				}
			}
			attack(x,y,board,compass,move);
		}
	}
	
	public static void attack(int x, int y, Soldier[][] board, int compass, Soldier attack)
	{
		for (int i = 0; i < attack.getRange(); i++)
		{
			switch (compass)
			{
			case 0: //[y-1][x]
				if (y > 0)
				{
					if(board[y-1][x] != null && board[y-1][x].getMyPlayer()!=attack.getMyPlayer())
					{
						board[y-1][x].setHealth(board[y-1][x].getHealth() - attack.getAttack());
					}
				}
				break;
			case 1: //[y][x+1]
				if (x < 7)
				{
					if(board[y][x+1] != null && board[y][x+1].getMyPlayer()!=attack.getMyPlayer())
					{
						board[y][x+1].setHealth(board[y][x+1].getHealth() - attack.getAttack());
					}
				}	
				break;
			case 2: //[y+1][x]
				if (y < 7)
				{
					if(board[y+1][x] != null && board[y+1][x].getMyPlayer()!=attack.getMyPlayer())
					{	
						board[y+1][x].setHealth(board[y+1][x].getHealth() - attack.getAttack());
					}
				}
				break;
			case 3: //[y][x-1]
				if (x > 0)
				{
					if(board[y][x-1] != null && board[y][x-1].getMyPlayer()!=attack.getMyPlayer())
					{
						board[y][x-1].setHealth(board[y][x-1].getHealth() - attack.getAttack());
					}
				}
				break;
			}
		}
	}
	
	
	public static boolean checkWin(Player playerOne, Player playerTwo) // checks to see if the player has won
	{
		if(playerTwo.getRoundCounter() >= 10 || playerOne.getMyGuys().getTotalHealth() == 0 || playerTwo.getMyGuys().getTotalHealth() == 0)
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
