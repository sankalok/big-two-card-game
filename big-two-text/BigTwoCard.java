/**
 * The BigTwoCard class is a subclass of the Card class, and is used to model a card used in a
 * Big Two card game. It overrides the compareTo() method it inherits from the Card class
 * to reflect the ordering of cards used in a Big Two card game.
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class BigTwoCard extends Card
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the Card.java.
	 */
	private static final long serialVersionUID = -713898713776577970L;
	/**
	 * A constructor for building a card with the specified suit and rank. 
	 * 
	 * @param integer suit
	 * 		Suit is an integer between 0 and 3.
	 * 		0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade.
	 * @param integer rank
	 * 		Rank is an integer between 0 and 12.
	 * 		0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 = 'Q', 12 = 'K'.
	 */
	public BigTwoCard(int suit, int rank)
	{
		/**
		 * Accesses the superclass Card's constructor.
		 */
		super(suit, rank);
	}
	/**
	 * A method for comparing the order of this card with the specified card. Returns a negative
	 * integer, zero, or a positive integer as this card is less than, equal to, or greater than the 
	 * specified card.
	 *  
	 * @param Card card
	 *		Stores the card to be compared.
	 * @return integer
	 * 		Returns a negative integer, zero, or a positive integer as this card is
	 *      less than, equal to, or greater than the specified card
	 */
	public int compareTo(Card card)
	{
		/**
		 * This (this.rank) rank.
		 */
		int thisRank = this.rank;
		/**
		 * The specified (card.rank) rank.
		 */
		int specifiedRank = card.rank;
		/**
		 * Changing thisRank to reflect the ordering in a Big Two game.
		 * The order of ranks from high to low is 2, A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3.
		 */
		if(thisRank == 0)
		{
			thisRank = 13;
		}
		if(thisRank == 1)
		{
			thisRank = 14;
		}
		/**
		 * Changing specifiedRank to reflect the ordering in a Big Two game.
		 * The order of ranks from high to low is 2, A, K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3.
		 */
		if(specifiedRank == 0)
		{
			specifiedRank = 13;
		}
		if(specifiedRank == 1)
		{
			specifiedRank = 14;
		}
		/**
		 * compareTo() function of Card.java.
		 */
		if(thisRank > specifiedRank)
		{
			return 1;
		}
		else if(thisRank < specifiedRank)
		{
			return -1;
		}
		else if(this.suit > card.suit)
		{
			return 1;
		}
		else if(this.suit < card.suit)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
