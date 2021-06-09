/**
 * These classes are a subclass of the Hand class, and are used to model a hand of pair in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class Pair extends Hand
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the CardList.java
	 */
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 * A constructor for building a Pair hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public Pair(CardGamePlayer player, CardList cards)
	{
		/**
		 * Defining the implicit super constructor Hand(), so explicitly invoking another constructor.
		 */
		super(player, cards);
	}
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card
	 * 		Returns the top card of this hand.
	 */
	public Card getTopCard()
	{
		/**
		 * Sorting the hand according to the order of the cards.
		 */
		this.sort();
		/**
		 * Returning the top card of the current hand.
		 */
		return this.getCard(1);
	}
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	public boolean isValid()
	{
		/**
		 * Checks whether the size of the hand equals to 2.
		 */
		if(this.size() == 2)
		{
			/**
			 * Finds the rank of the two cards.
			 */
			int rankC1 = this.getCard(0).getRank();
			int rankC2 = this.getCard(1).getRank();
			/**
			 * Checks if they're equal or not, hence forming a Pair.
			 */
			if(rankC1 == rankC2)
			{
				/**
				 * Returns true if they form a Pair.
				 */
				return true;
			}
			else
			{
				/**
				 * Returns false if they do not form a Pair.
				 */
				return false;
			}
		}
		/**
		 * Returns false if the size doesn't equal to 2, hence its not possible to be a Pair.
		 */
		return false;
	}
	/**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String
	 * 		Returns a String specifying the type of hand.
	 */
	public String getType()
	{
		/**
		 * Returns String specifying the type of hand: Pair.
		 */
		return "Pair";
	}
}