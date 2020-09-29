
public class Card {

	private int number;  // 2-10; J = 11, Q = 12, K = 13, A = 14
	private int suit; // 1 = Spades; 2 = Hearts; 3 = Diamonds; 4 = Clubs
	
	public Card()
	{
		number = 14;
		suit = 1;
	}
	
	public Card(int n, int s) // creates an ACE OF SPADES
	{
		number = n;
		suit = s;
	}
	
	public Card(int n, String s) // creates a card of specified suit and value
	{
		if (s.toUpperCase().equals("SPADES"))
			suit = 1;
		if (s.toUpperCase().equals("HEARTS"))
			suit = 2;
		if (s.toUpperCase().equals("DIAMONDS"))
			suit = 3;
		if (s.toUpperCase().equals("CLUBS"))
			suit = 4;
	}
	
	
	public int getValue() // returns the value of the card
	{
		return number;
	}
	
	public String getNumber() // returns the number on the card (including face cards)
	{
		if (number > 10)
		{
			if (number == 11)
				return "J";
			if (number == 12)
				return "Q";
			if (number == 13)
				return "K";
			if (number == 14)
				return "A";
		}
		return number + "";
	}
	
	public String getSuit() // returns the suit of the card
	{
		if (suit == 1)
			return "Spades";
		if (suit == 2)
			return "Hearts";
		if (suit == 3)
			return "Diamonds";
		if (suit == 4)
			return "Clubs";
		return "No Suit";
	}
	
	public String toString() // prints out the card in the form of a string
	{
		return "[" + getNumber() + " " + getSuit() + "]";
	}
	
}
