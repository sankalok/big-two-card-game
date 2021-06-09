/**
 * These classes are a subclass of the Hand class, and are used to model a hand of full house in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class FullHouse extends Hand
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the CardList.java
	 */
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 * A constructor for building a FullHouse hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public FullHouse(CardGamePlayer player, CardList cards)
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
		 * We compare card 0 with card 2 and card 2 with 4 because for them to be FullHouse either
		 * of the two possibilities have to be equal to form one of the Triple of the Suit.
		 */
		int checkFullHouse = -1;
		if(this.getCard(0).getRank() == this.getCard(2).getRank())
		{
			checkFullHouse = 2;
		}
		else if(this.getCard(2).getRank() == this.getCard(4).getRank())
		{
			checkFullHouse = 4;
		}
		/**
		 * Returns the top card after checking.
		 */
		return this.getCard(checkFullHouse);
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
			 * Gets the Rank of the five cards.
			 */
			int rankC1 = this.getCard(0).getRank();
			int rankC2 = this.getCard(1).getRank();
			int rankC3 = this.getCard(2).getRank();
			int rankC4 = this.getCard(3).getRank();
			int rankC5 = this.getCard(4).getRank();
			/**
			 * Case 1: Triple first 3 and Pair next 2.
			 */
			if(rankC2 == rankC3)
			{
				if(rankC1 == rankC2)
				{
					if(rankC4 == rankC5)
					{
						/**
						 * Checking for the Pair Second.
						 */
						return true;
					}
				}
			}
			/**
			 * Case 2: Pair first 2 and Triple next 3.
			 */
			if(rankC3 == rankC4)
			{
				if(rankC4 == rankC5)
				{
					if(rankC1 == rankC2)
					{
						/**
						 * Checking for the Pair First.
						 */
						return true;
					}
				}
			}
		}
		/**
		 * Returns false if they do not form a full house.
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
		 * Returns String specifying the type of hand: FullHouse.
		 */
		return "FlushHouse";
	}
}
