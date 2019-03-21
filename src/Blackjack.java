import java.util.Scanner;

public class Blackjack {
	
	private static Scanner in;
	
	
	private Deck hand;
	private Deck deck;
	private Deck splitHand;
	private Deck dealer;
	private Test test;
	private int bet;
	
	public Blackjack(Test test) {
		deck = new Deck(52);
		hand = new Deck(0);
		dealer = new Deck(0);
		this.test = test;
	}
	
	public void driver() {
		in = new Scanner(System.in);
		
		System.out.println("Hello, " + test.getName() + ". Welcome to Blackjack.");
		
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
					
				} else if (bet < 1) {
					System.out.println("Invalid input. Try again.");
					
				}else {
					validChip = true;
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid input. Try again");
			}
		} while (!validChip);
		
		
		System.out.println("Your first two cards:");
		
		Card firstCard = this.deal(hand);
		Card secondCard = this.deal(hand);
		
		System.out.println(firstCard.toString());
		System.out.println(secondCard.toString());
		
		System.out.println("The dealer's first card: ");
		
		Card firstDealer = this.deal(dealer);
		
		System.out.println(firstDealer.toString());
		
		if (getSum(hand) == 21) {
			System.out.println("Blackjack! You win!");
			test.setChips(test.getChips() + bet);
			System.out.println("You just won " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
			return;
		}
		
		System.out.println("Your total point value is: " + getSum(hand));
		
		int input = 0;
		boolean resume = true;
		do {
			try {
				System.out.println("Your chip total is " + bet);
				System.out.println("Press 1 to Hit.\n"
								 + "Press 2 to Stand.\n"
								 + "Press 3 to Double Down.\n"
								 + ((firstCard.compareTo(secondCard) == 0) ? "Press 4 to Split." : ""));
				
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
						
					} else if (getSum(hand) == 21) {
						System.out.println("You win!");
						test.setChips(test.getChips() + bet);
						System.out.println("You just won " + bet + " chips; now your total is "
								 + test.getChips() + " chips.");
						resume = false;
					}
					break;
				case 2:
					resume = false;
					stand();
					break;
				case 3: 
					doubleDown();
					resume = false;
					break;
				case 4: 
					if (firstCard.compareTo(secondCard) == 0) {
						splitHand = new Deck(1);
						splitHand.add(secondCard);
						hand.remove(secondCard);
						split();
					} else {
						System.out.println("Invalid input. Try again.");
					}
					break;
				default:
					System.out.println("Invalid input. Try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
			}
		} while (resume);
		
	}
	
	private boolean hit() {
		Card card = this.deal(hand);
		System.out.println(card.toString());
		if (deck.isEmpty()) {
			deck.shuffle();
		}
		System.out.println("Your current sum is: " + getSum(hand));
		if (getSum(hand) > 21) {
			for (Card cards : hand) {
				if (cards.getPointValue() == 11) {
					cards.setPointValue(1);
					if (getSum(hand) <= 21) {
						break;
					}
				}
			}
		}
		return getSum(hand) <= 21;
	}
	
	private void stand() {
		do {
			deal(dealer);
		} while (getSum(dealer) < 17);
		
		boolean won = (getSum(dealer) > 21) ? true : getSum(hand) >= getSum(dealer);
		
		System.out.println("These are your cards: \n");
		
		for (Card card : dealer) {
			System.out.println(card.toString());
		}
		
		System.out.println("\nThese are the dealer's cards:");
		
		for (Card card : dealer) {
			System.out.println(card.toString());
		}
		
		if (won) {
			System.out.println("You win! Your total sum is: " + getSum(hand) + ". The dealer's sum is: "
					+ getSum(dealer));
			test.setChips(test.getChips() + bet);
			System.out.println("You just won " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
		} else {
			System.out.println("You lose! Your total sum is: " + getSum(hand) + ". The dealer's sum is: "
					+ getSum(dealer));
			test.setChips(test.getChips() - bet);
			System.out.println("You just lost " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
		}
	}
	
	private Card deal(Deck deck) {
		Card card = this.deck.deal();
		if (card.rank().getValue() == 1) {
			oneValue(card, deck);
		} else if (card.getPointValue() > 10) {
			card.setPointValue(10);
		}
		deck.add(card);
		return card;
	}
	
	private void doubleDown() {
		boolean still = true;
		int percent = 0;
		do {
			try {
				System.out.println("By what percent do you want to increase your bet? "
						+ "Pick a value between 1 and 100 (inclusive).");
				percent = in.nextInt();
				in.nextLine();
				if (percent > 100 || percent < 1) {
					System.out.println("Invalid input. Try again.");
				} else {
					still = false;
				}
			} catch (NumberFormatException e) {
				System.out.println("invalid input. Try again.");
			}
		} while(still);
		
		bet += bet * percent / 100;
		
		boolean resume = hit();
		if (!resume) {
			System.out.println("Busted!");
			test.setChips(test.getChips() - bet);
			System.out.println("You just lost " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
			return;
		} else if (getSum(hand) == 21) {
			System.out.println("You win!");
			test.setChips(test.getChips() + bet);
			System.out.println("You just won " + bet + " chips; now your total is "
					 + test.getChips() + " chips.");
			return;
		}
		stand();
	}
	
	private void split() {
		
	}
	
	private void oneValue(Card card, Deck deck) {
		if (getSum(deck) + 11 <= 21) {
			card.setPointValue(11);
		} else if (getSum(deck) + 1 <= 21) {
			card.setPointValue(1);
		}
	}
	
	private int getSum(Deck deck) {
		int sum = 0;
		for (Card value : deck) {
			sum += value.getPointValue();
		}
		return sum;
	}
	
		
}