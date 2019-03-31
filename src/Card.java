public class Card implements Comparable<Card> {
	
	enum rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), 
		SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
		
		private int value;
		
		rank(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	enum suit {
		CLUBS, DIAMONDS, HEARTS, SPADES;

	}
	
	private suit suits;
	// which suit it is - spade, diamond, heart, clubs
	
	private rank ranks;
	// the type of card (king, queen, number card, jack)
	
	private int pointValue;
	// what is the numeric value of the card (depends on the game)
	
	// card constructors
	public Card(suit suits, rank ranks) {
		this.suits = suits;
		this.ranks = ranks;
		pointValue = ranks.getValue();
		
	}
	
	// Getters are listed below
	
	public suit suit() {
		return suits;
	}
	
	public rank rank() {
		return ranks;
	}
	
	public int getPointValue() {
		return pointValue;
	}
	
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	// implementing the compareTo method below
	
	@Override
	public int compareTo(Card card) {
		return pointValue - card.getPointValue();
	}
	
	@Override
	public String toString() {
		return "This card is a(n) " + ranks + " of " + suits + " (point value = " + pointValue + ")";
	}

}