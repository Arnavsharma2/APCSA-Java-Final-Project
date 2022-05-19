// Arnav Sharma
// 4/11/2022
// BloxyCrush.java
// Make try catch for images as one try catch, fix gameData error, 


import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;	
import java.awt.event.KeyListener;	
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;	
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Image;
import javax.imageio.ImageIO;

import javax.swing.Timer;

public class BloxyCrush
{
	public BloxyCrush()
	{
	}
	
	public static void main(String[] args) 
	{
		BloxyCrush bc = new BloxyCrush();
		bc.run();
	}
	
	public void run()
	{
		JFrame frame = new JFrame("Bloxy Crush");
		frame.setSize(800, 700);				
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); 
		frame.setLocation(0,0);
		frame.setResizable(false);
		cardPanelHolder cph = new cardPanelHolder(); 		
		frame.getContentPane().add( cph );		
		frame.setVisible(true);	
	}
}

class cardPanelHolder extends JPanel
{	
	private cardPanelHolder panelCards; // creates an class of this 
	// class which will be used to add this class to a cardlayout
	private CardLayout cards; // creation of the cardlayout, which will
	// be assigned to this class's instance
	
	// instantiazes every class in order to send in the necessary
	// info to for the class to be able to add itself to the deck of cards
	public cardPanelHolder ()
	{	
		CardLayout cards = new CardLayout();
		setLayout(cards);
		
		allPanels main = new allPanels(this, cards);
	}
}

class allPanels
{
	private cardPanelHolder panelCards; // will be set equal to the parameter
	// taken in by the constructor in order to add this panel
	// to the class's 
	private CardLayout cards; // will be set equal to the parameter
	// taken in by the constructor in order to add this panel
	// to the class's 
	private String gamemode, blockTypeString; // will receive a value by StartGamePanel
	// which will then be grabbed by GamePanel
	private boolean status; // will receive the status of the game
	private int score; // will receive a int value for the score of the game
	private Color mainBackground;
	private String username;
	private int backgroundColorInt[];
	
	public allPanels(cardPanelHolder panelCardsIn, CardLayout cardsIn)
	{
		panelCards = panelCardsIn;
		cards = cardsIn;
		
		gamemode = "";
		blockTypeString = "Block1";
		status = false;
		score = 0;
		backgroundColorInt = new int [3];
		backgroundColorInt[0] = 188;
		backgroundColorInt[1] = 233;
		backgroundColorInt[2] = 245;
		mainBackground = new Color(188, 233, 245);
		
		StartPanel first = new StartPanel();
		panelCardsIn.add(first, "1");
		
		InstructionsPanel1 second = new InstructionsPanel1();
		panelCardsIn.add(second, "2");
		
		InstructionsPanel2 third = new InstructionsPanel2();
		panelCardsIn.add(third, "3");
		
		StartGamePanel fourth = new StartGamePanel();
		panelCardsIn.add(fourth, "4");
		
		LeaderboardPanel fifth = new LeaderboardPanel();
		panelCardsIn.add(fifth, "5");
		
		GamePanel sixth = new GamePanel();
		panelCardsIn.add(sixth, "6");
		
		RoundLorWPanel seventh = new RoundLorWPanel();
		panelCardsIn.add(seventh, "7");
		
		ScorePanel eigth = new ScorePanel();
		panelCardsIn.add(eigth, "8");
		
		AnswersAndHelpPanel ninth = new AnswersAndHelpPanel();
		panelCardsIn.add(ninth, "9");
		
		SettingsPanel tenth = new SettingsPanel();
		panelCardsIn.add(tenth, "10");
		
	}
	
	class StartPanel extends JPanel
	{
		private Image image; // this image variable will be used in a try
		// catch block to grab the image, and in paintcomponent, to draw
		// the image using graphics
		
