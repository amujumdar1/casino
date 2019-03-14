import java.util.Scanner;

public class Test {
	
	private static Scanner in;
	private int chips;
	private String name;
	
	public static void main(String[] args) {
		
		Test test = new Test();
		
		in = new Scanner(System.in);
		Blackjack blackjack;
		Poker poker;
		
		System.out.println("Hello, what is your name?");
		test.setName(in.nextLine());
		
		boolean badChip = true;
		do {
			try {
				System.out.println("What is the total amount of chips you have? ($1 = 1 chip)");
				test.setChips(in.nextInt());
				in.nextLine();
				badChip = false;
			}
			catch (NumberFormatException e){
				System.out.println("Invalid amount. Try again.");
			}
		} while (badChip);
		
		boolean going = true;
		int choice = 0;
		do {
			try {
				System.out.println("Press 1 to play Blackjack.\n"
						 + "Press 2 to play Poker.\n"
						 + "Press 3 to play Texas Hold'em.\n"
						 + "Press 4 to cash out and quit.");
				choice = in.nextInt();
				in.nextLine();
		
				switch(choice) {
					case 1:
						blackjack = new Blackjack(test);
						blackjack.driver();
						break;
					case 2:
						poker = new Poker(test);
						poker.driver();
						break;
					case 3:
						break;
					case 4: 
						going = false;
						break;
					default:
						System.out.println("Invalid input.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
			}
		} while (going && test.chips > 0);
		
		if (test.chips < 0) System.out.println("You ran out of chips. Goodbye.");
		
		else System.out.println("You ended up with $" + test.chips + " total. Goodbye.");
		
		in.close();
	}

	public int getChips() {
		return chips;
	}

	public void setChips(int chips) {
		this.chips = chips;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
	