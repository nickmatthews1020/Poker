import java.util.Scanner;

public class GameADV {

	private Player you;
	private Player bot;
	private Deck main;
	private int pot;
	
	Scanner kb = new Scanner (System.in);
	private boolean won;
	private boolean playing = true;
	
	public GameADV() // creates an empty game
	{
		you = new Player();
		bot = new Player();
		pot = 0;
		main = new Deck();
	}
	public GameADV (int chips, int potChips) // creates a game of 2 players with equal starting chip totals
	{
		you = new Player(chips);
		bot = new Player(chips);
		pot = potChips;
		main = new Deck();
	}
	public GameADV(int youChips, int botChips, int potChips) // creates a game of 2 players with independent chip totals
	{
		you = new Player(youChips);
		bot = new Player(botChips);
		pot = potChips;
		main = new Deck();
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

	
	
	
}
