import java.util.Scanner;

public class Game {

	private Player you;
	private Player bot;
	private Deck main;
	private int pot;
	
	Scanner kb = new Scanner (System.in);
	private boolean won;
	private boolean playing = true;
	private boolean dead = false;
	private boolean satan = false;
	
	public Game() // creates an empty game
	{
		you = new Player();
		bot = new Player();
		pot = 0;
		main = new Deck();
	}
	
	public Game (int chips, int potChips) // creates a game of 2 players with equal starting chip totals
	{
		you = new Player(chips);
		bot = new Player(chips);
		pot = potChips;
		main = new Deck();
	}
	
	public Game(int youChips, int botChips, int potChips) // creates a game of 2 players with independent chip totals
	{
		you = new Player(youChips);
		bot = new Player(botChips);
		pot = potChips;
		main = new Deck();
	}
	
	public int AI1v1()
	{
		int max = bot.getChips();
		int value = bot.getHand().getCard(0).getValue();
		double fraction = bot.getHand().getCard(0).getValue()/13;
		double match = (value*max)/13;
		if (value >= 11)
		{
			if ((int)match > you.getChips())
				return you.getChips();
			if ((int)match > bot.getChips())
				return bot.getChips();
			return (int)match;
		}
		
		else if (value >= 7)
		{
			match -= (0.5)*max;
			if ((int)match > you.getChips())
				return you.getChips();
			return (int)match;
		}
		
		//else if (value == 4)
		//{
		//	return (int)(max*(1/4));
		//}
		
		//else if (value == 3)
		//{
		//	return (int)(max*(1/3));
		//}
		
		//else if (value == 2)
		//{
		//	return (int)(max*(1/2));
		//}
		
		return 0;
	}
	
	public int AI1v2(int wager) // runs algorithm simulation for one card (bluff on low value hands to utilize them) -1 = fold, 0 = match bet; >0 = raise bet
	{	
		int max = bot.getChips();
		int value = bot.getHand().getCard(0).getValue();
		double fraction = bot.getHand().getCard(0).getValue()/13;
		double match = (value*max)/13;
		if (value >= 11)
		{
			if ((int)match > you.getChips())
			{
				if ((int)match > bot.getChips())
					return bot.getChips();
				return you.getChips();
			}
			
			if ((int)match > bot.getChips())
			{
				if ((int)match > you.getChips())
					return you.getChips();
				return bot.getChips();
			}
			
			return (int)match;
		}
		
		else if (value >= 7)
		{
			match -= (0.45)*max;
			if ((int)match > you.getChips())
				return you.getChips();
			return (int)match;
		}
		
		else if (value > 4 && wager < (int)(max*0.25))
		{
			match -= (0.65)*max;
			if ((int)match > you.getChips())
				return you.getChips();
			return (int)match;
		}
		
		else if (value == 4 && wager < (int)(0.1*you.getChips()))
		{
			if ((int)(you.getChips()*0.1) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.1);
		}
		
		else if (value == 3 && wager < (int)(0.1*you.getChips()))
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}
		
