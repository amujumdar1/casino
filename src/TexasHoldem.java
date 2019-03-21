import java.util.*;

public class TexasHoldem {
	
	private static Scanner in;
	
	private Deck hand;
	private Deck deck;
	private Deck dealer;
	private Test test;
	private int bet;
	
	private TexasHoldem(Test test) {
		deck = new Deck(52);
		hand = new Deck(0);
		dealer = new Deck(0);
		this.test = test;
	}
	
	public void driver() {
		in = new Scanner(System.in);
		
		System.out.println("Hello, " + test.getName() + " . Welcome to Texas Hold'em.");
		game();
	}
	
	
	private void game() {
		
	}
}