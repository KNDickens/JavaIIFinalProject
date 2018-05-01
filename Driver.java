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
		
		Player playerOne = new Player();
		Player playerTwo = new Player();
		
		System.out.println("Enter Player One's Name: ");
		playerOne.setName(input.nextLine());
		System.out.println("Time to choose " + playerOne.getName() + "'s soldiers. You get 8 Soldiers. \n" +
				"(0) Melee, (1) Ranged, (2) Banner");
		for(int i = 0; i < 8; i++)
		{
			playerOne.addSoldier(input.nextInt());
			playerOne.getMyGuys().getHead().getMyGuy().setMyPlayer(playerOne);
		}
		input.nextLine();
		System.out.println("Enter Player Two's Name: ");
		playerTwo.setName(input.nextLine());
		System.out.println("Time to choose " + playerTwo.getName() + "'s soldiers. You get 8 Soldiers. \n" +
				"(0) Melee, (1) Ranged, (2) Banner");
		for(int i = 0; i < 8; i++)
		{
			playerTwo.addSoldier(input.nextInt());
			playerTwo.getMyGuys().getHead().getMyGuy().setMyPlayer(playerTwo);
		}
		
		SoldierNode curNode = playerOne.getMyGuys().getHead();
		for (int i = 0; i < 8; i++)
		{
			board[0][i] = curNode.getMyGuy();
			curNode = curNode.getNextUnit();
		}
		curNode = playerTwo.getMyGuys().getHead();
		for (int i = 0; i < 8; i++)
		{
			board[7][i] = curNode.getMyGuy();
			curNode = curNode.getNextUnit();
		}
		
		boolean end = false;
		
		Player current = playerOne;
		
		System.out.println(current.getName() + "'s turn. Please select a unit.");
		
		showBoard(board);
		
		do {
			if(current == playerOne)
			{
				playTurn(current, board);
				current.roundIncremenet();
				current = playerTwo;
			}
			else
			{
				playTurn(current, board);
				current.roundIncremenet();
				current = playerOne;
			}
			end = checkWin(playerOne, playerTwo);
		}while (end != true);
	}

	public static Player playTurn(Player current, Soldier[][] board)
	{
		Scanner turnInput = new Scanner(System.in);
		System.out.println(current.getName() + "'s turn: ");
		showBoard(board);
		System.out.println("User prompt / input goes around here later");
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
				if(board[y-1][x] != null && board[y-1][x].getMyPlayer()!=attack.getMyPlayer())
				{
					board[y-1][x].setHealth(board[y-1][x].getHealth() - attack.getAttack());
				}
				break;
			case 1: //[y][x+1]
				if(board[y][x+1] != null && board[y][x+1].getMyPlayer()!=attack.getMyPlayer())
				{
					board[y][x+1].setHealth(board[y][x+1].getHealth() - attack.getAttack());
				}
				break;
			case 2: //[y+1][x]
				if(board[y+1][x] != null && board[y+1][x].getMyPlayer()!=attack.getMyPlayer())
				{
					board[y+1][x].setHealth(board[y+1][x].getHealth() - attack.getAttack());
				}
				break;
			case 3: //[y][x-1]
				if(board[y][x-1] != null && board[y][x-1].getMyPlayer()!=attack.getMyPlayer())
				{
					board[y][x-1].setHealth(board[y][x-1].getHealth() - attack.getAttack());
				}
				break;
			}
		}
	}
	
	public static boolean checkWin(Player playerOne, Player playerTwo)
	{
		if(playerOne.getRoundCounter() >= 10)
		{
			if(playerOne.getMyGuys().getTotalHealth()>playerTwo.getMyGuys().getTotalHealth())
			{
				System.out.println(playerOne.getName() + " Wins!");
				return true;
			}
			else if(playerTwo.getMyGuys().getTotalHealth()>playerOne.getMyGuys().getTotalHealth())
			{
				System.out.println(playerTwo.getName() + "Wins!");
				return true;
			}
			else
			{
				System.out.println("Draw!");
				return true;
			}
		}
		return false;
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

					case 1: System.out.printf("* %12s", (board[row][col]==null ? "" : 
						board[row][col].getMyPlayer().getName().length()<13 ? 
						board[row][col].getMyPlayer().getName() : 
						board[row][col].getMyPlayer().getName().substring(0, 12)));
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
