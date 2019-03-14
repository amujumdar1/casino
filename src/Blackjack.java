import java.util.Scanner;

public class Blackjack {
	
	private static Scanner in;
	
	
	private Deck hand;
	private Deck deck;
	private Deck dealer;
	private Test test;
	private int bet;
	
	public Blackjack(Test test) {
		this.deck = new Deck(52);
		this.hand = new Deck(0);
		this.dealer = new Deck(0);
		this.test = test;
	}
	
	public void driver() {
		in = new Scanner(System.in);
		
		System.out.println("Hello, " + test.getName() + ". Welcome to Blackjack. "
				+ "In this game, if you win, all of your "
				+ "bet chips double.");
		
		game();
		boolean invalid = true;
		int choice = 0;
		do {
			try {
				System.out.println("Press 1 to play again.\n"
						 + "Press 2 to exit Blackjack.");
				choice = in.nextInt();
				in.nextLine();
				switch(choice) {
					case 1:
						deck = new Deck(52);
						hand = new Deck(0);
						dealer = new Deck(0);
						game();
						break;
					case 2:
						invalid = false;
						break;
					default:
						System.out.println("Invalid input. Please try again.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Please try again.");
			}
		} while (invalid);
		
	}
	
	public void game() {
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
		
		
		System.out.println("Your first two cards:");
		System.out.println(this.deal(hand).toString());
		System.out.println(this.deal(hand).toString());
		
		if (getSum(hand) == 21) {
			System.out.println("You win!");
			test.setChips(test.getChips() * 2);
			System.out.println("You just won " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
			return;
		}
		
		System.out.println("Your total point value is: " + getSum(hand));
		
		int input = 0;
		boolean resume = true;
		boolean won = false;
		
		do {
			try {
				System.out.println("Your chip total is " + bet);
				System.out.println("Press 1 to Hit.\nPress 2 to Stand.");
				
				input = in.nextInt();
				in.nextLine();
				
				switch(input) {
					case 1:
						resume = hit();
						if (!resume) {
							System.out.println("Busted!");
							test.setChips(test.getChips() - bet);
							System.out.println("You just lost " + bet + " chips; now your total is "
									 + test.getChips() + " chips.");
						}
						else if (getSum(hand) == 21) {
							System.out.println("You win!");
							test.setChips(test.getChips() + bet);
							System.out.println("You just won " + bet + " chips; now your total is "
									 + test.getChips() + " chips.");
						}
						break;
					case 2:
						resume = false;
						won = stand();
						if (won) {
							System.out.println("You win! Your total sum is: " + getSum(hand) + ". The dealer's sum is: "
									+ getSum(dealer));
							test.setChips(test.getChips() + bet);
							System.out.println("You just won " + bet + " chips; now your total is "
									 + test.getChips() + " chips.");
						}
						else {
							System.out.println("You lose! Your total sum is: " + getSum(hand) + ". The dealer's sum is: "
									+ getSum(dealer));
							test.setChips(test.getChips() - bet);
							System.out.println("You just lost " + bet + " chips; now your total is "
									 + test.getChips() + " chips.");
						}
						break;
					default:
						System.out.println("Invalid input. Try again.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
			}
		} while (resume);
		
	}
	
	public boolean hit() {
		Card card = this.deal(hand);
		System.out.println(card.toString());
		if (deck.isEmpty()) deck.shuffle();
		System.out.println("Your current sum is: " + getSum(hand));
		return getSum(hand) <= 21;
	}
	
	public boolean stand() {
		do {
			deal(dealer);
		} while (getSum(dealer) < 17);
		if (getSum(dealer) > 21) return true;
		return getSum(hand) >= getSum(dealer);
	}
	
	public Card deal(Deck deck) {
		Card card = this.deck.deal();
		if (card.rank().getValue() == 1) oneValue(card, deck);
		else if (card.getPointValue() > 10) card.setPointValue(10);
		deck.add(card);
		return card;
	}
	
	public void oneValue(Card card, Deck deck) {
		if (getSum(deck) + 10 <= 21) {
			card.setPointValue(11);
		}
		else if (getSum(deck) <= 21) {
			card.setPointValue(1);
		}
	}
	
	public int getSum(Deck deck) {
		int sum = 0;
		for (Card value : deck) {
			sum += value.getPointValue();
		}
		return sum;
	}
	
		
}