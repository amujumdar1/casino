import java.util.*;

public class Poker {
	
	private static Scanner in;
	
	private Deck hand;
	private Deck deck;
	private Deck splitHand;
	private Test test;
	private int bet;
	
	public Poker(Test test) {
		this.test = test;
		this.deck = new Deck(52);
		this.hand = new Deck(0);
	}
	
	public void driver() {
		in = new Scanner(System.in);
		System.out.println("Hello, " + test.getName() + ". Welcome to Poker.");
		
		
		game();
		
		Set<Card> mySet = new HashSet<Card>(deck);
		
		for (Card card : mySet) {
			System.out.println(card.toString());
		}
		
	}
	
	private void game() {
		System.out.println("You currently have " + test.getChips() + " chips.");
		System.out.print("Place the number of chips you want to bet: ");
		bet = 0;
		boolean validChip = false;
		do {
			try {
				bet = in.nextInt();
				in.nextLine();
				if (bet > test.getChips()) {
					System.out.println("Amount exceeds quantity of chips. Try again.");
				}
				else if (bet < 1) {
					System.out.println("Invalid input. Try again.");
				}
				else validChip = true;
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid input. Try again");
			}
		} while (!validChip);
		
		
		System.out.println("Your first five cards:");
		for (int i = 0; i < 5; i++) {
			Card card = deck.deal();
			System.out.println(card.toString());
			hand.add(card);
		}
		
	}
}