import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The BigTwoTable class implements the CardGameTable interface. It is used to build a GUI
 * for the Big Two card game and handle all user actions. 
 * 
 * @author Sankalok Sen (UID: 3035667869)
 */
public class BigTwoTable implements CardGameTable
{
	/**
	 * A card game that associates with this table.
	 */
	private CardGame game;
	/**
	 * A boolean array indicating which cards are being selected.
	 */
	private boolean[] selected;
	/**
	 *  An integer specifying the index of the active player.
	 */
	private int activePlayer;
	/**
	 * The main window of the application.
	 */
	private JFrame frame;
	/**
	 * A panel showing the cards of each player and the cards played on the table.
	 */
	private JPanel bigTwoPanel;
	/**
	 * A “Play” button for the active player to play the selected cards.
	 */
	private JButton playButton;
	/**
	 * A “Pass” button for the active player to pass his/her turn to the next player.
	 */
	private JButton passButton;
	/**
	 * A text area for showing the current game status as well as end of game messages.
	 */
	private JTextArea msgArea;
	/**
	 * A 2D array storing the images for the faces of the cards.
	 */
	private Image[][] cardImages;
	/**
	 * An image for the backs of the cards.
	 */
	private Image cardBackImage;
	/**
	 * An array storing the images for the avatars.
	 */
	private Image[] avatars;
	/**
	 * A constructor for creating a BigTwoTable. The parameter game is a reference to a card game 
	 * associates with the table.
	 * 
	 * @param CardGame game
	 * 		The CardGame game is a reference to a card game associates with the table.
	 */
	public BigTwoTable(CardGame game)
	{
		/**
		 * References this game.
		 */
		this.game = game;
		/**
		 * Initialises the selected array to 13 (maximum that a default hand can have).
		 */
		selected = new boolean[13];
		/**
		 * Sets the index of the current active player.
		 */
		setActivePlayer(game.getCurrentIdx());
		/**
         * Adding the four avatars to the avatars Image array.
         * Also resizing the 4 images to a standard size, by scaling it smoot and transforming the 
         * image icons back.
         */
        avatars = new Image[4];
        avatars[0] = new ImageIcon("src/images/superman.png").getImage();
		Image newimg0 = avatars[0].getScaledInstance(90, 90, Image.SCALE_SMOOTH);  
		avatars[0] = new ImageIcon(newimg0).getImage();
		avatars[1] = new ImageIcon("src/images/batman.png").getImage();
		Image newimg1 = avatars[1].getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		avatars[1] = new ImageIcon(newimg1).getImage();
		avatars[2] = new ImageIcon("src/images/flash.png").getImage();
		Image newimg2 = avatars[2].getScaledInstance(90, 90, Image.SCALE_SMOOTH);  
		avatars[2] = new ImageIcon(newimg2).getImage();
		avatars[3] = new ImageIcon("src/images/ironman.png").getImage();
		Image newimg3 = avatars[3].getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		avatars[3] = new ImageIcon(newimg3).getImage();
		/**
		 * Adding the cardBackImage.
		 * Also resizing the cardBackImage to a standard size, by scaling it smoot and transforming the 
         * image icons back.
		 */
		cardBackImage = new ImageIcon("src/images/b.gif").getImage();
		Image newCardBackImage = cardBackImage.getScaledInstance(75, 90, Image.SCALE_SMOOTH);
		cardBackImage = new ImageIcon(newCardBackImage).getImage();
		/**
		 * Adding the 52 deck cards Images.
		 */
		cardImages = new Image[4][13];
		char[] suit = {'d','c','h','s'};
		char[] rank = {'a', '2', '3', '4', '5', '6', '7', '8', '9', 't', 'j', 'q', 'k'};
		int i,j;
		for(i = 0; i < 4; i++)
		{
			for(j = 0; j < 13; j++)
			{
				/**
				 * Also resizing the cardBackImage to a standard size, by scaling it smoot and transforming the 
				 * image icons back.
				 */
				cardImages[i][j] = new ImageIcon("src/images/" + rank[j] + suit[i] + ".gif").getImage();
				Image newCard = cardImages[i][j].getScaledInstance(75, 90, Image.SCALE_SMOOTH);
				cardImages[i][j] = new ImageIcon(newCard).getImage();
			}
		}
		/**
		 * Initialising the main window of the application - with the Layout and the Size.
		 */
		frame = new JFrame("The Big Two Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		/**
		 * The size of the frame has been set to the maximised size of the screen.
		 */
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		/**
		 * Creating a menuBar, menu, and the 2 menu items - Restart and Quit.
		 * Also, calling the addActionListener for restart and quit.
		 */
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenuItem restart = new JMenuItem("Restart");
		JMenuItem quit = new JMenuItem("Quit");
		restart.addActionListener(new RestartMenuItemListener());
		quit.addActionListener(new QuitMenuItemListener());
		menu.add(restart);
		menu.add(quit);
		menuBar.add(menu);
		/**
		 * Initializing the bigTwoPanel which will show the cards of each player and in a vertical Box Layout.
		 */
		bigTwoPanel = new BigTwoPanel();
		bigTwoPanel.setLayout(new BoxLayout(bigTwoPanel, BoxLayout.Y_AXIS));
		/**
		 * Creating the panel for the two buttons, Play and Pass at the bottom of the frame.
		 * Also, calling the addActionListener for play and pass.
		 */
		JPanel panelButtons = new JPanel();
		playButton = new JButton("Play");
		passButton = new JButton("Pass");
		playButton.addActionListener(new PlayButtonListener());
		passButton.addActionListener(new PassButtonListener());
		panelButtons.add(playButton);
		panelButtons.add(Box.createHorizontalStrut(50));
		panelButtons.add(passButton);
		panelButtons.setBackground(Color.GRAY);
		/**
		 * Creating the Message Area with dynamic updates using the caret features.
		 */
		msgArea = new JTextArea();
		msgArea.setEditable(false);
		JScrollPane scrollableMsgArea = new JScrollPane(msgArea); 
		scrollableMsgArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
        scrollableMsgArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scrollableMsgArea.setPreferredSize(new Dimension(450, 0));
        DefaultCaret caret = (DefaultCaret)msgArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        /**
         * Setting the menuBar, adding the panelButtons, and the scrollableMsgArea, and setting the frame
         * to be visible upon execution.
         */
        frame.setJMenuBar(menuBar);
        frame.add(bigTwoPanel, BorderLayout.CENTER);
		frame.add(panelButtons, BorderLayout.SOUTH);
		frame.getContentPane().add(scrollableMsgArea, BorderLayout.EAST);  
        frame.setVisible(true);
	}
	/**
	 * A method for setting the index of the active player (i.e., the current player). 
	 * Setter Method.
	 * 
	 * @param activePlayer
	 * 		An integer value referencing the current activePlayer playing.
	 */
	public void setActivePlayer(int activePlayer)
	{
		this.activePlayer = activePlayer;
	}
	/**
	 * A method for getting an array of indices of the cards selected. 
	 * 
	 * @return int[] getSelected
	 * 		Returns the array of the indexes of the cards selected.
	 */
	public int[] getSelected()
	{
		/**
		 * Executing a for loop with a counter to check whether any cards have been selected or not.
		 */
		int i;
		int c = 0;
		for(i = 0; i < selected.length; i++)
		{
			if(selected[i] == true)
			{
				c = c + 1;
			}
		}
		/**
		 * If the counter is more than 0, then we store the indices in the indicesSelected array and return the array.
		 */
		if(c > 0)
		{
			int[] indicesSelected = new int[c];
			c = 0;
			for(i = 0; i < selected.length; i++)
			{
				if(selected[i] == true)
				{
					indicesSelected[c] = i;
					c = c + 1;
				}
			}
			return indicesSelected;
		}
		/**
		 * We return null otherwise.
		 */
		return null;
	}
	/**
	 * A method for resetting the list of selected cards. 
	 */
	public void resetSelected()
	{
		/**
		 * Resets the selected cards.
		 */
		int i;
		for(i = 0; i < selected.length; i++)
		{
			selected[i] = false;
		}
	}
	/**
	 * A method for repainting the GUI.
	 */
	public void repaint()
	{
		/**
		 * Resetting the list of selected cards.
		 */
		resetSelected();
		/**
		 * Repaints the GUI.
		 */
		frame.repaint();
	}
	/**
	 * A method for printing the specified string to the message area of the GUI. 
	 * 
	 * @param msg
	 * 		The specified string to be printed to the message area of the GUI.
	 */
	public void printMsg(String msg)
	{
		/**
		 * Appends the message at the end of previous message if already posted and a new message if 
		 * no message has been previously posted.
		 */
		msgArea.append(msg + "\n");
	}
	/**
	 * A method for clearing the message area of the GUI.
	 */
	public void clearMsgArea()
	{
		/**
		 * Selects the entire area and clears out the area by replacing with a null value.
		 */
		msgArea.selectAll();
		msgArea.replaceSelection(null);
	}
	/**
	 * A method for resetting the GUI. 
	 * 
	 * It does:
	 * (i) reset the list of selected cards using resetSelected() method from the CardGameTable interface;
	 * (ii) clear the message area using the clearMsgArea() method from the CardGameTable interface;
	 * (iii) enable user interactions using the enable() method from the CardGameTable interface.
	 */
	public void reset()
	{
		/**
		 * Resets the list of selected cards using resetSelected() method from the CardGameTable interface.
		 */
		this.resetSelected();
		/**
		 * Clears the message area using the clearMsgArea() method from the CardGameTable interface.
		 */
		this.clearMsgArea();
		/**
		 * Enables user interactions using the enable() method from the CardGameTable interface.
		 */
		this.enable();
	}
	/**
	 * A method for enabling user interactions with the GUI.
	 * 
	 * It does:
	 * (i) enable the “Play” button and “Pass” button (i.e., making them clickable);
	 * (ii) enable the BigTwoPanel for selection of cards through mouse clicks.
	 */
	public void enable()
	{
		/**
		 * Enabling the “Play” button and “Pass” button (i.e., making them clickable).
		 */
		playButton.setEnabled(true);
		passButton.setEnabled(true);
		/**
		 * Enabling the BigTwoPanel for selection of cards through mouse clicks.
		 */
		bigTwoPanel.setEnabled(true);
	}
	/**
	 * A method for disabling user interactions with the GUI.
	 * 
	 * It does:
	 * (i) disable the “Play” button and “Pass” button (i.e., making them not clickable);
	 * (ii) disable the BigTwoPanel for selection of cards through mouse clicks.
	 */
	public void disable()
	{
		/**
		 * Disabling the “Play” button and “Pass” button (i.e., making them not clickable).
		 */
		playButton.setEnabled(false);
		passButton.setEnabled(false);
		/**
		 * Disabling the BigTwoPanel for selection of cards through mouse clicks.
		 */
		bigTwoPanel.setEnabled(false);
	}
	/**
	 * An inner class that extends the JPanel class and implements the 
	 * MouseListener interface. It overrides the paintComponent() method inherited from the 
	 * JPanel class to draw the card game table and implements the mouseClicked() method from 
	 * the MouseListener interface to handle mouse click events. 
	 * 
	 * @author Sankalok Sen (UID: 3035667869)
	 */
	class BigTwoPanel extends JPanel implements MouseListener
	{
		/**
		 * Added default serial version UID;
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * A constructor for adding the specified mouse listener to receive mouse events from this component.
		 */
		public BigTwoPanel()
		{
			/**
			 * References this mouse listener.
			 */
			this.addMouseListener(this);
		}
		public void paintComponent(Graphics g)
		{
			/**
			 * Iterators for executing the for loops.
			 */
			int i;
			/**
			 * Paint Component and the Graphics2D.
			 */
			super.paintComponent(g);
			Graphics2D g2D = (Graphics2D) g;
			/**
			 * Setting Background color.
			 */
			int red = 0;
			int green = 210;
			int blue = 105;
			this.setBackground(new Color(red, green, blue));
			/**
			 * Setting font.
			 */
			g.setFont(new Font("default", Font.BOLD, 12));
			/**
			 * Player 0.
			 */
			/**
			 * Setting Color.
			 */
			if(activePlayer == 0)
			{
				g.setColor(Color.RED);
				g.drawString("Player 0", 10, 15);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Player 0", 10, 15);
			}
			/**
			 * Drawing the avatar.
			 */
			g.drawImage(avatars[0], 30, 25, this);
			/**
			 * If active player, shows the cards as well as selected cards in a raised fashion.
			 */
			if(activePlayer == 0)
			{
				for(i = 0; i < game.getPlayerList().get(0).getNumOfCards(); i++) 
		    	{
		    		if(selected[i] == false)
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(0).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(0).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 35, this);
		    		}	
		    		else
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(0).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(0).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 15, this);
		    		}	
		    	}
			}
			/**
			 * Otherwise, shows cardBackImage.
			 */
			else
			{
				for(i = 0; i < game.getPlayerList().get(0).getNumOfCards(); i++)
				{
					g.drawImage(cardBackImage, 150 + (45 * i), 35, this);
				}
			}
			/**
			 * Drawing the Line.
			 */
			g2D.setColor(Color.BLACK);
			g2D.drawLine(0, 130, 1600, 130);
			/**
			 * Player 1.
			 */
			/**
			 * Setting Color.
			 */
			if(activePlayer == 1)
			{
				g.setColor(Color.RED);
				g.drawString("Player 1", 10, 145);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Player 1", 10, 145);
			}
			/**
			 * Drawing the avatar.
			 */
			g.drawImage(avatars[1], 30, 155, this);
			/**
			 * If active player, shows the cards as well as selected cards in a raised fashion.
			 */
			if(activePlayer == 1)
			{
				for(i = 0; i < game.getPlayerList().get(1).getNumOfCards(); i++) 
		    	{
		    		if(selected[i] == false)
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(1).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(1).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 165, this);
		    		}	
		    		else
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(1).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(1).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 145, this);
		    		}	
		    	}
			}
			/**
			 * Otherwise, shows cardBackImage.
			 */
			else
			{
				for(i = 0; i < game.getPlayerList().get(1).getNumOfCards(); i++)
				{
					g.drawImage(cardBackImage, 150 + (45 * i), 165, this);
				}
			}
			/**
			 * Drawing the Line.
			 */
			g2D.setColor(Color.BLACK);
			g2D.drawLine(0, 260, 1600, 260);
			/**
			 * Player 2.
			 */
			/**
			 * Setting Color.
			 */
			if(activePlayer == 2)
			{
				g.setColor(Color.RED);
				g.drawString("Player 2", 10, 275);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Player 2", 10, 275);
			}
			/**
			 * Drawing the avatar.
			 */
			g.drawImage(avatars[2], 30, 285, this);
			/**
			 * If active player, shows the cards as well as selected cards in a raised fashion.
			 */
			if(activePlayer == 2)
			{
				for(i = 0; i < game.getPlayerList().get(2).getNumOfCards(); i++) 
		    	{
		    		if(selected[i] == false)
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(2).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(2).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 295, this);
		    		}	
		    		else
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(2).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(2).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 275, this);
		    		}	
		    	}
			}
			/**
			 * Otherwise, shows cardBackImage.
			 */
			else
			{
				for(i = 0; i < game.getPlayerList().get(2).getNumOfCards(); i++)
				{
					g.drawImage(cardBackImage, 150 + (45 * i), 295, this);
				}
			}
			/**
			 * Drawing the Line.
			 */
			g2D.setColor(Color.BLACK);
			g2D.drawLine(0, 390, 1600, 390);
			/**
			 * Player 3.
			 */
			/**
			 * Setting Color.
			 */
			if(activePlayer == 3)
			{
				g.setColor(Color.RED);
				g.drawString("Player 3", 10, 405);
			}
			else
			{
				g.setColor(Color.WHITE);
				g.drawString("Player 3", 10, 405);
			}
			/**
			 * Drawing the avatar.
			 */
			g.drawImage(avatars[3], 30, 415, this);
			/**
			 * If active player, shows the cards as well as selected cards in a raised fashion.
			 */
			if(activePlayer == 3)
			{
				for(i = 0; i < game.getPlayerList().get(3).getNumOfCards(); i++) 
		    	{
		    		if(selected[i] == false)
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(3).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(3).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 425, this);
		    		}	
		    		else
		    		{
		    			g.drawImage(cardImages[game.getPlayerList().get(3).getCardsInHand().getCard(i).getSuit()][game.getPlayerList().get(3).getCardsInHand().getCard(i).getRank()], 150 + (45 * i), 405, this);
		    		}	
		    	}
			}
			/**
			 * Otherwise, shows cardBackImage.
			 */
			else
			{
				for(i = 0; i < game.getPlayerList().get(3).getNumOfCards(); i++)
				{
					g.drawImage(cardBackImage, 150 + (45 * i), 425, this);
				}
			}
			/**
			 * Drawing the Line.
			 */
			g2D.setColor(Color.BLACK);
			g2D.drawLine(0, 520, 1600, 520);
			/**
			 * Hands on table.
			 */
			g.drawString("Last Played Hand:", 10, 535);
			if (game.getHandsOnTable().isEmpty() == false)
		    {
				/**
				 * Gets the hand that was last added to the table.
				 */
		    	Hand lastHandOnTable = game.getHandsOnTable().get(game.getHandsOnTable().size() - 1);
		    	/**
		    	 * Drawing the last hand played.
		    	 */
		    	for(i = 0; i < lastHandOnTable.size(); i++)
	    		{
	    			g.drawImage(cardImages[lastHandOnTable.getCard(i).getSuit()][lastHandOnTable.getCard(i).getRank()], 150 + (45 * i), 555, this);
	    		}
	    		g.drawString("Played by " + game.getHandsOnTable().get(game.getHandsOnTable().size() - 1).getPlayer().getName(), 200, 535);
		    }
		    repaint();
		}
		/**
		 * An event which indicates that a mouse action occurred in a component.
		 * 
		 * @param MouseEvent e
		 * 		A mouse action is considered to occur in a particular component if and only if the mouse cursor is over the
		 * 		un-obscured part of the component's bounds when the action happens.
		 */
		public void mouseClicked(MouseEvent e) 
		{
			/**
			 * A check so that after every selection or de-selection, the GUI repaints itself to update.
			 */
			boolean check = false;
			/**
			 * Number of cards played by the current active player.
			 * Since we're counting from card 0 to card (n-1) for n cards.
			 */
			int numberCards = game.getPlayerList().get(activePlayer).getNumOfCards() - 1;
			/**
			 * Checking the last card since it spans the entire width of itself.
			 */
			if(e.getX() >= (150 + (numberCards * 45)) && e.getX() <= (225 + (numberCards * 45))) 
			{
				/**
				 * If card hasn't been selected.
				 */
				if(selected[numberCards] == false && e.getY() >= (35 + (130 * activePlayer)) && e.getY() <= (125 + (130 * activePlayer))) 
				{
					/**
					 * We select the card.
					 */
					selected[numberCards] = true;
					check = true;
				} 
				/**
				 * If card has been selected.
				 */
				else if (selected[numberCards] == true && e.getY() >= (15 + (130 * activePlayer)) && e.getY() <= (105 + (130 * activePlayer)))
				{
					/**
					 * We de-select the card.
					 */
					selected[numberCards] = false;
					check = true;
				}
			}
			/**
			 * We iteration from the second last card to the first card.
			 * If any of the above scenarios are true, the GUI repaints itself directly as it fails
			 * the check inside the for loop.
			 */
			for (numberCards = game.getPlayerList().get(activePlayer).getNumOfCards() - 2; numberCards >= 0; numberCards--) 
			{
				/**
				 * If after one iteration check is true, then we exit the for loop and the GUI repaints itself.
				 */
				if(check == false)
				{
					/**
					 * we compare the first half of the card that is visible to us.
					 */
					if (e.getX() >= (150 + (numberCards * 45)) && e.getX() <= (195 + (numberCards*45))) 
					{
						/**
						 * If card is not selected.
						 */
						if(selected[numberCards] == false && e.getY() >= (35 + (130 * activePlayer)) && e.getY() <= (125 + (130 * activePlayer))) 
						{
							/**
							 * We select the card.
							 */
							selected[numberCards] = true;
							check = true;
						} 
						/**
						 * If card is not selected.
						 */
						else if(selected[numberCards] == true && e.getY() >= (15 + (130 * activePlayer)) && e.getY() <= (105 + (130 * activePlayer)))
						{
							/**
							 * We de-select the card.
							 */
							selected[numberCards] = false;
							check = true;
						}
					}
					/**
					 * we compare the second half the card which is overlapping with another card.
					 */
					else if(e.getX() >= (195 + (numberCards * 45)) && e.getX() <= (225 + (numberCards * 45)) && e.getY() >= (35 + (130 * activePlayer)) && e.getY() <= (125 + (130 * activePlayer))) 
					{
						/**
						 * If card is not selected.
						 */
						if(selected[numberCards + 1] == true && selected[numberCards] == false) 
						{
							/**
							 * We select the card.
							 */
							selected[numberCards] = true;
							check = true;
						}
					}
					/**
					 * we compare the second half the card which is overlapping with another card.
					 */
					else if(e.getX() >= (195 + (numberCards * 45)) && e.getX() <= (225 + (numberCards * 45)) && e.getY() >= (15 + (130 * activePlayer)) && e.getY() <= (105 + (130 * activePlayer)))
					{
						/**
						 * If card is selected.
						 */
						if(selected[numberCards+1] == false && selected[numberCards] == true)
						{
							/**
							 * We de-select the card.
							 */
							selected[numberCards] = false;
							check = true;
						}
					}
				}
			}
			/**
			 * We repaint the GUI after every successful selection/de-selection of cards.
			 */
			this.repaint();
		}
		/**
		 * Dummy method.
		 * 
		 * @param MouseEvent e
		 */
		public void mouseEntered(MouseEvent e)
		{
		}
		/**
		 * Dummy method.
		 * 
		 * @param MouseEvent e
		 */
		public void mouseExited(MouseEvent e)
		{
		}
		/**
		 * Dummy method.
		 * 
		 * @param MouseEvent e
		 */
		public void mousePressed(MouseEvent e)
		{
		}
		/**
		 * Dummy method.
		 * 
		 * @param MouseEvent e
		 */
		public void mouseReleased(MouseEvent e)
		{
		}
	}
	/**
	 * An inner class that implements the ActionListener interface. It implements the actionPerformed()
	 * method from the ActionListener interface to handle button-click events for the “Play” button.
	 * When the “Play” button is clicked, we call the makeMove() method of your CardGame
	 * object to make a move.
	 * 
	 * @author Sankalok Sen (UID: 3035667869)
	 */
	class PlayButtonListener implements ActionListener
	{
		/**
		 * It implements the actionPerformed() method from the ActionListener interface.
		 * Handles the action performed by the play button - if it was clicked.
		 * 
		 * @param ActionEvent e
		 * 		Detects whether the Action was performed or not.
		 */
		public void actionPerformed(ActionEvent e)
		{
			/**
			 * If no card is selected.
			 */
			if(getSelected() == null)
			{
				printMsg("No cards have been selected.");
				repaint();
			}
			/**
			 * Otherwise we call the makeMove() method of the CardGame object game to make a move.
			 */
			else
			{
				game.makeMove(activePlayer, getSelected());
				repaint();
			}
		}
	}
	/**
	 * An inner class that implements the ActionListener interface. It implements the actionPerformed()
	 * method from the ActionListener interface to handle button-click events for the “Pass” button.
	 * When the “Pass” button is clicked, we call the makeMove() method of your CardGame 
	 * object to make a move.
	 * 
	 * @author Sankalok Sen (UID: 3035667869)
	 */
	class PassButtonListener implements ActionListener
	{
		/**
		 * It implements the actionPerformed() method from the ActionListener interface.
		 * Handles the action performed by the pass button - if it was clicked.
		 * 
		 * @param ActionEvent e
		 * 		Detects whether the Action was performed or not.
		 */
		public void actionPerformed(ActionEvent e)
		{
			/**
			 * The pass button is selected.
			 */
			game.makeMove(activePlayer, null);
			repaint();
		}
	}
	/**
	 * An inner class that implements the ActionListener interface. It implements the actionPerformed() 
	 * method from the ActionListener interface to handle menu-item-click events for the “Restart” menu
	 * item. When the “Restart” menu item is selected, we:
	 * 		(i) create a new BigTwoDeck object and call its shuffle() method;
	 * 		(ii) call the start() method of your CardGame object with the BigTwoDeck object as an argument.
	 * 
	 * @author Sankalok Sen (UID: 3035667869)
	 */
	class RestartMenuItemListener implements ActionListener
	{
		/**
		 * It implements the actionPerformed() method from the ActionListener interface.
		 * Handles the action performed when the restart menu item is selected.
		 * 
		 * @param ActionEvent e
		 * 		Detects whether the Action was performed or not.
		 */
		public void actionPerformed(ActionEvent e)
		{
			/**
			 * Creating a new BigTwoDeck object and call its shuffle() method.
			 */
			BigTwoDeck deck = new BigTwoDeck();
			deck.shuffle();
			/**
			 * Calling the start() method of your CardGame object with the BigTwoDeck object as an argument.
			 */
			game.start(deck);
			/**
			 * Resetting the GUI.
			 */
			reset();
		}
	}
	/**
	 * An inner class that implements the ActionListener interface. It implements the actionPerformed() 
	 * method from the ActionListener interface to handle menu-item-click events for the “Quit” menu item.
	 * When the “Quit” menu item is selected, we should terminate your application. 
	 * (You may use System.exit() to terminate your application.)
	 * 
	 * @author Sankalok Sen (UID: 3035667869)
	 */
	class QuitMenuItemListener implements ActionListener
	{
		/**
		 * It implements the actionPerformed() method from the ActionListener interface.
		 * Handles the action performed when the quit menu item is selected.
		 * 
		 * @param ActionEvent e
		 * 		Detects whether the Action was performed or not.
		 */
		public void actionPerformed(ActionEvent e)
		{
			/**
			 * Exits the system after 0 miliseconds.
			 */
			System.exit(0);
		}
	}
}