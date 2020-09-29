import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck() // creates a standard 52-card deck
	{
		cards = new ArrayList<Card>();
		
		for (int i = 2; i < 15; i++)
		{
			cards.add(new Card(i,4));
		}
		
		for (int i = 2; i < 15; i++)
		{
			cards.add(new Card(i,3));
		}
		
		for (int i = 2; i < 15; i++)
		{
			cards.add(new Card(i,2));
		}
		
		for (int i = 2; i < 15; i++)
		{
			cards.add(new Card(i,1));
		}
	}
	
	public Deck(ArrayList<Card> x) // creates a deck with card arrayList; can be entered with NULL data to create empty deck
	{
		cards = new ArrayList<Card>();
		cards.addAll(x);
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public void add(Card c) // adds a card to the bottom of the deck
	{
		cards.add(c);
	}
	
	public void add(int index, Card c) // adds a card to a position in the deck
	{
		cards.add(index,c);
	}
	
	public void add(Hand h) // adds all of the cards in a hand to the bottom of the deck
	{
		for (int i = 0; i < h.getCards().size(); i ++)
		{
			cards.add(h.getCard(i));
		}
	}
	
	public Card draw() // removes and returns the top card of the deck
	{
		return cards.remove(0);
	}
	
	public void remove(int index) // removes a card at a specified location
	{
		cards.remove(index);
	}
	
	public void shuffle() // randomizes the order of cards in the deck
	{
		ArrayList<Card> temp = new ArrayList<Card>();
		int start = cards.size();
		
		for (int i = 0; i < start; i++)
		{
			temp.add(cards.remove((int)(Math.random()*cards.size())));
		}
		
		cards = temp;
	}
	
	
	public int size()
	{
		return cards.size();
	}
	
}
