import java.util.ArrayList;
/**
 * The BigTwo class implements the CardGame interface. It is used to model a Big Two card
 * game.
 * 
 * @author Sankalok Sen (UID: 3035667689)
 */
public class BigTwo implements CardGame
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
	 *  a Big Two table which builds the GUI for the game and handles all user actions.
	 */
	private BigTwoTable table;
	/**
	 * A constructor for creating a Big Two card game. It:
	 * (i) Creating 4 players and add them to the list of players;
	 * (ii) Creating a Big Two table which builds the GUI for the game and handles user actions.
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
		 * Initializing the current index holding the index of the current player to a default value.
		 */
		currentIdx = -999;
		/**
		 * Initializing the new handsOnTable.
		 */
		handsOnTable = new ArrayList<Hand>();
		/**
		 * Creating a Big Two table which builds the GUI for the game and handles user actions.
		 */
		table = new BigTwoTable(this);
	}
	/**
	 * A method for getting the number of players.
	 * Getter Method.
	 * 
	 * @return NumOfPlayers
	 * 		Returns the number of players playing the BigTwo game.
	 */
	public int getNumOfPlayers()
	{
		return playerList.size();
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
		return deck;
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
		return playerList;
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
		return handsOnTable;
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
		return currentIdx;
	}
	/**
	 * A method for starting/restarting the game with a given shuffled deck of cards. We:
	 * 		(i) remove all the cards from the players as well as from the table;
	 * 		(ii) distribute the cards to the players;
	 * 		(iii) identify the player who holds the 3 of Diamonds;
	 * 		(iv) set both the currentIdx of the BigTwo instance and the activePlayer of the BigTwoTable
	 * 		 instance to the index of the player who holds the 3 of Diamonds.
	 * 
	 * @param deck
	 * 		A shuffled deck of cards used as argument to start the game.
	 */
	public void start(Deck deck)
	{
		/**
		 * Iterators integers to help in running the loops.
		 */
		int i, j;
		currentIdx = -999;
		/**
		 * Removing all elements from the handsOnTable.
		 */
		handsOnTable.removeAll(handsOnTable);
		/**
		 * Removing all the cards from the players' hands.
		 */
		for(i = 0; i < playerList.size(); i++)
		{
			playerList.get(i).getCardsInHand().removeAllCards();
		}
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
		 * Identifying the player who holds the 3 of Diamonds.
		 */
		/**
		 * The 3 of Diamonds card, the player who has it starts the game.
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
				currentIdx = i;
				break;
			}
		}
		/**
		 * Setting both the currentIdx of the BigTwo instance and the activePlayer of the BigTwoTable
		 * instance to the index of the player who holds the 3 of Diamonds.
		 */
		table.setActivePlayer(currentIdx);
		table.repaint();
	}
	/**
	 * A method for making a move by a player with the specified playerID using the cards specified
	 * by the list of indices. This method should be called from the BigTwoTable when the active 
	 * player presses either the “Play” or “Pass” button. We simply call the checkMove() method
	 * from the CardGame interface with the playerID and cardIdx as the arguments.
	 * 
	 * @param playerId
	 * 		Integer storing the ID of the players currently playing.
	 * @param cardIdx
	 * 		List of integers storing the indexes of the cards of the player currently playing.
	 */
	public void makeMove(int playerID, int[] cardIdx)
	{
		checkMove(playerID, cardIdx);
	}
	/**
	 * A method for checking a move made by a player. This method should be called from the makeMove()
	 * method from the CardGame interface. 
	 * 
	 * @param playerId
	 * 		Integer storing the ID of the players currently playing.
	 * @param cardIdx
	 * 		List of integers storing the indexes of the cards of the player currently playing.
	 */
	public void checkMove(int playerID, int[] cardIdx)
	{
		/**
		 * The 3 of Diamonds card, the player who has it starts the game.
		 */
		Card threeOfDiamonds = new Card(0, 2);
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
		 * Checks if legal playerID has been used and the cardIdx list is not empty
		 */
		if((playerID >= 0) && (playerID <= 3) && (cardIdx != null))
		{
			/**
			 * Returns the list of cards played by the player.
			 */
			cardList = playerList.get(playerID).play(cardIdx);
			/**
			 * Returns a valid hand from the specified cardlist, else returns a null value.
			 */
			Hand hand = composeHand(playerList.get(playerID), cardList);
			/**
			 * If the table is empty, that is, it is the start of the game.
			 */
			if(handsOnTable.isEmpty() == true)
			{
				/**
				 * Checks if Hand is containing three of diamonds
				 */
				if(((hand.contains(threeOfDiamonds)) == true) && (hand.isValid() == true))
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
				 * equal the index of the player who previously played.
				 */
				if(handsOnTable.get(handsOnTable.size() - 1).getPlayer() != playerList.get(playerID) && (hand.isEmpty() == false))
				{
					/**
					 * Checks if the hand played can beat the hand present on the table or not.
					 */
					legalHand = hand.beats(handsOnTable.get(handsOnTable.size() - 1));
				}
				else if(handsOnTable.get(handsOnTable.size() - 1).getPlayer() != playerList.get(playerID) && (hand.isEmpty() == true))
				{
					/**
					 * Cannot beat since Hand is empty.
					 */
					legalHand = false;
				}
				else if(handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerID) && (hand.isEmpty() == false))
				{
					/**
					 * LegalHand becomes true since here the player index == starting player index.
					 * So, one complete round of Passes have been played and the position is back to the player
					 * who played in the previous round before the round of Passes, and now that player
					 * can play any legal hand the player wants.
					 */
					legalHand = true;
				}
				else if(handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerID) && (hand.isEmpty() == true))
				{
					/**
					 * Cannot beat since Hand is empty.
					 */
					legalHand = false;
				}
			}
			/**
			 * If the legal hand is played and the hand is valid.
			 */
			if((legalHand == true) && (hand.isValid() == true))
			{
				/**
				 * Iterator for for loops.
				 */
				int i;
				/**
				 * Executing a for loop to remove the cards played.
				 */
				for(i = 0; i < cardList.size(); i++)
				{
					/**
					 * Removes the cards one by one after a legal hand has been played.
					 */
					playerList.get(playerID).getCardsInHand().removeCard(cardList.getCard(i));
				}
				/**
				 * We add the hand played to the hands on table.
				 */
				handsOnTable.add(hand);
				/**
				 * Printing the player, hand and type of hand played.
				 */
				table.printMsg("Player " + playerID + "'s turn:");
				table.printMsg("{" + hand.getType() + "} " + hand);
				/**
				 * Changes the current player index so now the next player can play his/her move.
				 */
				currentIdx = (currentIdx + 1);
				if(currentIdx > 3)
				{
					currentIdx = currentIdx - 4;
				}
				/**
				 * Set the Active Player to the new Active Player.
				 */
				table.setActivePlayer(currentIdx);
			}
			else if((legalHand == false) || (hand.isValid() == false))
			{
				/**
				 * Print that the move played is not a legal move.
				 */
				table.printMsg("Player " + playerID + "'s turn:");
				table.printMsg(cardList + " <== Not a legal move!!!");
			}
		}
		/**
		 * If the cardIdx is null.
		 */
		else if(cardIdx == null)
		{
			/**
			 * If the hands on table is not empty and starting player index not equals to player index,
			 * it means some moves of hands have already been played, and now a null hand has been played,
			 * signifying a Pass by the current player to shift play to the next player.
			 */
			if((handsOnTable.isEmpty() == false) && (handsOnTable.get(handsOnTable.size() - 1).getPlayer() != playerList.get(playerID)))
			{
				/**
				 * Pass played, shifting the move to the next player.
				 */
				table.printMsg("Player " + playerID + "'s turn:");
				table.printMsg("{Pass}");
				/**
				 * Changes the current player index so now the next player can play his/her move.
				 */
				currentIdx = (currentIdx + 1);
				if(currentIdx > 3)
				{
					currentIdx = currentIdx - 4;
				}
				/**
				 * Set the Active Player to the new Active Player.
				 */
				table.setActivePlayer(currentIdx);
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
				table.printMsg("Player " + playerID + "'s turn:");
				table.printMsg("{Pass} <== Not a legal move!!!");
				legalHand = false;
			}
		}
		/**
		 * Repainting the table.
		 */
		table.repaint();
		/**
		 * Checking if game has ended
		 */
		if(endOfGame() == true)
		{
			/**
			 * The index of the active player has been set to less than 0 signifying the end of the game
			 * and repaints the table.
			 */
			table.setActivePlayer(-999);
			table.repaint();
			/**
			 * Printing the end of the game status.
			 */
			table.printMsg("Game ends");
			/**
			 * Iterator for for loop.
			 */
			int i;
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
					table.printMsg("Player " + i + " wins the game.");
				}
				else
				{
					/**
					 * How many cards each of the other 3 players still hold in their hands.
					 */
					table.printMsg("Player " + i + " has " + playerList.get(i).getCardsInHand().size() + " cards in hand.");
				}
			}
		}
	}
	/**
	 * A method for checking if the game ends.
	 * 
	 * @param boolean endOfGame
	 * 		Returns true if the game has ended, and false otherwise.
	 */
	public boolean endOfGame()
	{
		/**
		 * Iterator integer to help in running the loops.
		 */
		int i;
		/**
		 * Returns true if games has ended and false otherwise.
		 */
		for(i = 0; i < playerList.size(); i++)
		{
			if(playerList.get(i).getCardsInHand().isEmpty() == true)
			{
				return true;
			}
		}
		return false;
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
		return null;
	}
	/**
	 * A method for starting a Big Two card game. It:
	 * (i) Creates a Big Two card game; 
	 * (ii) create and shuffle a deck of cards;
	 * (iii) start the game with the deck of cards.
	 * 
	 *  @param args
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
