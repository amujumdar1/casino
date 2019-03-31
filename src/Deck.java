import java.util.ArrayList;



public class Deck extends ArrayList<Card>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int stillLeft;

	public Deck(int size) {
		super(size);
		if (size == 52) {
			createDeck();
		}
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
		if (isEmpty()) {
			return null;
		}
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
	
	
	/*public Deck merge(Deck deck) {
		sort(deck, 0, deck.stillLeft - 1);
		return deck;
	}
	    
	private void sort(Deck deck, int i, int j) {
		if (j - i < 1) return;
		int middle = (i + j) / 2;
		
		sort(deck, i, middle);
		sort(deck, middle + 1, j);
		
		merging(deck, i, middle, j);
	}

	private void merging(Deck deck, int p, int mid, int q) {

		Deck tempDeck = new Deck(q-p+1); 
		
		int i = p;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= q) {
		    if (deck.get(i).compareTo(deck.get(j)) <= 0)
			tempDeck.set(k, deck.get(i++));
		    else
		    tempDeck.set(k, deck.get(j++));
		    k++;
		}
		if (i <= mid && j > q) {
		    while (i <= mid) 
			tempDeck.set(k++, deck.get(i++));
		} 
		else {
		    while (j <= q)
		    	tempDeck.set(k++, deck.get(j++));
		}
		for (k = 0; k < tempDeck.stillLeft; k++) {
		    deck.set(k + p , tempDeck.get(k));
		}
	}*/

}