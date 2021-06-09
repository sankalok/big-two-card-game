/**
 * These classes are a subclass of the Hand class, and are used to model a hand of straight flush in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class StraightFlush extends Hand
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the CardList.java
	 */
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 * A constructor for building a Straight Flush hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public StraightFlush(CardGamePlayer player, CardList cards)
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
		 * Creating an array to contain the 5 cards.
		 */
		int[] rankC = {this.getCard(0).getRank(), this.getCard(1).getRank(), this.getCard(2).getRank(), this.getCard(3).getRank(), this.getCard(4).getRank()};
		/**
		 * Integers to execute the for loops.
		 */
		int i, j, rankTemp;
		/**
		 * Changing the ranks according the BigTwo game rules.
		 */
		for(i = 0; i < rankC.length; i++)
		{
			if(rankC[i] == 0)
			{
				rankC[i] = 13;
			}
			else if(rankC[i] == 1)
			{
				rankC[i] = 14;
			}
		}
		/**
		 * Sorting the array rankC.
		 * Implementing Bubble Sorting technique.
		 */
		for(i = 0; i < rankC.length; i++)
		{
			for(j = 0; j < rankC.length - i - 1; j++)
			{
				if(rankC[j] > rankC[j + 1])
				{
					rankTemp = rankC[j];
					rankC[j] = rankC[j + 1];
					rankC[j + 1] = rankTemp;
				}
			}
		}
		/**
		 * Note: We have the highest ranked Card at position 4 of the rankC array.
		 * We transform the rank back into initial rank.
		 */
		for(i = 0; i < rankC.length; i++)
		{
			if(rankC[i] >= 13)
			{
				rankC[i] = rankC[i] - 13;
			}
		}
		/*
		 * Returning the top card.
		 */
		int topCardIndex = -1;
		for(i = 0; i < rankC.length; i++)
		{
			if(this.getCard(i).getRank() == rankC[4])
			{
				topCardIndex = i;
			}
		}
		return this.getCard(topCardIndex);
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
			 * Gets the suit of the five cards.
			 */
			int suitC1 = this.getCard(0).getSuit();
			int suitC2 = this.getCard(1).getSuit();
			int suitC3 = this.getCard(2).getSuit();
			int suitC4 = this.getCard(3).getSuit();
			int suitC5 = this.getCard(4).getSuit();
			/**
			 * Checks if they're equal or not, hence forming a Flush.
			 */
			if((suitC1 == suitC2) && (suitC2 == suitC3) && (suitC3 == suitC4) && (suitC4 == suitC5))
			{
				/**
				 * Creating an array to contain the 5 cards.
				 */
				int[] rankC = {this.getCard(0).getRank(), this.getCard(1).getRank(), this.getCard(2).getRank(), this.getCard(3).getRank(), this.getCard(4).getRank()};
				/**
				 * Integers to execute the for loops.
				 */
				int i, j, rankTemp;
				/**
				 * Changing the ranks according the BigTwo game rules.
				 */
				for(i = 0; i < rankC.length; i++)
				{
					if(rankC[i] == 0)
					{
						rankC[i] = 13;
					}
					else if(rankC[i] == 1)
					{
						rankC[i] = 14;
					}
				}
				/**
				 * Sorting the array rankC.
				 * Implementing Bubble Sorting technique.
				 */
				for(i = 0; i < rankC.length; i++)
				{
					for(j = 0; j < rankC.length - i - 1; j++)
					{
						if(rankC[j] > rankC[j + 1])
						{
							rankTemp = rankC[j];
							rankC[j] = rankC[j + 1];
							rankC[j + 1] = rankTemp;
						}
					}
				}
				/**
				 * Checks whether a straight hand or not.
				 */
				int checkStraight = 0;
				for(i = 0; i < rankC.length - 1; i++)
				{
					if(rankC[i] == (rankC[i+1] - 1))
					{
						checkStraight = checkStraight + 1;
					}
				}
				/**
				 * We return true if checkStraight counter equals 4.
				 */
				if(checkStraight == 4)
				{
					/**
					 * Final check to see whether a straight flush or not.
					 */
					return true;
				}
			}
		}
		/**
		 * We return false otherwise.
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
		 * Returns String specifying the type of hand: StraightFlush.
		 */
		return "StraightFlush";
	}
}
