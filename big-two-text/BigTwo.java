import java.util.ArrayList;
/**
 * The BigTwo class is used to model a Big Two card game. It has private instance variables for
 * storing a deck of cards, a list of players, a list of hands played on the table, an index of the 
 * current player, and a console for providing the user interface.
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class BigTwo
{
	/**
	 * A deck of cards.
	 */
	private Deck deck;
	/**
	 * A list of players.
	 */
	private ArrayList<CardGamePlayer> playerList;
	/**
	 * A list of hands played on the table.
	 */
	private ArrayList<Hand> handsOnTable;
	/**
	 * An integer specifying the index of the current player.
	 */
	private int currentIdx;
	/**
	 * A BigTwoConsole object for providing the user interface.
	 */
	private BigTwoConsole bigTwoConsole;
	/**
	 * A constructor for creating a Big Two card game. Created 4 
	 * players and add them to the player list. Created a ‘console’ (i.e., a 
	 * BigTwoConsole object) for providing the user interface.
	 */
	public BigTwo()
	{
		/**
		 * Initializing the new playerList.
		 */
		playerList = new ArrayList<CardGamePlayer>();
		/**
		 * Integer to iterate through the ArrayList for adding 4 players.
		 */
		int i;
		/**
		 * Creating 4 players and adding them to the ArrayList.
		 */
		for(i = 0; i <= 3; i++)
		{
			/**
			 * Creating new player for each iteration and adding them to the playerList.
			 */
			CardGamePlayer newPlayer = new CardGamePlayer();
			playerList.add(newPlayer);
		}
		/**
		 * Initializing the new handsOnTable.
		 */
		handsOnTable = new ArrayList<Hand>();
		/**
		 * Creating a console for providing the user interface.
		 */
		bigTwoConsole = new BigTwoConsole(this);
	}
	/**
	 * A method for retrieving the deck of cards being used.
	 * Getter Method.
	 * 
	 * @return Deck
	 * 		Returns the Deck deck object.
	 */
	public Deck getDeck()
	{
		return this.deck;
	}
	/**
	 * A method for retrieving the list of players.
	 * Getter Method.
	 * 
	 * @return ArrayList<CardGamePlayer>
	 * 		Returns the list of players.
	 */
	public ArrayList<CardGamePlayer> getPlayerList()
	{
		return this.playerList;
	}
	/**
	 * A method for retrieving the list of hands played on the table.
	 * Getter Method.
	 * 
	 * @return ArrayList<Hand>
	 * 		Returns the list of hands played.
	 */
	public ArrayList<Hand> getHandsOnTable()
	{
		return this.handsOnTable;
	}
	/**
	 * A method for retrieving the index of the current player.
	 * Getter Method.
	 * 
	 * @return integer index currentIdx
	 * 		Returns index of the current player.
	 */
	public int getCurrentIdx()
	{
		return this.currentIdx;
	}
	/**
	 * A method for starting the game with a (shuffled) deck 
	 * of cards supplied as the argument. It implements the Big Two game logics.
	 * 
	 * @param BigTwoDeck deck
	 * 		A shuffled deck of cards used as argument to start the game.
	 */
	public void start(BigTwoDeck deck)
	{
		/**
		 * Iterators integers to help in running the loops.
		 */
		int i, j;
		/**
		 * Distributing the deck between the four players.
		 */
		for(i = 0; i < 4; i++)
		{
			for(j = 0; j < 13; j++)
			{
				/**
				 * ith player gets 13 cards each.
				 */
				playerList.get(i).addCard(deck.getCard((13 * i) + j));
			}
		}
		/**		
		 * Sorts the cards for the four players as per the output requirements.
		 * Sorted from lowest rank to highest rank, and for same ranks, from lowest to highest suits.
		 */
		for(i = 0; i < 4; i++)
		{
			/**
			 * Cards get sorted following the BigTwo rule requirements.
			 */
			playerList.get(i).getCardsInHand().sort();
		}
		/**
		 * Starting player index for each iteration and index of the player who played before the first/current player.
		 */
		int startingPlayerIndex = -1;
		int playerIndex = -1;
		/**
		 * The 3 of Diamonds cards, the player who has it starts the game.
		 */
		Card threeOfDiamonds = new Card(0, 2);
		/**
		 * Executing a for loop and finding the player who has 3 of Diamonds, who shall start the game.
		 */
		for(i = 0; i < 4; i++)
		{
			/**
			 * The player with the 3 of Diamonds starts the game, so stores the index of the
			 * starting player.
			 */
			if(playerList.get(i).getCardsInHand().contains(threeOfDiamonds))
			{
				startingPlayerIndex = i;
			}
		}
		/**
		 * Setting the index of the first active player with 3 of Diamonds to start the game.
		 */
		bigTwoConsole.setActivePlayer(startingPlayerIndex);
		/**
		 * Boolean which remains true while the game lasts and turns false when the game has ended.
		 */
		boolean theGame = true;
		/**
		 * Boolean which remains true which a legal hand is placed else turns false once we find an
		 * illegal hand has been played.
		 */
		boolean legalHand = true;
		/**
		 * CardList created to store the list of cards of the current player playing his/her hand.
		 */
		CardList cardList = new CardList();
		/**
		 * The game.
		 */
		while(theGame == true)
		{
			/**
			 * Repaints the console for the first instance, and after each instance depends on whether a
			 * legal hand has been played or not.
			 */
			if(legalHand == true)
			{
				/**
				 * At each player’s turn, the program should print out the cards held by each player 
				 * as well as the last hand played on the table, by calling the repaint() method of 
				 * the BigTwoConsole object.
				 */
				bigTwoConsole.repaint();
			}
			/**
			 * At each player’s turn, the program reads from the keyboard a space-separated list of
			 * indices that represents the list of cards played by the player, by calling
			 * the getSelected() method of the BigTwoConsole object.
			 */
			int[] handPlayed = bigTwoConsole.getSelected();
			/**
			 * If a null hand hasn't been played.
			 */
			if(handPlayed != null)
			{
				/**
				 * Returns the list of cards played by the player.
				 */
				cardList = playerList.get(startingPlayerIndex).play(handPlayed);
				/**
				 * Returns a valid hand from the specified cardlist, else returns a null value.
				 */
				Hand hand = composeHand(playerList.get(startingPlayerIndex), cardList);
				/**
				 * If the table is empty, that is, at the start of the game.
				 */
				if(handsOnTable.isEmpty() == true)
				{
					/**
					 * Hand containing three of diamonds.
					 */
					if((hand.contains(threeOfDiamonds)) && (hand.isValid() == true))
					{
						legalHand = true;						
					}
					else
					{
						legalHand = false;
					}
				}
				/**
				 * If the table is non empty.
				 */
				else if(handsOnTable.isEmpty() == false)
				{
					/**
					 * When the starting move has been already played so player index does not
					 * equal the index of the starting player of the round.
					 */
					if(playerIndex != startingPlayerIndex)
					{
						/**
						 * Checks if the hand played can beat the hand present on the table or not.
						 */
						legalHand = hand.beats(handsOnTable.get(handsOnTable.size() - 1));
					}
					else
					{
						/**
						 * LegalHand becomes true since here the player index == starting player index.
						 */
						legalHand = true;
					}
				}
				/**
				 * If the legal hand is played and the hand is valid.
				 */
				if((legalHand == true) && (hand.isValid() == true))
				{
					/**
					 * We consider that a move has been finished and now the player index equals that
					 * of the starting player.
					 */
					playerIndex = startingPlayerIndex;
					/**
					 * Executing a for loop to check if all the players still have cards left or not.
					 */
					for(i = 0; i < cardList.size(); i++)
					{
						/**
						 * Removes the cards one by one after a legal hand has been played.
						 */
						playerList.get(playerIndex).getCardsInHand().removeCard(cardList.getCard(i));
					}
					/**
					 * If one of the players has zero cards left, then the game ends.
					 */
					if(playerList.get(playerIndex).getCardsInHand().isEmpty() == true)
					{
						theGame = false;
					}
					/**
					 * We add the hand played to the hands on table.
					 */
					handsOnTable.add(hand);
					/**
					 * Priniting the hand and type of hand played.
					 */
					System.out.println("{" + hand.getType() + "} " + hand);
					System.out.println();
					/**
					 * Changes the starting player index so now the next player can play his/her move.
					 */
					startingPlayerIndex = (startingPlayerIndex + 1);
					if(startingPlayerIndex > 3)
					{
						startingPlayerIndex = startingPlayerIndex - 4;
					}
					/**
					 * Set the Active Player to the new Active Player.
					 */
					bigTwoConsole.setActivePlayer(startingPlayerIndex);
				}
				else
				{
					/**
					 * Else, we find that an illegal move has been played.
					 */
					System.out.println("Not a legal move!!!");
				}
			}
			/**
			 * If the hand played is null.
			 */
			else if(handPlayed == null)
			{
				/**
				 * If the hands on table is not empty and starting player index not equals to player index,
				 * it means some moves of hands have already been played, and now a null hand has been played,
				 * signifying a Pass by the current player to shift play to the next player.
				 */
				if((handsOnTable.isEmpty() == false) && (startingPlayerIndex != playerIndex))
				{
					/**
					 * Pass played, shifting the move to the next player.
					 */
					System.out.println("{Pass}");
					System.out.println();
					/**
					 * Changes the starting player index so now the next player can play his/her move.
					 */
					startingPlayerIndex = (startingPlayerIndex + 1);
					if(startingPlayerIndex > 3)
					{
						startingPlayerIndex = startingPlayerIndex - 4;
					}
					/**
					 * Set the Active Player to the new Active Player.
					 */
					bigTwoConsole.setActivePlayer(startingPlayerIndex);
					/**
					 * The null hand is still legal as the game continues to next player as is and the current
					 * player has just passes his or her hand.
					 */
					legalHand = true;
				}
				else
				{
					/**
					 * Else, we find that an illegal move has been played.
					 */
					System.out.println("Not a legal move!!!");
				}
			}
		}
		/**
		 * The game has ended.
		 */
		if(theGame == false)
		{
			/**
			 * The index of the active player has been set to less than 0 signifying the end of the game.
			 */
			bigTwoConsole.setActivePlayer(-1);
			bigTwoConsole.repaint();
			/**
			 * Printing the end of the game status.
			 */
			System.out.println();
			System.out.println("Game ends");
			/**
			 * Executing the for loop to show the ending statistics and who won.
			 */
			for(i = 0; i < playerList.size(); i++)
			{
				if(playerList.get(i).getCardsInHand().size() == 0)
				{
					/**
					 * Player who won.
					 */
					System.out.println("Player " + i + " wins the game.");
				}
				else
				{
					/**
					 * How many cards each of the other 3 players still hold in their hands.
					 */
					System.out.println("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand.");
				}
			}
		}
	}
	/**
	 * A method for returning a valid hand from the specified list of cards of the player. 
	 * Returns "null" is no valid hand can be composed from the specified list of cards.
	 * 
	 * @return Hand
	 * 		Returns the hand if its a valid hand and null otherwise. 
	 * @param CardGamePlayer player
	 * 		Object which consists of a list of the players in the game.
	 * @param CardList cards
	 * 		Object which consists of the list of the cards being played by the current active player.
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards)
	{
		/**
		 * Creating a Single object.
		 */
		Single single = new Single(player, cards);
		/**
		 * Creating a Pair object.
		 */
		Pair pair = new Pair(player, cards);
		/**
		 * Creating a Triple object.
		 */
		Triple triple = new Triple(player, cards);
		/**
		 * Creating a Straight object.
		 */
		Straight straight = new Straight(player, cards);
		/**
		 * Creating a Flush object.
		 */
		Flush flush = new Flush(player, cards);
		/**
		 * Creating a FullHouse object.
		 */
		FullHouse fullhouse = new FullHouse(player, cards);
		/**
		 * Creating a Quad object.
		 */
		Quad quad = new Quad(player, cards);
		/**
		 * Creating a StraightFlush object.
		 */
		StraightFlush straightflush = new StraightFlush(player, cards);
		/**
		 * If straightflush is valid, returns straightflush.
		 */
		if(straightflush.isValid())
		{
			return straightflush;
		}
		/**
		 * If quad is valid, returns quad.
		 */
		else if(quad.isValid())
		{
			return quad;
		}
		/**
		 * If fullhouse is valid, returns fullhouse.
		 */
		else if(fullhouse.isValid())
		{
			return fullhouse;
		}
		/**
		 * If flush is valid, returns flush.
		 */
		else if(flush.isValid())
		{
			return flush;
		}
		/**
		 * If straight is valid, returns straight.
		 */
		else if(straight.isValid())
		{
			return straight;
		}
		/**
		 * If triple is valid, returns triple.
		 */
		else if(triple.isValid())
		{
			return triple;
		}
		/**
		 * If pair is valid, returns pair.
		 */
		else if(pair.isValid())
		{
			return pair;
		}
		/**
		 * If single is valid, returns pair.
		 */
		else if(single.isValid())
		{
			return single;
		}
		/**
		 * Returns a null hand when no valid hand can be made from the specified list of cards.
		 */
		else
		{
			return null;
		}
	}
	/**
	 * A method for starting a Big Two card game. It creates a Big Two card game, creates and shuffles
	 * a deck of cards, and starts the game with the deck of cards.
	 * 
	 * @param args
	 * 		Not used in this BigTwo class.
	 */
	public static void main(String[] args)
	{
		/**
		 * Creating a BigTwo object for starting the game.
		 */
		BigTwo bigtwo = new BigTwo();
		/**
		 * Creating a BigTwoDeck object for starting the game.
		 */
		BigTwoDeck deck = new BigTwoDeck();
		/**
		 * Shuffles the created deck.
		 */
		deck.shuffle();
		/**
		 * Starts the BigTwo bigtwo game with the shuffled deck.
		 */
		bigtwo.start(deck);
	}
}
