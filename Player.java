
public class Player {

	private Hand cards;
	private int chips;
	
	public Player() // creates a player with an empty hand and no chips
	{
		cards = new Hand();
		chips = 0;
	}
	
	public Player(int c) // creates a player with an empty hand and a number of chips
	{
		cards = new Hand();
		chips = c;
	}
	
	public Hand getHand() // returns the player's hand
	{
		return cards;
	}
	
	public int getChips() // returns the player's current number of total chips
	{
		return chips;
	}
	
	public void addChips(int i) // adds some number of chips to the player's total
	{
		chips += i;
	}
	
	public void setChips(int i) // sets the player's total chips equal to some number
	{
		chips = i;
	}
	
	public void fold() // removes all cards in a player's hand
	{
		cards = new Hand();
	}
	
	public void draw(Card c) // adds a card to the top of the player's hand
	{
		cards.addCard(c);
	}
	
	public int size()
	{
		return cards.size();
	}
	
}
