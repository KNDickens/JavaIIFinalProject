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
		
		System.out.println("Enter Player Two's Name: ");
		playerTwo.setName(input.nextLine());
		
		boolean End = true;
		
		Player current = playerOne;
		
		System.out.println(current.getName() + "'s turn. Please select a unit.");
		
		showBoard(board);
		
		do {
			if(current == playerOne)
			{
				playTurn(current, board);
				checkWin(playerOne, playerTwo);
				current.roundIncremenet();
				current = playerTwo;

			}
			else
			{
				playTurn(current, board);
				checkWin(playerOne, playerTwo);
				current.roundIncremenet();
				current = playerOne;
			}
		}while (End != true);
	}

	public static Player playTurn(Player current, Soldier[][] board)
	{
		Scanner turnInput = new Scanner(System.in);
		System.out.println(current.getName() + "'s turn: ");
		showBoard(board);
		System.out.println("User prompt / input goes around here later");
		return null;
	}
	
	
	
	public static boolean checkWin(Player playerOne, Player playerTwo)
	{
		if(playerOne.getRoundCounter() >= 5)
		{
			if(playerOne.getMyGuys().getTotalHealth()>playerTwo.getMyGuys().getTotalHealth())
			{
				System.out.println(playerOne.getName() + " Wins!");
				return true;
			}
			else if(playerTwo.getTotalHealth()>playerOne.getTotalHealth())
			{
				System.out.println(playerTwo.getName() + "Wins!");
				return true;
			}
			else(playerTwo.getTotalHealth()==playerOne.getTotalHealth())
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
					case 1: System.out.printf("*%14s", (board[row][col]==null ? "" : 
						board[row][col].getMyPlayer().getName().length()<15 ? 
						board[row][col].getMyPlayer().getName() : 
						board[row][col].getMyPlayer().getName().substring(0, 14)));
						break;
					case 2: System.out.printf("*%14s", (board[row][col]==null ? "" : board[row][col].getHealth()));
						break;
					case 3: System.out.printf("*%14s", (board[row][col]==null ? "" : board[row][col].getAttack()));
						break;
					case 4: System.out.printf("*%14s", (board[row][col]==null ? "" : board[row][col].getRange()));
						break;
					case 5: System.out.printf("*%14s", (board[row][col]==null ? "" : "Unit #: " + board[row][col].getMyPlayer().findMe(board[row][col])));
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
