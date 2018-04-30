import java.io.IOException;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException
	{

		Scanner input = new Scanner(System.in);
		
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
		
		do {
			
		}while (End != true);
		
		
		
	}
}
