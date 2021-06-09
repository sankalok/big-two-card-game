/**
 * The Hand class is a subclass of the CardList class, and is used to model a hand of cards. It
 * has a private instance variable for storing the player who plays this hand. It also has methods
 * for getting the player of this hand, checking if it is a valid hand, getting the type of this hand,
 * getting the top card of this hand, and checking if it beats a specified hand.
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public abstract class Hand extends CardList
{
	/**
	 * Declaring the static final serialVersionUID field of type long as same as in the CardList.java
	 */
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 * The player who plays this hand.
	 */
	private CardGamePlayer player;
	/**
	 * A constructor for building a hand with the specified player and list of cards.
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public Hand(CardGamePlayer player, CardList cards)
	{
		/**
		 * Building a hand with the specified player.
		 */
		this.player = player;
		/**
		 * Counter integer to execute the for loop to add the cards.
		 */
		int i;
		/**
		 * Creating the list of cards used.
		 */
		for(i = 0; i < cards.size(); i++)
		{
			/**
			 * Appends the cards to the end of the list after getting the card at the specified position in the list.
			 */
			this.addCard(cards.getCard(i));
		}
	}
	/**
	 * A method for retrieving the player of this hand.
	 * 
	 * @return CardGamePlayer player
	 * 		Returns the player of this hand.
	 */
	public CardGamePlayer getPlayer()
	{
		return this.player;
	}
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return null
	 * 		Returns the top card of this hand. Returns null as it gets overwritten in the individual subclasses of the Hand
	 * 		class for which the Hand class is a superclass.
	 */
	public Card getTopCard()
	{
		return null;
	}
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand
	 * 		Computes and checks whether this hand beats a specified hand or not.
	 * @return boolean
	 * 		Returns a boolean value depending on whether this hand beats a specified hand or not. 
	 * 		Returns true if it does, and false if it doesn't. 
	 */
	public boolean beats(Hand hand)
	{
		/**
		 * List of legal combinations of Big Two game hands.
		 */
		String[] legalCombos = {"Single", "Pair", "Triple", "Straight", "Flush", "FullHouse", "Quad", "StraightFlush"};
		/**
		 * Single
		 */
		if(((this.size() == 1) && (hand.size() == 1)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[0] && hand.getType() == legalCombos[0])
			{
				/**
				 * Returns Single.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Single combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Pair
		 */
		if(((this.size() == 2) && (hand.size() == 2)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[1] && hand.getType() == legalCombos[1])
			{
				/**
				 * Returns Pair.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Pair combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Triple
		 */
		if(((this.size() == 3) && (hand.size() == 3)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[2] && hand.getType() == legalCombos[2])
			{
				/**
				 * Returns Triple.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Triple combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Straight
		 */
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[3] && hand.getType() == legalCombos[3])
			{
				/**
				 * Returns Straight.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Straight combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Flush
		 */
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[4] && hand.getType() == legalCombos[4])
			{
				/**
				 * Returns Flush.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Flush combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * FullHouse
		 */
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[5] && hand.getType() == legalCombos[5])
			{
				/**
				 * Returns FullHouse.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if FullHouse combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Quad
		 */
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[6] && hand.getType() == legalCombos[6])
			{
				/**
				 * Returns Quad.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if Quad combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * StraightFlush
		 */
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[7] && hand.getType() == legalCombos[7])
			{
				/**
				 * Returns StraightFlush.
				 */
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					/**
					 * Returns true if StraightFlush combination of this hand beats the specified hand.
					 */
					return true;
				}
			}
		}
		/**
		 * Returns false for all other cases.
		 */
		return false;
	}
	/**
	 * The abstract methods: isValid() and getType().
	 */
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	 public abstract boolean isValid();
	 /**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String
	 * 		Returns a String specifying the type of hand.
	 */
	public abstract String getType();
}