		else if (value == 2 && wager < (int)(0.1*you.getChips()))
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}

		
		return 0;
	}
	
	public int AI1v3(int wager) // runs algorithm simulation for one card (bluff on low value hands to utilize them) -1 = fold, 0 = match bet; >0 = raise bet
	{	
		int max = bot.getChips();
		int value = bot.getHand().getCard(0).getValue();
		int percent = (int)(Math.random()*100 + 1);
		double match = ((value-1)*max)/13;
		if (value >= 11 && percent < 95)
		{
			if ((int)match > you.getChips())
			{
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if ((int)match > bot.getChips())
			{
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			return (int)match;
		}
		
		else if (value >= 7)
		{
			if (value == 10 && percent < 80)
			{
				match -= (0.45)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if (value == 9 && percent < 60)
			{
				match -= (0.45)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if (value == 8 && percent < 50)
			{
				match -= (0.45)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if (value == 7 && percent < 40)
			{
				match -= (0.45)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
		}
		
		else if (value > 4 && wager < (int)(max*0.25))
		{
			if (value == 6 && percent < 15)
			{
				match -= (0.65)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if (value == 6 && percent < 10)
			{
				match -= (0.65)*max;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
		}
		
		else if (value == 4 && wager < (int)(0.1*you.getChips()) && percent < 20)
		{
			if ((int)(you.getChips()*0.1) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.1);
		}
		
		else if (value == 3 && wager < (int)(0.1*you.getChips()) && percent < 30)
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}
		
		else if (value == 2 && wager < (int)(0.1*you.getChips()) && percent < 45)
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}

		
		return 0;
	}
	
	public int AI1v3c2(int wager) // runs algorithm simulation for one card (bluff on low value hands to utilize them) -1 = fold, 0 = match bet; >0 = raise bet
	{	
		int max = bot.getChips();
		int value = bot.getHand().getBig();
		int percent = (int)(Math.random()*100 + 1);
		double match = ((value-1)*max)/13;
		
		if (bot.getHand().size() > 1 && bot.getHand().pair2())
		{
			if (bot.getHand().getCard(0).getValue() >= 10 && percent < 97)
			{
				match += max*0.5;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
			if (percent < 90)
			{
				match += max*0.75;
				if ((int)match > bot.getChips())
					return bot.getChips();
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)(match);
			}
			
		}
		
		if (value >= 11)
		{
			if ((int)match > you.getChips())
			{
				if ((int)match > bot.getChips())
					return bot.getChips();
				return you.getChips();
			}
			
			if ((int)match > bot.getChips())
			{
				if ((int)match > you.getChips())
					return you.getChips();
				return bot.getChips();
			}
			
			return (int)match;
		}
		
		else if (value >= 7)
		{
			if (value == 10 && percent < 80)
			{
				match -= (0.45)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
			
			if (value == 9 && percent < 60)
			{
				match -= (0.45)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
			
			if (value == 8 && percent < 50)
			{
				match -= (0.45)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
			
			if (value == 7 && percent < 40)
			{
				match -= (0.45)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
		}
		
		else if (value > 4 && wager < (int)(max*0.25))
		{
			if (value == 6 && percent < 15)
			{
				match -= (0.65)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
			
			if (value == 6 && percent < 10)
			{
				match -= (0.65)*max;
				if ((int)match > you.getChips())
					return you.getChips();
				return (int)match;
			}
		}
		
		else if (value == 4 && wager < (int)(0.1*you.getChips()) && percent < 20)
		{
			if ((int)(you.getChips()*0.1) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.1);
		}
		
		else if (value == 3 && wager < (int)(0.1*you.getChips()) && percent < 30)
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}
		
		else if (value == 2 && wager < (int)(0.1*you.getChips()) && percent < 45)
		{
			if ((int)(you.getChips()*0.25) > bot.getChips())
				return max;
			return (int)(you.getChips()*0.25);
		}

		
		return 0;
	}
	
	public int getPot() // returns the total number of chips in the pot
	{
		return pot;
	}
	
	public Deck getMain()
	{
		return main;
	}
	
	public Player getYou() // returns the player controlled by input
	{
		return you;
	}
	
	public Player getBot() // returns the player controlled by algorithm
	{
		return bot;
	}
	
	public void betYou(int wager) // moves chips from player hand to the pot
	{
		you.addChips(-wager);
		pot += wager;
	}
	
	public void betBot(int wager) // moves chips from player controlled by the computer hand to the pot
	{
		bot.addChips(-wager);
		pot += wager;
	}
	
	public void foldYou() // folds player's hand and gives pot to opponent
	{
		for (int i = 0; i < you.getHand().getCards().size(); i++)
			main.add(you.getHand().getCards().get(i));
		you.fold();
		bot.addChips(pot);
		pot = 0;
	}
	
	public void fold()
	{
		for (int i = 0; i < you.getHand().getCards().size(); i++)
			main.add(you.getHand().getCards().get(i));
		you.fold();
		for (int i = 0; i < bot.getHand().getCards().size(); i++)
			main.add(bot.getHand().getCards().get(i));
		bot.fold();
	}
	
	public void foldBot() // folds player's hand controlled by the computer and gives pot to opponent
	{
		for (int i = 0; i < bot.getHand().getCards().size(); i++)
			main.add(bot.getHand().getCards().get(i));
		bot.fold();
		you.addChips(pot);
		pot = 0;
	}
	
	public void draw() // draws cards for both players, removing them from the deck
	{
		you.draw(main.draw());
		bot .draw(main.draw());
	}
	
	public void ante(int wager)
	{
		betYou(wager);
		betBot(wager);
	}
	
	public void run() // runs comparison of hands
	{
		if (you.getHand().getCard(0).getValue() > bot.getHand().getCard(0).getValue())
		{
			System.out.println();
			System.out.println("YOU WIN");
			you.addChips(pot);
			pot = 0;
		}
		else if (you.getHand().getCard(0).getValue() < bot.getHand().getCard(0).getValue())
		{
			System.out.println();
			System.out.println("YOU LOSE");
			bot.addChips(pot);
			pot = 0;
		}
		else
		{
			System.out.println();
			System.out.println("TIE");
			bot.addChips(((int)(pot*0.5)));
			you.addChips(((int)(pot*0.5)));
			pot = 0;
		}
		
	}
	
	public void run2() // runs comparison of hands
	{
		if (you.getHand().getRank2() > bot.getHand().getRank2())
		{
			System.out.println();
			System.out.println("YOU WIN");
			you.addChips(pot);
			pot = 0;
		}
		else if (you.getHand().getRank2() < bot.getHand().getRank2())
		{
			System.out.println();
			System.out.println("YOU LOSE");
			bot.addChips(pot);
			pot = 0;
		}
		else if (you.getHand().getBig() > bot.getHand().getBig())
		{
			System.out.println();
			System.out.println("YOU WIN");
			you.addChips(pot);
			pot = 0;
		}
		else if (you.getHand().getBig() < bot.getHand().getBig())
		{
			System.out.println();
			System.out.println("YOU LOSE");
			bot.addChips(pot);
			pot = 0;
		}
		else // DOES NOT ACCOUNT FOR SINGLE CARD TIES; NO RUN-OFFS
		{
			if (you.getHand().getNextBig() > bot.getHand().getNextBig())
			{
				System.out.println();
				System.out.println("YOU WIN");
				you.addChips(pot);
				pot = 0;
			}
			
			else if (you.getHand().getNextBig() < bot.getHand().getNextBig())
			{
				System.out.println();
				System.out.println("YOU LOSE");
				bot.addChips(pot);
				pot = 0;
			}
			
			else
			{
				System.out.println();
				System.out.println("TIE");
			}
		}
		
	}
	
	public void displayChips()
	{
		System.out.println("YOUR CHIPS: " + you.getChips());
		System.out.println("HIS CHIPS: " + bot.getChips());
		System.out.println("POT: " + pot);
	}
	
	public void displayHand()
	{
		System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
		System.out.println("HIS HAND: " + bot.getHand().getCard(0)); // prints the first card (not final display)
	}
	
	
	public void play1() // runs one hand of betting
	{	
		main.shuffle();
		draw();
		
		displayHand();
		displayChips();
		
		int wager = you.getChips() + 1;
		while (wager > you.getChips() || wager > bot.getChips())
		{
			System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
			wager = kb.nextInt();
		}
		
		if (wager < 0)
			foldYou();
		else if (wager >= 0)
		{
			betYou(wager);
			betBot(wager); // without algorithm implementation
			run();
		}	
		
		foldYou();
		foldBot();
		
		displayChips();
	}
	
	public void play() // runs many hand of betting
	{	
		while(playing)
		{
			main.shuffle();
			draw();
		
			System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
			displayChips();
		
			int wager = you.getChips() + 1;
			while (wager > you.getChips() || wager > bot.getChips())
			{
				System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
				wager = kb.nextInt();
			}
		
			if (wager < 0)
			{
				foldYou();
				System.out.println("YOU FOLDED");
			}
			else if (wager >= 0)
			{
				betYou(wager);
				betBot(wager); // without algorithm implementation
				run();
			}	
			
			System.out.println();
		
			System.out.println("HIS HAND: " + bot.getHand().getCard(0)); // prints the first card (not final display)
			displayChips();
			System.out.println();
			
			if (you.getChips() > bot.getChips())
				won = true;
			else if (you.getChips() < bot.getChips())
				won = false;
			
			if ((you.getChips() <= 0 || bot.getChips() <= 0) && pot == 0)
				playing = false;
			
			fold();
		}
		
		if (won)
			System.out.println("VICTORY ROYALE");
		else if (!won)
			System.out.println("YOU HAVE BEEN ELIMINATED");
		else
			System.out.println("TIE");
		
	}
	
	public void play(int ant) // runs many hand of betting with ante
	{	
		while(playing)
		{
			main.shuffle();
			draw();
		
			System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
			ante(ant);
			displayChips();
		
			int wager = you.getChips() + 1;
			while (wager > you.getChips() || wager > bot.getChips())
			{
				System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
				wager = kb.nextInt();
			}
		
			if (wager < 0)
			{
				foldYou();
				System.out.println("YOU FOLDED");
			}
			else if (wager >= 0)
			{
				betYou(wager);
				betBot(wager); // without algorithm implementation
				run();
			}	
			
			System.out.println();
		
			System.out.println("HIS HAND: " + bot.getHand().getCard(0)); // prints the first card (not final display)
			displayChips();
			System.out.println();
			
			if (you.getChips() > bot.getChips())
				won = true;
			else if (you.getChips() < bot.getChips())
				won = false;
			
			if ((you.getChips() < ant || bot.getChips() < ant) && pot == 0)
				playing = false;
			
			fold();
		}
		
		if (won)
			System.out.println("VICTORY ROYALE");
		else if (!won)
			System.out.println("YOU HAVE BEEN ELIMINATED");
		else
			System.out.println("TIE");
		
	}
	
	public void play2Card2RoundAI(int ant) // runs many hand of betting with ante w/ algorithm
	{	
		boolean play = false;
		
		while(playing)
		{
			main.shuffle();
			draw();
			Card temp1 = bot.getHand().getCard(0);
			Card temp2 = new Card(0,0);
		
			System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
			ante(ant);
			displayChips();
		
			int wager = you.getChips() + 1;
			while ((wager > you.getChips() || wager > bot.getChips()) && wager != 666 && wager != 1000)
			{
				System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
				wager = kb.nextInt();
			}
		
			if (wager == 1000) // shoot your opponent
			{
				won = true;
				playing = false;
				dead = true;
			}
			
			else if (wager == 666)
			{
				won = true;
				playing = false;
				satan = true;
			}
			
			int wager1 = wager;
			
			if (wager < 0)
			{
				foldYou();
				System.out.println("YOU FOLDED");
			}
			
			else if (wager >= 0)
			{
				int match = AI1v3(wager);
				if (match >= wager)
				{
					System.out.println("HE BET: " + match);
					wager = you.getChips() + 1;
					while (wager > you.getChips() || wager > bot.getChips())
					{
						System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
						wager = kb.nextInt();
					}
					
					if (match == wager)
					{
						betYou(match);
						betBot(match); // with algorithm implementation
						play = true;
					}
					
					else if (match > wager)
					{
						betYou(wager1);
						betBot(wager1);
						foldYou();
						System.out.println("YOU FOLDED");
						play = false;
					}
					
					else
					{
						if (wager - match > (int)(0.25*match))
						{
							foldBot();
							System.out.println("HE FOLDED");
							play = false;
						}
						
						else
						{
							System.out.println("HE BET: " + wager);
							betYou(wager);
							betBot(wager); // with algorithm implementation
							play = true;
						}
					}
				}
				
				else
				{
					System.out.println("HE FOLDS ");
					foldBot();
					foldYou();
				}
				
				if (play)
				{
					draw();
					temp2 = bot.getHand().getCard(0);
					
					System.out.println();
					System.out.println("YOUR HAND: " + you.getHand().getCard(0) + you.getHand().getCard(1)); // prints the first card
					displayChips();
				
					int wager2 = you.getChips() + 1;
					while (wager2 > you.getChips() || wager2 > bot.getChips())
					{
						System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
						wager2 = kb.nextInt();
					}
					
					wager1 = wager2;
				
					if (wager2 < 0)
					{
						foldYou();
						System.out.println("YOU FOLDED");
					}
					
					else if (wager2 >= 0)
					{
						match = AI1v3c2(wager2);
						if (match >= wager2)
						{
							System.out.println("HE BET: " + match);
							wager2 = you.getChips() + 1;
							while (wager2 > you.getChips() || wager2 > bot.getChips())
							{
								System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
								wager2 = kb.nextInt();
							}
							
							if (match == wager2)
							{
								betYou(match);
								betBot(match); // with algorithm implementation
								run2();
							}
							
							else if (match > wager2)
							{
								betYou(wager1);
								betBot(wager1);
								foldYou();
								System.out.println("YOU FOLDED");
							}
							
							else
							{
								if (wager2 - match > (int)(0.25*match))
								{
									foldBot();
									System.out.println("HE FOLDED");
									play = false;
								}
								
								else
								{
									System.out.println("HE BET: " + wager2);
									betYou(wager2);
									betBot(wager2); // with algorithm implementation
									run2();
								}
							}
							
						}
						else
						{
							foldBot();
							System.out.println("HE FOLDED");
						}

					}
				}
				
			}	
			
			System.out.println();
			if (bot.getHand().size()== 2)
				System.out.println("HIS HAND: " + bot.getHand().getCard(0) + bot.getHand().getCard(1)); // prints the first card (not final display)
			else if (bot.getHand().size()== 1)
				System.out.println("HIS HAND: " + bot.getHand().getCard(0));
			else if (temp2.getValue() == 0)
				System.out.println("HIS HAND: " + temp1);
			else
				System.out.println("HIS HAND: " + temp1 + temp2);
			
			displayChips();
			System.out.println();
			
			if (you.getChips() > bot.getChips())
				won = true;
			else if (you.getChips() < bot.getChips())
				won = false;
			
			if ((you.getChips() < ant || bot.getChips() < ant))
				playing = false;
			
			fold();
		}
		
		if (won)
			System.out.println("VICTORY ROYALE");
		else if (!won)
			System.out.println("YOU HAVE BEEN ELIMINATED");
		else
			System.out.println("TIE");
		
		if (dead)
		{
			System.out.println();
			System.out.println("YOU SHOOT YOUR OPPONENT");
			System.out.println("HE IS DEAD");
		}
		
		if (satan)
		{
			System.out.println();
			System.out.println("YOU HAVE SOLD YOUR SOUL TO SATAN");
			System.out.println("IN EXCHANGE, YOU HAVE WON THE GAME");
			System.out.println();
			System.out.println("YOU HAVE ALSO GONE INSANE");
			System.out.println();
			
			for (int i = 0; i < 5; i ++)
			{
				System.out.println("RIP <> TEAR <> KILL <> HUNGER <> BLOOD <> VOICES <> INSANITY <> STARVATION <> FLESH <> RITUAL <> SACRAFICE <> DEMONS <> SATAN");
			}
		}
	}
	
	
	public void playAI(int ant) // runs many hand of betting with ante w/ algorithm
	{	
		while(playing)
		{
			main.shuffle();
			draw();
			Card temp = bot.getHand().getCard(0);
		
			System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
			ante(ant);
			displayChips();
		
			int wager = you.getChips() + 1;
			while ((wager > you.getChips() || wager > bot.getChips()) && wager != 1000 && wager != 666)
			{
				System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
				wager = kb.nextInt();
			}
			
			if (wager == 1000) // shoot your opponent
			{
				won = true;
				playing = false;
				dead = true;
			}
			
			else if (wager == 666)
			{
				won = true;
				playing = false;
				satan = true;
			}
			
			int wager1 = wager;
		
			if (wager < 0)
			{
				foldYou();
				System.out.println("YOU FOLDED");
			}
			else if (wager >= 0)
			{
				int match = AI1v3(wager);
				System.out.println("HE BET: " + match);
				if (match >= wager)
				{
					wager = you.getChips() + 1;
					while (wager > you.getChips() || wager > bot.getChips())
					{
						System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
						wager = kb.nextInt();
					}
					
					if (match == wager)
					{
						betYou(match);
						betBot(match); // with algorithm implementation
						run();
					}
					
					else if (match > wager)
					{
						betYou(wager1);
						betBot(wager1);
						foldYou();
						System.out.println("YOU FOLDED");
					}
					
					else
					{
						if (wager - match > (int)(0.25*match))
						{
							foldBot();
							System.out.println("HE FOLDED");
						}
						
						else
						{
							System.out.println("HE BET: " + wager);
							betYou(wager);
							betBot(wager); // with algorithm implementation
							run();
						}
					}
					
				}
				else
				{
					foldBot();
					System.out.println("HE FOLDED");
				}
			}	
			
			System.out.println();
		
			System.out.println("HIS HAND: " + temp); // prints the first card (not final display)
			displayChips();
			System.out.println();
			
			if (you.getChips() > bot.getChips())
				won = true;
			else if (you.getChips() < bot.getChips())
				won = false;
			
			if ((you.getChips() < ant || bot.getChips() < ant) && pot == 0)
				playing = false;
			
			fold();
		}
		
		if (won)
			System.out.println("VICTORY ROYALE");
		else if (!won)
			System.out.println("YOU HAVE BEEN ELIMINATED");
		else
			System.out.println("TIE");
		
		if (dead)
		{
			System.out.println();
			System.out.println();
			System.out.println("YOU DRAW A GUN FROM A CONCEALED HOLSTER UNDER THE TABLE");
			System.out.println("YOU COCK THE HAMMER QUIETLY AND RAISE IT AT YOUR OPPONENT");
			System.out.println();
			System.out.println("BANG");
			System.out.println();
			System.out.println("YOU SHOOT YOUR OPPONENT");
			System.out.println("HE IS DEAD");
		}
		
		if (satan)
		{
			System.out.println();
			System.out.println("YOU HAVE SOLD YOUR SOUL TO SATAN");
			System.out.println("IN EXCHANGE, YOU HAVE WON THE GAME");
			System.out.println();
			System.out.println("YOU HAVE ALSO GONE INSANE");
			System.out.println();
			
			for (int i = 0; i < 5; i ++)
			{
				System.out.println("RIP <> TEAR <> KILL <> HUNGER <> BLOOD <> VOICES <> INSANITY <> STARVATION <> FLESH <> RITUAL <> SACRAFICE <> DEMONS <> SATAN");
			}
		}
		
	}
	
	
	public void play2Card2Round(int ant) // plays a game of 2 card draw with 2 rounds of betting
	{
		while(playing)
		{
			main.shuffle();
			draw();
		
			System.out.println("YOUR HAND: " + you.getHand().getCard(0)); // prints the first card
			ante(ant);
			displayChips();
		
			int wager = you.getChips() + 1;
			while ((wager > you.getChips() || wager > bot.getChips()) && wager != 666 && wager != 1000)
			{
				System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
				wager = kb.nextInt();
			}
		
			if (wager == 1000) // shoot your opponent
			{
				won = true;
				playing = false;
				dead = true;
			}
			
			else if (wager == 666)
			{
				won = true;
				playing = false;
				satan = true;
			}
			
			else if (wager < 0)
			{
				foldYou();
				System.out.println("YOU FOLDED");
			}
			
			else if (wager == you.getChips()) // going all in on your hand
			{
				System.out.println("YOU ARE GOING ALL IN");
				betYou(wager);
				betBot(wager);
				run();
			}
			
			else if (wager == bot.getChips()) // going all in on his hand
			{
				System.out.println("HE IS GOING ALL IN");
				betYou(wager);
				betBot(wager);
				run();
			}
			
			else if (wager >= 0)
			{
				betYou(wager);
				betBot(wager);
				
				draw();
				
				System.out.println();
				System.out.println("YOUR HAND: " + you.getHand().getCard(0) + you.getHand().getCard(1)); // prints the first card
				displayChips();
			
				int wager2 = you.getChips() + 1;
				while (wager2 > you.getChips() || wager2 > bot.getChips())
				{
					System.out.print("Enter Bet: "); // =0 -> check; <0 -> fold; >0 -> bet of amount
					wager2 = kb.nextInt();
				}
			
				if (wager2 < 0)
				{
					foldYou();
					System.out.println("YOU FOLDED");
				}
				
				else if (wager2 >= 0)
				{
					betYou(wager2);
					betBot(wager2); // without algorithm implementation
					run2();
				}	
			}	
			
			System.out.println();
			if (bot.getHand().size()== 2)
				System.out.println("HIS HAND: " + bot.getHand().getCard(0) + bot.getHand().getCard(1)); // prints the first card (not final display)
			else
				System.out.println("HIS HAND: " + bot.getHand().getCard(0));
			displayChips();
			System.out.println();
			
			if (you.getChips() > bot.getChips())
				won = true;
			else if (you.getChips() < bot.getChips())
				won = false;
			
			if ((you.getChips() < ant || bot.getChips() < ant))
				playing = false;
			
			fold();
		}
		
		if (won)
			System.out.println("VICTORY ROYALE");
		else if (!won)
			System.out.println("YOU HAVE BEEN ELIMINATED");
		else
			System.out.println("TIE");
		
		if (dead)
		{
			System.out.println();
			System.out.println("YOU SHOOT YOUR OPPONENT");
			System.out.println("HE IS DEAD");
		}
		
		if (satan)
		{
			System.out.println();
			System.out.println("YOU HAVE SOLD YOUR SOUL TO SATAN");
			System.out.println("IN EXCHANGE, YOU HAVE WON THE GAME");
			System.out.println();
			System.out.println("YOU HAVE ALSO GONE INSANE");
			System.out.println();
			
			for (int i = 0; i < 5; i ++)
			{
				System.out.println("RIP <> TEAR <> KILL <> HUNGER <> BLOOD <> VOICES <> INSANITY <> STARVATION <> FLESH <> RITUAL <> SACRAFICE <> DEMONS <> SATAN");
			}
		}
	}
	
}
