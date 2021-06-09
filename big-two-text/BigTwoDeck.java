/**
 * The BigTwoDeck class is a subclass of the Deck class, and is used to model a deck of cards
 * used in a Big Two card game. It overrides the initialize() method it inherits from the
 * Deck class to create a deck of Big Two cards.
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class BigTwoDeck extends Deck
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the Deck.java.
	 */
	private static final long serialVersionUID = -3886066435694112173L;
	/**
	 * A method for initializing a deck of Big Two cards. It should remove all cards from the deck,
	 * create 52 Big Two cards and add them to the deck.
	 */
	public void initialize()
	{
		/**
		 * Removing all cards from the Deck.
		 */
		removeAllCards();
		/**
		 * Integers i and j to iterate 4*13 times to create new 52 Big Two cards.
		 */
		int i, j;
		/**
		 * Creating 52 Big Two cards.
		 */
		for(i = 0; i < 4; i++)
		{
			for(j = 0; j < 13; j++)
			{
				BigTwoCard bigtwocard = new BigTwoCard(i, j);
				/**
				 * Adding the new created bigtwocard to the deck.
				 */
				addCard(bigtwocard);
			}
		}
	}
}