		// uses a null layout to create the starting panel of the game
		// which is the panel which will be seen first it adds itself to 
		// the card's deck by using the parameters sent in by cardPanelHolder
		// and navigates cards by using cards.next();
		public StartPanel ()
		{
			setLayout(null);
			
			ButtonHandler bh = new ButtonHandler();
			JLabel header = new JLabel("BloxyCrush");
			JButton play = new JButton("Play");
			JButton leaderboard = new JButton("Leaderboard");
			play.addActionListener(bh);
			leaderboard.addActionListener(bh);
			
			setBackground(mainBackground);
			Font headerFont = new Font("Serif", Font.BOLD, 30);
			
			add(header);
			add(play);
			add(leaderboard);
			header.setFont(headerFont);
			header.setLocation(325, 5); 
			header.setSize ( 200, 100 );
			play.setLocation(325, 105); 
			play.setSize ( 150, 100 );
			leaderboard.setLocation(325, 205); 
			leaderboard.setSize ( 150, 100 );
			panelCards.add(this);
			
			String pictName = new String("Background1");
			try
			{
				image = ImageIO.read(new File(pictName + ".jpeg"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
			panelCards.add(this);
		}
		
		// draws an image into the size of the background
		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 800, 700, null);
		}
		
		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Leaderboard"))
				{
					cards.next(panelCards);
					cards.next(panelCards);
					cards.next(panelCards);
					cards.next(panelCards);
				} else if (command.equals("Play"))
				{
					cards.next(panelCards);
				}
			}
		}
	}

	class InstructionsPanel1 extends JPanel
	{
			private Image image; // this image variable will be used in a try
			// catch block to grab the image, and in paintcomponent, to draw
			// the image using graphics
			
			// InstructionsPanel1 is the first panel of instructions which 
			// uses multiple panels to create a panel with 2 navigation buttons
			// a header at the top, and an image, correlating with instructions
			// written in a JtextArea
			public InstructionsPanel1 ()
			{
				
				setLayout(new BorderLayout(100, 10));
				JPanel headerPanel = new JPanel();
				headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 600, 30));
				JPanel instructionsPanel = new JPanel();
				instructionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 30));
				
				Font headerFont = new Font("Serif", Font.BOLD, 30);
				JTextArea header = new JTextArea("How to play BloxyCrush");
				JTextArea instructions = new JTextArea("A cannon that shoots " + 
					"bullets can be moved using the key (a) for left, and (d) for right. " + 
					"The cannon's bullets can be reloaded by clicking, or when " + 
					"it goes off the screen.");
				
				header.setEditable(false);
				instructions.setEditable(false);
				instructions.setSize(300, 300);
				instructions.setLineWrap(true);
				instructions.setWrapStyleWord(true);
				instructions.setBackground(mainBackground);
				instructionsPanel.setBackground(mainBackground);
				headerPanel.setBackground(mainBackground);
				buttonPanel.setBackground(mainBackground);
				header.setFont(headerFont);
				header.setBackground(mainBackground);
				setBackground(mainBackground);

				JButton back = new JButton("Back");
				JButton proceed = new JButton("Proceed");
				ButtonHandler bh = new ButtonHandler();
				back.addActionListener(bh);
				proceed.addActionListener(bh);
				
				add(headerPanel, BorderLayout.NORTH);
				headerPanel.add(header);
				add(instructionsPanel, BorderLayout.EAST);
				instructionsPanel.add(instructions);
				add(buttonPanel, BorderLayout.SOUTH);
				buttonPanel.add(back);
				buttonPanel.add(proceed);
				String pictName = new String("instruction1Pic");
				try
				{
					image = ImageIO.read(new File(pictName + ".png"));
				} 
				catch (IOException e)
				{
					System.err.println("\n\n" + pictName + " can't be found.\n\n");
					e.printStackTrace();
				}
				panelCards.add(this);
			}

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(image, 20, 100, 450, 450, this);
			}
			
			class ButtonHandler implements ActionListener 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Back"))
					{
						cards.previous(panelCards); 
					}
					if (command.equals("Proceed"))
					{
						cards.next(panelCards);
					}
				}
			}
	}

	class InstructionsPanel2 extends JPanel
	{
			// this class is a copy of InstructionsPanel1, made with a 
			// image and set of instructions
			private Image image;
			
			public InstructionsPanel2 ()
			{
				
				setLayout(new BorderLayout(100, 10));
				JPanel headerPanel2 = new JPanel();
				headerPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 600, 30));
				JPanel instructionsPanel2 = new JPanel();
				instructionsPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
				JPanel buttonPanel2 = new JPanel();
				buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 30));
				
				Font headerFont = new Font("Serif", Font.BOLD, 30);
				JTextArea header = new JTextArea("How to play BloxyCrush");
				JTextArea instructions = new JTextArea(" Spiked blocks " + 
				"will continously fall down the screen after a certain amount "
				+ "of time. Read the question and shoot the wrong answers " 
				+ "in order to prevent it from falling below the screen. "
				+ "each block will take 10 from your 50 health. There are "
				+ "5 rounds per level. Have fun!");
				
				header.setEditable(false);
				instructions.setEditable(false);
				instructions.setSize(300, 300);
				instructions.setLineWrap(true);
				instructions.setBackground(mainBackground);
				instructions.setWrapStyleWord(true);
				instructionsPanel2.setBackground(mainBackground);
				headerPanel2.setBackground(mainBackground);
				buttonPanel2.setBackground(mainBackground);
				header.setFont(headerFont);
				header.setBackground(mainBackground);
				setBackground(mainBackground);

				JButton back = new JButton("Back");
				JButton proceed = new JButton("Proceed");
				ButtonHandler bh = new ButtonHandler();
				back.addActionListener(bh);
				proceed.addActionListener(bh);
				
				add(headerPanel2, BorderLayout.NORTH);
				headerPanel2.add(header);
				add(instructionsPanel2, BorderLayout.EAST);
				instructionsPanel2.add(instructions);
				add(buttonPanel2, BorderLayout.SOUTH);
				buttonPanel2.add(back);
				buttonPanel2.add(proceed);
				String pictName = new String("instruction2Pic");
				try
				{
					image = ImageIO.read(new File(pictName + ".png"));
				} 
				catch (IOException e)
				{
					System.err.println("\n\n" + pictName + " can't be found.\n\n");
					e.printStackTrace();
				}
				panelCards.add(this);
			}

			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(image, 20, 100, 450, 450, this);
			}
			
			class ButtonHandler implements ActionListener 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Back"))
					{
						cards.previous(panelCards); 
					}
					if (command.equals("Proceed"))
					{
						cards.next(panelCards);
					}
				}
			}
	}

	class StartGamePanel extends JPanel
	{
			private JTextArea header;
			// This panel consists of 2 panels excluding the StartGamePanel 
			// itself, which is used to create a settings button, a button
			// to return to the start of the instructions (using cards.previous())
			// and a JMenu for choosing the gamemode, Easy, Medium, or Hard
			public StartGamePanel ()
			{
				repaint();
				revalidate();
				setLayout(new FlowLayout());
				JPanel center1 = new JPanel();
				center1.setLayout(new FlowLayout(FlowLayout.CENTER, 275,50));
				JPanel center = new JPanel();
				center.setLayout(new FlowLayout(FlowLayout.CENTER, 150,50));
				header = new JTextArea("Bloxy's Gamemodes");
				setLayout(new BorderLayout(10, 10));
				
				JMenuBar menuBar = new JMenuBar();
				JMenu gamemodes = new JMenu("Gamemodes");
				menuBar.add(gamemodes);
				
				JMenuItem easy = new JMenuItem("Easy");
				JMenuItem medium = new JMenuItem("Medium");
				JMenuItem hard = new JMenuItem("Hard");
				
				MenuHandler mh = new MenuHandler();
				easy.addActionListener(mh);
				medium.addActionListener(mh);
				hard.addActionListener(mh);
				
				gamemodes.add(easy);
				gamemodes.add(medium);
				gamemodes.add(hard);
				
				header.setEditable(false);
				Font headerFont = new Font("Serif", Font.BOLD, 30);
				header.setFont(headerFont);
				header.setBackground(mainBackground);
				center.setBackground(mainBackground);
				center1.setBackground(mainBackground);
				setBackground(mainBackground);
				
				JButton back = new JButton("Back To The Instructions");
				JButton settings = new JButton("Settings");
				ButtonHandler bh = new ButtonHandler();
				back.addActionListener(bh);
				settings.addActionListener(bh);
				
				add(center1, BorderLayout.NORTH);
				center1.add(header);
				add(center, BorderLayout.CENTER);
				center.add(settings);
				center.add(back);
				center.add(menuBar);
				panelCards.add(this);
				repaint();
				revalidate();
			}
			
			class ButtonHandler implements ActionListener 
			{
				public void actionPerformed(ActionEvent evt) 
				{
					String command = evt.getActionCommand();
					if(command.equals("Back To The Instructions"))
					{
						cards.previous(panelCards); 
						cards.previous(panelCards); 
					}
					if (command.equals("Settings"))
					{
						cards.next(panelCards);
						cards.next(panelCards);
						cards.next(panelCards);
						cards.next(panelCards);
						cards.next(panelCards);
						cards.next(panelCards);
					}
				}
			}
			
			class MenuHandler implements ActionListener 
			{
				public void actionPerformed( ActionEvent evt ) 
				{
					String command = evt.getActionCommand();
					GamePanel gp = new GamePanel();
					if(command.equals("Easy"))
					{
						gamemode = "Easy";
						System.out.println("gamemode set " + gamemode);
						gp.setupBlocks();
						cards.next(panelCards);
						cards.next(panelCards);
					} 
					if(command.equals("Medium"))
					{
						gamemode = "Medium";
						gp.setupBlocks();
						cards.next(panelCards);
						cards.next(panelCards);
					}	
					if(command.equals("Hard"))
					{
						gamemode = "Hard";
						gp.setupBlocks();
						cards.next(panelCards);
						cards.next(panelCards);
					}	
				}
			}	
	}
		
	class LeaderboardPanel extends JPanel
	{
		private String currentLeaderboard;
		private JTextArea leaderboard;
		
		// LeaderboardPanel has not been configured to grab stats for a leaderboard
		// but will be able to soon enough. This would grab the highest scores
		// in a gamemode from a text file, and post them in the textArea. 
		// Currently this Panel creates a textArea header, and has a placeholder
		// for leaderboardScores.
		public LeaderboardPanel ()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 600, 30));
			currentLeaderboard = new String("");
			Font font = new Font("Serif", Font.BOLD, 40);
			JTextArea header = new JTextArea("Leaderboard");
			leaderboard = new JTextArea("Choose the gamemode type you want to see the leaderboard for.");
			
			JMenuBar menuBar = new JMenuBar();
			JMenu gamemodes = new JMenu("Gamemodes");
			menuBar.add(gamemodes);
			
			JMenuItem easy = new JMenuItem("Easy");
			JMenuItem medium = new JMenuItem("Medium");
			JMenuItem hard = new JMenuItem("Hard");
			
			MenuHandler mh = new MenuHandler();
			easy.addActionListener(mh);
			medium.addActionListener(mh);
			hard.addActionListener(mh);
			
			gamemodes.add(easy);
			gamemodes.add(medium);
			gamemodes.add(hard);
			
			setBackground(mainBackground);
			header.setBackground(mainBackground);
			header.setFont(font);
			header.setForeground(Color.BLACK);
			font = new Font("Serif", Font.BOLD, 20);
			leaderboard.setFont(font);
			leaderboard.setForeground(Color.BLACK);
			leaderboard.setWrapStyleWord(true);
			
			JButton back = new JButton("Back");
			ButtonHandler bh = new ButtonHandler();
			back.addActionListener(bh);
		
			add(header);
			add(leaderboard);
			add(menuBar);
			add(back);
			panelCards.add(this);
		}
		
		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
				}
			}
		}
		
		class MenuHandler implements ActionListener 
		{
			public void actionPerformed( ActionEvent evt ) 
			{
				String command = evt.getActionCommand();
				
				if(command.equals("Easy"))
				{
					Scanner inFileEasy = null;
					String fileNameEasy = "EasyLeaderboard.txt";
					File inputFileEasy = new File(fileNameEasy);
					
					try
					{
						inFileEasy = new Scanner(inputFileEasy);
					}
					catch(FileNotFoundException e)
					{
						System.err.printf("ERROR: Cannot open &S\n", fileNameEasy);
						System.out.println(e);
						System.exit(1);
					}
					
					currentLeaderboard = "Easy Leaderboard: \n";
					while(inFileEasy.hasNext())
					{
						String line = inFileEasy.nextLine();
						currentLeaderboard += line + "\n";
					}
					leaderboard.setText(currentLeaderboard);
					inFileEasy.close();
				} 
				if(command.equals("Medium"))
				{
					Scanner inFileMedium = null;
					String fileNameMedium = "MediumLeaderboard.txt";
					File inputFileMedium = new File(fileNameMedium);
					
					try
					{
						inFileMedium = new Scanner(inputFileMedium);
					}
					catch(FileNotFoundException e)
					{
						System.err.printf("ERROR: Cannot open &S\n", fileNameMedium);
						System.out.println(e);
						System.exit(1);
					}
					
					currentLeaderboard = "Medium Leaderboard: \n";
					while(inFileMedium.hasNext())
					{
						String line = inFileMedium.nextLine();
						currentLeaderboard += line + "\n";
					}
					leaderboard.setText(currentLeaderboard);
					inFileMedium.close();
				}	
				if(command.equals("Hard"))
				{
					
					Scanner inFileHard = null;
					String fileNameHard = "HardLeaderboard.txt";
					File inputFileHard = new File(fileNameHard);
					
					try
					{
						inFileHard = new Scanner(inputFileHard);
					}
					catch(FileNotFoundException e)
					{
						System.err.printf("ERROR: Cannot open &S\n", fileNameHard);
						System.out.println(e);
						System.exit(1);
					}
					
					currentLeaderboard = "Hard Leaderboard: \n";
					while(inFileHard.hasNext())
					{
						String line = inFileHard.nextLine();
						currentLeaderboard += line + "\n";
					}
					leaderboard.setText(currentLeaderboard);
					inFileHard.close();	
				}	
				currentLeaderboard = "";
			}
		}	
	}

	class GamePanel extends JPanel implements KeyListener
	{
		
		private int xShooter, yBullet, initialBulletX, health, questionNum;
		// xShooter is the x coordinate of the shooter, which is changed, in 
		// 		order to make the shooter look like its moving, when its repainted
		// 		multiple times with a different x location.
		// yBullet with the same logic as xShooter, yBullet changes its 
		//		location in order to make it seem as if its moving.
		// initialBulletX is used to find the initial shot x coordinate, to
		//		make sure the bullet isnt repainted to the x coordinate of the 
		// 		shooter, when the shooter is moved and the user clicks the screen
		// health used to check whether the person has lost or won that round
		// questionNum the number of the question currently being solved
		// blockHitCount is a score calculated by the number of rounds won
		// 		and number of blocks hit
		
		private int yBlock[], xBlock[], yBlockIncrement[], questionNumber2; 
		private int count;
		// yBlock[] is an array containing the y coordinate of every block
		// xBlock[] is an array containing the x coordinate of every block
		// yBlockIncrement[] is an array containing the y coordinate
		// 		rate of incrementation each block will have, which will determine
		//		how fast the block will move.
		
		private int topLeftY[], topRightY[], bottomRightY[], bottomLeftY[];
		private int	topLeftX[], topRightX[], bottomRightX[], bottomLeftX[];
		// these arrays are contain the coordinate of each block as its written
		// such as how topLeftY would be the Y coordinate of the topLeft of 
		// the block. It is made an array so that it can contain the coordinates
		// for every block. 
		
		private Timer timer; // creation of Timer FV, to be used to call the class 
		// after a millisecond specified interval
		
		private boolean hitBlock[], correctOrWrong[], readyToShoot, shot, hitTarget; 
		private boolean quit, roundWin[], visible;
		// hitBlock[] is initially filled with false for every column, and 
		// 		when a bullet hits a block, the block corresponding with the 
		//		column's boolean will turn true, and an if statement is restricting
		//		the drawing of blocks that are hit (marked as true in hitBlock[])
		// readyToShoot this stores the true/false value of whether the bullet
		//		is ready to be shot, which controls when the bullet can or cannot
		//		be shot
		// shot this stores whether bullet has been shot, and if it has, it will
		// 		not change the bullets x to be the same as the shooter
		// hitTarget this stores whether the bullet has hit a target, and if
		//		it has, will become true, and that will result in a perk to 
		//		automatically reload after a click.
		// quit is a boolean which will turn true when the quit button has 
		// 		been clicked, if it has, the game will restart
		// roundWin[] logs whether the user has won or lost a round
		
		private String correctAnswer, wrongAnswer[], labelHealth, question, answer; 
		private String random[], previousQuestions[];
		// correctAnswer will contain the correct answers for each problem
		// wrongAnswer will contain the wrong answers for each problem
		// labelHealth used to reset the labels text everytime health goes down

		private Image image1, image2, image3, image4;
		// Image variables used to set the variable to an image using a try
		// 		catch method
		
		private JLabel healthLabel, questionNumber, gamemodeLabel;
		// healthLabel is the label displaying the health of the user in game
		// questionNumber is the label displaying the questionNumber of the 
		//		user in game
		
		private JTextArea problem;
		
		private JPanel bottomPanel;
		
		// GamePanel uses for loops to assign each blocks x coordinate randomly
		// with a decent spacing in between, the y coordinate is also assigned,
		// with the y coordinate being in the negatives, to allow the block 
		// to come in after time. A text area with the problem number is in 
		// the corner, which would be grabbed incremented per problem, and a 
		// text area with the gamemode, which has yet to be configured, is in 
		// the top left. The probability problem is made at the bottom of the 
		// screen with an exit button to the right of it.
		public GamePanel ()
		{
			xShooter = 400; 
			yBullet = 600;
			readyToShoot = false;
			shot = false;
			hitTarget = false;
			xBlock = new int[60];
			yBlock = new int[60];
			topLeftY = new int[60];
			topRightY = new int[60];
			bottomLeftY = new int[60];
			bottomRightY = new int[60];
			topLeftX = new int[60];
			topRightX = new int[60];
			bottomRightX = new int[60];
			bottomLeftX = new int[60];
			yBlockIncrement = new int[60];
			hitBlock = new boolean[60];
			initialBulletX = 0;
			health = 50;
			questionNum = 1;
			correctAnswer = new String("1/2");
			wrongAnswer = new String[60];
			labelHealth = new String("");
			labelHealth = "Health: " + health + "/50";
			quit = false;
			correctOrWrong = new boolean[60];
			roundWin = new boolean[6];
			question = "";
			random = new String[60];
			questionNumber = new JLabel("");
			visible = true;
			previousQuestions = new String[5];
			count = 0;
			
			addKeyListener(this);
			
			setupImages();
			setupBlocks();
			setupBlocks();
			getQuestion();
					
			Mover mover = new Mover();
			timer = new Timer(15, mover);
			timer.start();
			
			setLayout(new BorderLayout(10, 10));
			
			bottomPanel = new JPanel();
			JPanel topPanel = new JPanel();
			
			JButton quit = new JButton("Quit");
			ButtonHandler bh = new ButtonHandler();
			quit.addActionListener(bh);
			problem = new JTextArea(question);
			questionNumber = new JLabel(questionNum + "/5");
			Font font = new Font("Serif", Font.BOLD, 20);
			gamemodeLabel = new JLabel("Game Difficulty: " + gamemode);
			gamemodeLabel.setFont(font);
			healthLabel = new JLabel(labelHealth);
			
			font = new Font("Serif", Font.BOLD, 16);
			problem.setWrapStyleWord(true);
			setBackground(mainBackground);
			bottomPanel.setBackground(mainBackground);
			topPanel.setBackground(mainBackground);
			
			problem.setBackground(mainBackground);
			problem.setFont(font);
			font = new Font("Serif", Font.BOLD, 20);
			questionNumber.setFont(font);
			gamemodeLabel.setFont(font);
			
			bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			topPanel.setLayout(new BorderLayout(5, 5));
			
			problem.setLineWrap(true);
			problem.setSize(500,200);
			
			bottomPanel.add(problem);
			bottomPanel.add(quit);
			add(bottomPanel, BorderLayout.SOUTH);
			topPanel.add(questionNumber, BorderLayout.WEST);
			topPanel.add(healthLabel, BorderLayout.CENTER);
			topPanel.add(gamemodeLabel, BorderLayout.EAST);
			add(topPanel, BorderLayout.NORTH);

			panelCards.add(this);
		}
		
		public void keyPressed(KeyEvent evt) 
		{	
			int code = evt.getKeyCode();
			if(code == 27)
			{
				if(visible)
				{
					bottomPanel.setVisible(false);
					visible = false;
				}
				else
				{
					bottomPanel.setVisible(true);
					visible = true;
				}
			}
		}
		public void keyReleased(KeyEvent evt) {}
		public void keyTyped(KeyEvent evt) {}
		
		// This uses try catch methods to assign the image to each image
		// 		variable
		public void setupImages()
		{
			String pictName1 = new String(blockTypeString);
			try
			{
				image1 = ImageIO.read(new File(pictName1 + ".png"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName1 + " can't be found.\n\n");
				e.printStackTrace();
			}
			
			String pictName2 = new String("Bullet");
			try
			{
				image2 = ImageIO.read(new File(pictName2 + ".png"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName2 + " can't be found.\n\n");
				e.printStackTrace();
			}

			String pictName3 = new String("Cannon");
			try
			{
				image3 = ImageIO.read(new File(pictName3 + ".png"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName3 + " can't be found.\n\n");
				e.printStackTrace();
			}
			
			String pictName4 = new String("Background2");
			try
			{
				image4 = ImageIO.read(new File(pictName4 + ".png"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName4 + " can't be found.\n\n");
				e.printStackTrace();
			}
		}
		
		// This ultimately sets up the blocks in the game, by reassigning all
		//		variables, and choosing all the configuring of blockIncrement
		//		random numbers to be printed, and random starting locations of the blocks.
		public void setupBlocks()
		{
			questionNumber.setText(questionNum + "/5");
			xShooter = 400; 
			yBullet = 600;
			readyToShoot = false;
			shot = false;
			hitTarget = false;
			xBlock = new int[60];
			yBlock = new int[60];
			topLeftY = new int[60];
			topRightY = new int[60];
			bottomLeftY = new int[60];
			bottomRightY = new int[60];
			topLeftX = new int[60];
			topRightX = new int[60];
			bottomRightX = new int[60];
			bottomLeftX = new int[60];
			yBlockIncrement = new int[60];
			hitBlock = new boolean[60];
			initialBulletX = 0;
			health = 50;
			correctAnswer = new String("1/2");
			wrongAnswer = new String[60];
			labelHealth = new String("");
			labelHealth = "Health: " + health + "/50";
			quit = false;
			correctOrWrong = new boolean[60];
			
			for(int i = 0; i < 60; i++)
					random[i] = (int)((Math.random()*30)+1) + "/" + (int)((Math.random()*30)+1);
			
			if(gamemode.equals("Easy"))
			{
				for(int i = 0; i < 15; i++)
				{
					yBlockIncrement[i] = (int)((Math.random()*2)+1);
				}
				
				for(int i = 0; i < 15; i++)
				{
					hitBlock[i] = false;
				}
				
				for(int i = 0; i < 15; i++)
				{
					if(i == 0||i == 5||i == 10)
						xBlock[i] = (int)((Math.random()*30)+ 10);
					else
						xBlock[i] = xBlock[i-1]+(int)((Math.random()*100)+76);
				}
				
				int range = 1200;
				int minimum = 800;
				for(int i = 0; i < 15; i++)
				{
					yBlock[i] -= (int)((Math.random()*range)+minimum);
					if(i%5 == 0)
					{
						range += 100;
						minimum += 100;
					}
				}
				range = 1200;
				minimum = 800;
				
				int rand = (int)(Math.random()*15 + 5);
				for(int i = 0; i < 15; i++)
				{				
					if(i == rand)
						correctOrWrong[i] = true;
					else
						correctOrWrong[i] = false;
				}
			}
			else if(gamemode.equals("Medium"))
			{
				for(int i = 0; i < 30; i++)
				{
					yBlockIncrement[i] = (int)((Math.random()*2)+1);
				}
				
				for(int i = 0; i < 30; i++)
				{
					hitBlock[i] = false;
				}
				
				for(int i = 0; i < 30; i++)
				{
					if(i%5==0)
						xBlock[i] = (int)((Math.random()*30)+ 10);
					else
						xBlock[i] = xBlock[i-1]+(int)((Math.random()*100)+76);
				}
				
				int range = 1200;
				int minimum = 800;
				for(int i = 0; i < 30; i++)
				{
					yBlock[i] -= (int)((Math.random()*range)+minimum);
					if(i%5 == 0)
					{
						range += 100;
						minimum += 100;
					}
				}
				range = 1200;
				minimum = 800;
				
				int rand = (int)(Math.random()*30 + 10);
				for(int i = 0; i < 30; i++)
				{				
					if(i == rand)
						correctOrWrong[i] = true;
					else
						correctOrWrong[i] = false;
				}
			}
			else if(gamemode.equals("Hard"))
			{
				for(int i = 0; i < 60; i++)
				{
					yBlockIncrement[i] = (int)((Math.random()*2)+1);
				}
				
				for(int i = 0; i < 60; i++)
				{
					hitBlock[i] = false;
				}
				
				for(int i = 0; i < 60; i++)
				{
					if(i%5==0)
						xBlock[i] = (int)((Math.random()*30)+ 10);
					else
						xBlock[i] = xBlock[i-1]+(int)((Math.random()*100)+76);
				}
				
				int range = 1200;
				int minimum = 800;
				for(int i = 0; i < 60; i++)
				{
					yBlock[i] -= (int)((Math.random()*range)+minimum);
					if(i%5 == 0)
					{
						range += 100;
						minimum += 100;
					}
				}
				range = 1200;
				minimum = 800;
				
				int rand = (int)(Math.random()*60 + 10);
				for(int i = 0; i < 30; i++)
				{				
					if(i == rand)
						correctOrWrong[i] = true;
					else
						correctOrWrong[i] = false;
				}
			}
		}
		
		public void getQuestion()
		{
			questionNumber2 = (int)(Math.random() * 16);
			Scanner inFile = null;
			String fileName = "Problems.txt";
			File inputFile = new File(fileName);
			try
			{
				inFile = new Scanner(inputFile);
			}
			catch(FileNotFoundException e)
			{
				System.err.printf("ERROR: Cannot open &S\n", fileName);
				System.out.println(e);
				System.exit(1);
			}
			
			String track = new String("");
			int stars = 0;
			int countStars = 0;
			
			stars = questionNumber2*3 + 1;
			while(stars != countStars)
			{
				if(track.equals("***"))
					countStars++;
				track = inFile.next();
			}

			boolean proceed = true;
			while(inFile.hasNext() && proceed)
			{
				if(track.equals("***"))
					proceed = false;
				else
					question += track + " ";
				track = inFile.next();
			}
			answer = track;
			
			previousQuestions[count] = question + " " + answer;
			count++;
			
			inFile.close();
		} 
		
		// This is called when the round ends, or all rounds have ended and
		// the user is ready to proceed to the next panel. This decides whether
		// either situation has been satisfied, and calls the setupBlocks method 
		// accordingly.
		public void endOrNext()
		{
			if(questionNum >= 5)
			{
				timer.stop();
				
				ScorePanel sp = new ScorePanel();
				
				cards.next(panelCards);
				cards.next(panelCards);
			}
			else if(quit)
			{
				questionNum = 1;
				question = "";
				getQuestion();
				problem.setText(question);
				for(int i = 0; i<5; i++)
					previousQuestions[i] = "";
				setupBlocks();
				visible = false;
				timer.start();
			}
			else if(roundWin[questionNum])
			{
				questionNum++;
				question = "";
				getQuestion();
				score += 1000;
				problem.setText(question);
				status = true;
				setupBlocks();
				visible = false;
				timer.start();
				
				cards.next(panelCards);
			}
			else
			{
				questionNum++;
				question = "";
				getQuestion();
				score += 100;
				problem.setText(question);
				status = false;
				setupBlocks();
				visible = false;
				timer.start();
				
				cards.next(panelCards);
			}
		}
		
		class Mover implements ActionListener, MouseListener, KeyListener
		{
			public Mover()
			{
				addMouseListener(this);
				addKeyListener(this);
				setupBlocks();
			}
			
			// this repeatedly calls paint component to give it its animation
			public void actionPerformed(ActionEvent evt)
			{
				repaint();
				requestFocusInWindow();
			}

			public void mousePressed(MouseEvent evt){}
			
			// this controls the bullets movement by making sure it can only
			// reload when it is not readyToShoot and has not been shot, or
			// if it has hit a target
			public void mouseClicked(MouseEvent evt) 
			{
				if(!readyToShoot && !shot || hitTarget)
				{
					initialBulletX = xShooter + 10;
					readyToShoot = true;
					shot = true;
					hitTarget = false;
				}
			}
			public void mouseReleased(MouseEvent evt) {}
			public void mouseEntered(MouseEvent evt) {}
			public void mouseExited(MouseEvent evt) {}
			
			// this grabs the key that was pressed, and if it were left arrow
			// it would move the decrease the shooters x variable by 40
			// so when paintComponent is called again, the shooter will have
			// moved 40 to the left.
			public void keyPressed(KeyEvent evt) 
			{	
				int code = evt.getKeyCode();
				if(code == 'A')
					xShooter -= 40;
				else if(code == 'D')
					xShooter += 40;
				else if(code == KeyEvent.VK_RIGHT)
					xShooter += 40;
				else if(code == KeyEvent.VK_LEFT)
					xShooter -= 40;

				if(xShooter<0)
					xShooter = 0;
				if (xShooter > getWidth()-50)
					xShooter = getWidth() - 50;
			}
			public void keyReleased(KeyEvent evt) {}
			public void keyTyped(KeyEvent evt) {}
			
		}
		
		// This assigns the bounds of every rectangle, and draws the rectangle
		// with the already assigned coordinates. It then has a for loop to
		// increment every rectangles movement. It checks the bounds of each 
		// rectangle and checks if the bullet has entered those bounds. The
		// bullet's variable for y decreases to give it its animation effect.
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			setupImages();
			g.setColor(Color.RED);
			
			gamemodeLabel.setText("Game Difficulty: " + gamemode);
			
			healthLabel.setText("Health: " + health + "/50");
			questionNumber.setText(questionNum + "/5");
			
			for(int i = 0; i < 60; i++)
			{
				topLeftX[i] = xBlock[i];
				topLeftY[i] = yBlock[i];
				
				topRightX[i] = xBlock[i]+75;
				topRightY[i] = yBlock[i];
				
				bottomRightX[i] = xBlock[i]+75;
				bottomRightY[i] = yBlock[i]+75;
				
				bottomLeftX[i] = xBlock[i];
				bottomLeftY[i] = yBlock[i]+75;
			}
			
			if(health == 0)
			{
				timer.stop();
				endOrNext();
			}
			else if(gamemode.equals("Easy"))
			{
				g.drawImage(image4, 0, 0, 800, 700, null);
				for(int i = 0; i < 15; i++)
				{
					if(yBlock[i] >= 632 && !correctOrWrong[i])
					{
						health -= 10;
						hitBlock[i] = true;
						yBlock[i] = 0;
					}
					else if(yBlock[i] >= 632 && correctOrWrong[i])
					{
						roundWin[questionNum] = true;
						score += 500;
						endOrNext();
					}
					
					g.setColor(Color.RED);
					if(!hitBlock[i])
					{
						g.setColor(Color.WHITE);
						g.drawImage(image1, xBlock[i], yBlock[i], 75, 75, this);
						if(correctOrWrong[i])
							g.drawString(answer, xBlock[i] + 17, yBlock[i] + 45);
						else 
						{
							g.drawString(random[i], xBlock[i] + 17, yBlock[i] + 45);
						}
						yBlock[i] += yBlockIncrement[i];
					}
					
						
					if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && !correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score += 200;
					}
					else if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score += 200;
						endOrNext();
					}
				}
				
				g.setColor(Color.BLACK);
				if(readyToShoot)
				{
					if(hitTarget)
					{
						yBullet = 650;
						initialBulletX = xShooter + 10;
						shot = false;
					}
					g.drawImage(image2, initialBulletX, yBullet, 30, 30, this);
					yBullet -= 30;
				}
				
				if(yBullet <= 0)
				{
					shot = false;
					readyToShoot = false;
					yBullet = 650;
				}
				
				g.setColor(Color.GREEN);
				g.drawImage(image3, xShooter, 625, 50, 50, this);
			} else if(gamemode.equals("Medium"))
			{
				g.drawImage(image4, 0, 0, 800, 700, null);
				for(int i = 0; i < 30; i++)
				{
					if(yBlock[i] >= 632 && !correctOrWrong[i])
					{
						health -= 10;
						hitBlock[i] = true;
						yBlock[i] = 0;
					}
					else if(yBlock[i] >= 632 && correctOrWrong[i])
					{
						roundWin[questionNum] = true;
						score += 500;
						endOrNext();
					}
					
					g.setColor(Color.RED);
					if(!hitBlock[i])
					{
						g.setColor(Color.WHITE);
						g.drawImage(image1, xBlock[i], yBlock[i], 75, 75, this);
						if(correctOrWrong[i])
							g.drawString(answer, xBlock[i] + 17, yBlock[i] + 45);
						else 
						{
							g.drawString(random[i], xBlock[i] + 17, yBlock[i] + 45);
						}
						yBlock[i] += yBlockIncrement[i];
					}
						
					if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && !correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score+= 200;
					}
					else if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score+= 200;
						endOrNext();
					}
				}
				
				g.setColor(Color.BLACK);
				if(readyToShoot)
				{
					if(hitTarget)
					{
						yBullet = 650;
						initialBulletX = xShooter + 10;
						shot = false;
					}
					g.drawImage(image2, initialBulletX, yBullet, 30, 30, this);
					yBullet -= 30;
				}
				
				if(yBullet <= 0)
				{
					shot = false;
					readyToShoot = false;
					yBullet = 650;
				}
				
				g.setColor(Color.GREEN);
				g.drawImage(image3, xShooter, 625, 50, 50, this);
			}
			else if(gamemode.equals("Hard"))
			{
				g.drawImage(image4, 0, 0, 800, 700, null);
				for(int i = 0; i < 60; i++)
				{
					if(yBlock[i] >= 632 && !correctOrWrong[i])
					{
						health -= 10;
						hitBlock[i] = true;
						yBlock[i] = 0;
					}
					else if(yBlock[i] >= 632 && correctOrWrong[i])
					{
						roundWin[questionNum] = true;
						score += 500;
						endOrNext();
					}
					
					g.setColor(Color.RED);
					if(!hitBlock[i])
					{
						g.setColor(Color.WHITE);
						g.drawImage(image1, xBlock[i], yBlock[i], 75, 75, this);
						if(correctOrWrong[i])
							g.drawString(answer, xBlock[i] + 17, yBlock[i] + 45);
						else 
						{
							g.drawString(random[i], xBlock[i] + 17, yBlock[i] + 45);
						}
						yBlock[i] += yBlockIncrement[i];
					}
						
					if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && !correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score+= 200;
					}
					else if(bottomRightX[i] >= initialBulletX && bottomLeftX[i] <= initialBulletX
						&& yBullet >= topLeftY[i] && yBullet <= bottomLeftY[i] && !hitBlock[i]
						&& shot && correctOrWrong[i])
					{
						hitTarget = true;
						hitBlock[i] = true;
						score+= 200;
						endOrNext();
					}
				}
				
				g.setColor(Color.BLACK);
				if(readyToShoot)
				{
					if(hitTarget)
					{
						yBullet = 650;
						initialBulletX = xShooter + 10;
						shot = false;
					}
					g.drawImage(image2, initialBulletX, yBullet, 30, 30, this);
					yBullet -= 30;
				}
				
				if(yBullet <= 0)
				{
					shot = false;
					readyToShoot = false;
					yBullet = 650;
				}
				
				g.setColor(Color.GREEN);
				g.drawImage(image3, xShooter, 625, 50, 50, this);
			}
				
		}
		
		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Quit"))
				{
					quit = true;
					endOrNext();
					cards.previous(panelCards);
					cards.previous(panelCards);
				}
			}
		}
	}

	class RoundLorWPanel extends JPanel
	{
		private Image image;
		private boolean win; // if true, then the win is considered true, if false
		// then the win is considered false (loss)
		private JTextArea winOrLoss; // This is the JTextArea which has a different
		// win and loss message
		
		public RoundLorWPanel()
		{
			
			setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
			
			JButton back = new JButton("Continue Game");
			ButtonHandler bh = new ButtonHandler();
			back.addActionListener(bh);
			if(status)
				winOrLoss = new JTextArea("You won this round! Ready to go on to the next one?");
			else 
				winOrLoss = new JTextArea("You lost this round, nice try! Go get it right next round!");
			Font headerFont = new Font("Serif", Font.BOLD, 30);
			winOrLoss.setBackground(mainBackground);
			winOrLoss.setFont(headerFont);
			winOrLoss.setWrapStyleWord(true);
			
			String pictName = new String("Background2");
			try
			{
				image = ImageIO.read(new File(pictName + ".png"));
			} 
			catch (IOException e)
			{
				System.err.println("\n\n" + pictName + " can't be found.\n\n");
				e.printStackTrace();
			}
			
			add(winOrLoss);
			add(back);	
			
			panelCards.add(this);
		} 
		
		// This method creates the image for the background of the panel, by 
		// making the image draw from the top left of the screen to the bottom
		// right of the screen.
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);
			g.drawImage(image, 0, 0, 800, 700, null);
		}

		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Continue Game"))
				{
					cards.previous(panelCards);
				}
			}
		}
	}

	class ScorePanel extends JPanel
	{		
		private int scoreInt;
		private JTextArea scoreText;
		private JTextField enterUsername;
		
		public ScorePanel()
		{
			setBackground(mainBackground);
			setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
			
			ButtonHandler bh = new ButtonHandler();
			TextFieldListener tfl = new TextFieldListener();
			JButton help = new JButton("Math Help");
			JButton answersOverview = new JButton("Review the Questions");
			enterUsername = new JTextField("Enter your username to log your score!");
			
			help.addActionListener(bh);
			answersOverview.addActionListener(bh);
			enterUsername.addActionListener(tfl);
			
			scoreText = new JTextArea("Well done! You scored " + score);	
			
			add(scoreText);
			add(answersOverview);
			add(help);
			add(enterUsername);
			
			panelCards.add(this);
		}
		
		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Math Help"))
				{
					cards.next(panelCards);
					cards.next(panelCards);
				}
				if(command.equals("Review the Questions"))
				{
					cards.next(panelCards);
				}
			}
		}
		
		class TextFieldListener implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				username = enterUsername.getText();
				if(username.equals("Easy"))
					username = "Easy";
				if(username.equals("Medium:"))
					username = "Medium";
				if(username.equals("Hard:"))
					username = "Hard";
				logUsername();
			}
		}
		
		public void logUsername()
		{
			int scoreArray[];
			scoreArray = new int[5];
			int count = 1;
			if(gamemode.equals("Easy"))
			{
				Scanner inFileEasy = null;
				String fileNameEasy = "EasyLeaderboard.txt";
				File inputFileEasy = new File(fileNameEasy);
				
				try
				{
					inFileEasy = new Scanner(inputFileEasy);
				}
				catch(FileNotFoundException e)
				{
					System.err.printf("ERROR: Cannot open &S\n", fileNameEasy);
					System.out.println(e);
					System.exit(1);
				}
				
				while(inFileEasy.hasNextInt())
				{
					int nextInt = inFileEasy.nextInt();
					scoreArray[count] = nextInt;
					count++;
				}
				inFileEasy.close();
				
				if(score >= scoreArray[5])
				{
					File ioFileEasy = new File("EasyLeaderboard.txt");
					PrintWriter outFileEasy = null;
					try 
					{
						outFileEasy = new PrintWriter(ioFileEasy);
					}
					catch(IOException e)
					{
						e.printStackTrace();
						System.exit(1);
					}
					outFileEasy.print(username + " " + score);
					outFileEasy.close();
				}
			}
		}
	}

	class AnswersAndHelpPanel extends JPanel
	{
	
		public AnswersAndHelpPanel()
		{
			
			setLayout(new BorderLayout(15,15));
			JPanel mathHelp = new JPanel();
			JPanel questionsAndAnswers = new JPanel();
			
			JTextArea mathHelpText = new JTextArea(" A box contains seven" +
				" green marbles, six blue marbles and eight orange marbles."+
				" Without looking, you choose two marbles out of the bag."+
				" What is the probability that the first two picked will" +
				" both be green if you don’t replace the marble after the" +
				" first pick?\n Steps to solve this problem:\n1. Add the " +
				" number that will go in the denominator of the probability" +
				" by identifying the amount of marbles there are in total" +
				" \n2.  " );

			// *** 1/10 ***	
			//JTextArea questionsAndAnswers = new JTextArea(previousQuestions[0] + "\n"
			//	previousQuestions[1] + "\n" + previousQuestions[2] + "\n"
			//	+ previousQuestions[3] + "\n" + previousQuestions[4] + "\n");
				
				// 112/162
			mathHelpText.setSize(300, 300);
			mathHelpText.setWrapStyleWord(true);
			
			setBackground(mainBackground);
			
			
			JButton back = new JButton("Back");
			ButtonHandler bh = new ButtonHandler();
			back.addActionListener(bh);
			
			mathHelp.add(mathHelpText); 
			add(questionsAndAnswers, BorderLayout.WEST);
			add(mathHelp, BorderLayout.EAST);
			
			panelCards.add(this);
		}

		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String command = evt.getActionCommand();
				if(command.equals("Back"))
				{
					cards.previous(panelCards);
				}
			}
		}
	}

	class SettingsPanel extends JPanel 
	{
		private JSlider backgroundSlider1;
		private JSlider backgroundSlider2;
		private JSlider backgroundSlider3;
		
		private JPanel topPanel;
		private JPanel leftPanel;
		private JPanel rightPanel;
		private JPanel bottomPanel;

		public SettingsPanel()
		{
			setLayout(new BorderLayout(5,5));
		
			ButtonGroup bg = new ButtonGroup();
			
			topPanel = new JPanel();
			leftPanel = new JPanel();
			rightPanel = new JPanel();
			bottomPanel = new JPanel();
			setBackground(mainBackground);
			
			JLabel question1 = new JLabel("Select a color");
			question1.setFont(new Font("Serif", Font.BOLD, 20));
			
			ButtonHandler bh = new ButtonHandler();
			JButton back = new JButton("Back");
			back.addActionListener(bh);
			
			RadioButtonHandler rbh = new RadioButtonHandler();
			JRadioButton choice1 = new JRadioButton("Black Spiked Block");	
			bg.add(choice1);					
			choice1.addActionListener(rbh); 				
			
			JRadioButton choice2 = new JRadioButton( "Orange Block" );	
			bg.add( choice2 );		
			choice2.addActionListener(rbh); 		
			
			JRadioButton choice3 = new JRadioButton( "Red Block" );	
			bg.add( choice3 );		
			choice3.addActionListener(rbh); 
			
			JRadioButton choice4 = new JRadioButton( "Dark Blue Block" );	
			bg.add( choice4);		
			choice3.addActionListener(rbh); 
			
			backgroundSlider1 = new JSlider(0, 250, backgroundColorInt[0]);
			backgroundSlider1.setOrientation(JSlider.HORIZONTAL);
			SliderListener1 sl1 = new SliderListener1();
			backgroundSlider1.addChangeListener(sl1);
			
			backgroundSlider2 = new JSlider(0, 250, backgroundColorInt[1]);
			backgroundSlider2.setOrientation(JSlider.HORIZONTAL);
			SliderListener2 sl2 = new SliderListener2();
			backgroundSlider2.addChangeListener(sl2);
			
			backgroundSlider3 = new JSlider(0, 250, backgroundColorInt[2]);
			backgroundSlider3.setOrientation(JSlider.HORIZONTAL);
			SliderListener3 sl3 = new SliderListener3();
			backgroundSlider3.addChangeListener(sl3);
			
			
			leftPanel.setBackground(mainBackground);
			topPanel.setBackground(mainBackground);
			bottomPanel.setBackground(mainBackground);
			leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
			topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 50, 50));
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			leftPanel.add(choice1);
			leftPanel.add(choice2);
			leftPanel.add(choice3);
			leftPanel.add(choice4);
			add(leftPanel, BorderLayout.WEST);
			bottomPanel.add(back);
			add(bottomPanel, BorderLayout.SOUTH);
			topPanel.add(backgroundSlider1);
			topPanel.add(backgroundSlider2);
			topPanel.add(backgroundSlider3);
			add(topPanel, BorderLayout.NORTH);
			
			
			panelCards.add(this);
		}
		
		public void callAll()
		{
			StartPanel first = new StartPanel();
		
			InstructionsPanel1 second = new InstructionsPanel1();
			
			InstructionsPanel2 third = new InstructionsPanel2();
			
			StartGamePanel fourth = new StartGamePanel();
			fourth.repaint();
			fourth.revalidate();
			
			LeaderboardPanel fifth = new LeaderboardPanel();
			
			GamePanel sixth = new GamePanel();
			
			RoundLorWPanel seventh = new RoundLorWPanel();
			
			ScorePanel eigth = new ScorePanel();
			
			AnswersAndHelpPanel ninth = new AnswersAndHelpPanel();
		}
		
		class ButtonHandler implements ActionListener 
		{
			public void actionPerformed( ActionEvent evt ) 
			{
				
				String command = evt.getActionCommand();
				if ( command.equals( "Back" ) )
				{
					callAll();
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
					cards.previous(panelCards);
				}
			}
		}
		
		class SliderListener1 implements ChangeListener 
		{
			public void stateChanged (ChangeEvent evt) 
			{
				int val = backgroundSlider1.getValue();
				backgroundColorInt[0] = val;
				mainBackground = new Color(backgroundColorInt[0], backgroundColorInt[1], backgroundColorInt[2]);
				setBackground(mainBackground);
				leftPanel.setBackground(mainBackground);
				topPanel.setBackground(mainBackground);
				bottomPanel.setBackground(mainBackground);
			}
		}
		
		class SliderListener2 implements ChangeListener 
		{
			public void stateChanged (ChangeEvent evt) 
			{
				int val = backgroundSlider2.getValue();
				backgroundColorInt[1] = val;	
				mainBackground = new Color(backgroundColorInt[0], backgroundColorInt[1], backgroundColorInt[2]);
				setBackground(mainBackground);
				leftPanel.setBackground(mainBackground);
				topPanel.setBackground(mainBackground);
				bottomPanel.setBackground(mainBackground);
			}
		}
		
		class SliderListener3 implements ChangeListener 
		{
			public void stateChanged (ChangeEvent evt) 
			{
				int val = backgroundSlider3.getValue();	
				backgroundColorInt[2] = val;
				mainBackground = new Color(backgroundColorInt[0], backgroundColorInt[1], backgroundColorInt[2]);
				setBackground(mainBackground);
				leftPanel.setBackground(mainBackground);
				topPanel.setBackground(mainBackground);
				bottomPanel.setBackground(mainBackground);
			}
		}
		
		class RadioButtonHandler implements ActionListener 
		{
			public void actionPerformed( ActionEvent evt ) 
			{
				String command = evt.getActionCommand();
				if ( command.equals( "Black Spiked Block" ) )
					blockTypeString = "Block1";
				else if ( command.equals( "Orange Block" ) )
					blockTypeString = "Block2";
				else if ( command.equals( "Red Block" ) )	
					blockTypeString = "Block3";
				else if ( command.equals( "Dark Blue Block" ) )	
					blockTypeString = "Block3";	
			}
		}
	}
}
