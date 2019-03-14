import java.util.Scanner;

public class Poker {
	
	private static Scanner in;
	
	private Deck hand;
	private Deck deck;
	private Test test;
	
	public Poker(Test test) {
		this.test = test;
		this.deck = new Deck(52);
		this.hand = new Deck(0);
	}
	
	public void driver() {
		in = new Scanner(System.in);
	}
}