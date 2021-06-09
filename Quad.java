/**
 * These classes are a subclass of the Hand class, and are used to model a hand of quad in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class Quad extends Hand
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the CardList.java
	 */
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 * A constructor for building a Quad hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public Quad(CardGamePlayer player, CardList cards)
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
		 * Case 1: Compare the first 4.
		 */
		if(this.getCard(0).getRank() == this.getCard(1).getRank())
		{
			if((this.getCard(1).getRank() == this.getCard(2).getRank()) && (this.getCard(2).getRank() == this.getCard(3).getRank()))
			{
				if(this.getCard(0).getSuit() == 3)
				{
					return this.getCard(0);
				}
				else if(this.getCard(1).getSuit() == 3)
				{
					return this.getCard(1);
				}
				else if(this.getCard(2).getSuit() == 3)
				{
					return this.getCard(2);
				}
				else
				{
					return this.getCard(3);
				}
			}
		}
		/**
		 * Case 2: Compare the second 4.
		 */
		if(this.getCard(1).getRank() == this.getCard(2).getRank())
		{
			if((this.getCard(2).getRank() == this.getCard(3).getRank()) && (this.getCard(3).getRank() == this.getCard(4).getRank()))
			{
				if(this.getCard(1).getSuit() == 3)
				{
					return this.getCard(0);
				}
				else if(this.getCard(2).getSuit() == 3)
				{
					return this.getCard(1);
				}
				else if(this.getCard(3).getSuit() == 3)
				{
					return this.getCard(2);
				}
				else
				{
					return this.getCard(3);
				}
			}
		}
		/**
		 * The top card gets returned on satisfying any of the above cases else we return null.
		 */
		return null;
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
		 * Returning false if Hand doesn't contain 5 cards.
		 */
		if(this.size() == 5)
		{
			/**
			 * Sorting the hand according to the order of the cards.
			 */
			this.sort();
			/**
			 * Case 1: Compare the first 4.
			 */
			if(this.getCard(0).getRank() == this.getCard(1).getRank())
			{
				if((this.getCard(1).getRank() == this.getCard(2).getRank()) && (this.getCard(2).getRank() == this.getCard(3).getRank()))
				{
					return true;
				}
			}
			/**
			 * Case 2: Compare the second 4.
			 */
			if(this.getCard(1).getRank() == this.getCard(2).getRank())
			{
				if((this.getCard(2).getRank() == this.getCard(3).getRank()) && (this.getCard(3).getRank() == this.getCard(4).getRank()))
				{
					return true;
				}
			}
		}
		/**
		 * Returns false otherwise.
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
		 * Returns String specifying the type of hand: Quad.
		 */
		return "Quad";
	}
}