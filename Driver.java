import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException
	{

		Scanner input = new Scanner(System.in);
		
		Soldier[][] board = new Soldier[4][4];
		
		System.out.println("Melee units are high attack with high health low range.");
		System.out.println("Ranged units have low attack, low health, but high range.");
		System.out.println("Banner units have low attack, high health, and low range. They are used to link melee units into a phalanx");
		System.out.println("Phalanx units attack and move as one powerful group.");
		
		Player playerOne = new Player();
		Player playerTwo = new Player();
		
		System.out.println("Enter Player One's Name: ");
		playerOne.setName(input.nextLine());
		
		System.out.println("Enter Player Two's Name: ");
		playerTwo.setName(input.nextLine());
		
		boolean End = false;
		
		Player current = playerOne;
		
		System.out.println(current.getName() + "'s turn. Please select a unit.");
		
		showBoard(board);
		
		do {
			
		}while (End != true);
		
		
		
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
					case 1: System.out.printf("*%14s", (board[row][col]==null?"":board[row][col].getMyPlayer().getName()));
						break;
					case 2: System.out.printf("*%14s", (board[row][col]==null?"":board[row][col].getHealth()));
						break;
					case 3: System.out.printf("*%14s", (board[row][col]==null?"":board[row][col].getAttack()));
						break;
					case 4: System.out.printf("*%14s", (board[row][col]==null?"":board[row][col].getRange()));
						break;
					case 5: System.out.printf("*%14s", (board[row][col]==null?"":board[row][col].getMyPlayer().findMe(board[row][col])));
						break;
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