import java.util.ArrayList;



public class Deck extends ArrayList<Card> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int stillLeft;

	public Deck(int size) {
		super(size);
		if (size == 52) createDeck();
	}
	
	/**
	 * 
	 */
	public void createDeck() {
		for (Card.rank rank : Card.rank.values()) {
			for (Card.suit suit : Card.suit.values()) {
				this.add(new Card(suit, rank));
			}
		}
		stillLeft = size();
		shuffle();
	}
	
	public Card deal() {
		if (isEmpty()) return null;
		return this.get(--stillLeft);
		
	}
	
	public boolean isEmpty() {
		return stillLeft <= 0;
	}
	
	public void shuffle() {
		for (int k = size() - 1; k > 0; k--) {
			int howMany = k + 1;
			int start = 0;
			int randPos = (int) (Math.random() * howMany) + start;
			Card temp = this.get(k);
			this.set(k, this.get(randPos));
			this.set(randPos, temp);
		}
		stillLeft = size();
	}
}