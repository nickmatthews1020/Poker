import java.util.ArrayList;

public class Hand {

	private ArrayList<Card> cards;
	
	public Hand() // creates an empty hand
	{
		cards = new ArrayList<Card>();
	}
	
	public Hand(ArrayList<Card> x) // creates a hand with cards in given arrayList
	{
		cards = new ArrayList<Card>();
		for (int i = 0; i < x.size(); i++)
		{
			cards.addAll(x);
		}
	}
	
	public ArrayList<Card> getCards() // returns the arrayList of cards in a hand
	{
		return cards;
	}
	
	public Card getCard(int index) // returns card at the specified location of a hand
	{
		return cards.get(index);
	}
	
	public Card getBigADV() // returns the first instance of the largest value single card
	{
		Card big = cards.get(0);
		for (int i = 0; i < cards.size(); i ++)
		{
			if (big.getValue() < cards.get(i).getValue())
				big = cards.get(i);
		}
		return big;
	}
	
	public int getBigPOS() // returns the position of the largest value single card
	{
		Card big = cards.get(0);
		int position = 0;
		for (int i = 0; i < cards.size(); i ++)
		{
			if (big.getValue() < cards.get(i).getValue())
			{
				big = cards.get(i);
				position = i;
			}
		}
		
		return position;
	}
	
	public int getBig() // returns value of the highest card in hand
	{
		int big = cards.get(0).getValue();
		for (int i = 0; i < cards.size(); i ++)
		{
			if (big < cards.get(i).getValue())
				big = cards.get(i).getValue();
		}
		return big;
	}
	
	public int getNextBigPOS() // returns the value of the next highest card
	{
		int big = 0;
		int pos = getBigPOS();
		int positionF = 0;
		
		for (int i = 0; i < pos; i++)
		{
			if(cards.get(i).getValue() > big)
			{
				big = cards.get(i).getValue();
				positionF = i;
			}
		}
		
		for (int i = 0; i < cards.size() - pos; i++)
		{
			if(cards.get(i).getValue() > big)
			{
				big = cards.get(i).getValue();
				positionF = i;
			}
		}
		
		return big;
	}
	
	public int getNextBig() // returns the value of the next highest card
	{
		int big = 0;
		int pos = getBigPOS();
		
		
		for (int i = 0; i < pos; i++)
		{
			if(cards.get(i).getValue() > big)
			{
				big = cards.get(i).getValue();
			}
		}
		
		for (int i = pos+1; i < cards.size(); i++)
		{
			if(cards.get(i).getValue() > big)
			{
				big = cards.get(i).getValue();
				
			}
		}
		
		return big;
	}
	
	public void addCard(Card c) // adds a card to the top of a player's hand
	{
		cards.add(0,c);
	}
	
	public int size()
	{
		return cards.size();
	}
	
	// PRECONDITION: exactly 2 cards in hand
	public int getRank2() // 0 -> high card; 1 -> pair
	{
		if (cards.get(0).getValue() == cards.get(1).getValue())
			return 1;
		return 0;
	}
	
	public boolean pair2()
	{
		if (cards.get(0).getValue() == cards.get(1).getValue())
			return true;
		return false;
	}
	
	
	
	//methods to be used in GameADV class
	
	public boolean pair()
	{
		for (int i = 0; i < cards.size() - 1; i++)
		{
			for (int j = i + 1; j < cards.size(); j++)
			{
				if (cards.get(i).getValue() == cards.get(j).getValue())
					return true;
			}
		}
		
		return false;
	}
	
	public boolean pair(ArrayList<Card> x)
	{
		for (int i = 0; i < x.size() - 1; i++)
		{
			for (int j = i + 1; j < x.size(); j++)
			{
				if (x.get(i).getValue() == x.get(j).getValue())
					return true;
			}
		}
		
		return false;
	}
	
	public int pairValue() //returns value of biggest pair; returns 0 if no pairs
	{
		int big = 0;
		
		if (pair())
		{
			for (int i = 0; i < cards.size() - 1; i++)
			{
				for (int j = i + 1; j < cards.size(); j++)
				{
					if (cards.get(i).getValue() == cards.get(j).getValue())
						if (big <= cards.get(i).getValue())
							big = cards.get(i).getValue();
				}
			}
		}
		
		return big;
	}
	
	public int pairValue(ArrayList<Card> x) //returns value of biggest pair; returns 0 if no pairs
	{
		int big = 0;
		
		if (pair())
		{
			for (int i = 0; i < x.size() - 1; i++)
			{
				for (int j = i + 1; j < x.size(); j++)
				{
					if (x.get(i).getValue() == x.get(j).getValue())
						if (big <= x.get(i).getValue())
							big = x.get(i).getValue();
				}
			}
		}
		
		return big;
	}
	
	
	public boolean twoPair()
	{
		ArrayList<Card> temp = new ArrayList<Card>();
		for (int i = 0; i < cards.size(); i ++)
			temp.add(cards.get(i));
		
		if (pair())
		{
			for (int i = 0; i < temp.size(); i++)
			{
				if (temp.get(i).getValue() == pairValue())
				{
					temp.remove(i);
					i--;
				}
			}
			
			if (pair(temp))
				return true;
		}
		
		return false;
	}
	
	public int[] twoPairValue() // returns a 2 space array of integers for the card value of each of the two pairs
	{
		ArrayList<Card> temp = new ArrayList<Card>(); // not same as ArrayList new = cards !!!!
		for (int i = 0; i < cards.size(); i ++)
			temp.add(cards.get(i));
		int value1 = 0;
		int value2 = 0;
		
		if (pair())
		{
			value1 = pairValue();
			for (int i = 0; i < temp.size(); i++)
			{
				if (temp.get(i).getValue() == pairValue())
				{
					temp.remove(i);
					i--;
				}
			}
			
			if (pair(temp))
				value2 = pairValue(temp);
		}
		
		int values[] = {value1,value2};
		
		return values;
		
	}
	
	
	public boolean threeOfAKind()
	{
		int count = 0;
		for (int i = 2; i < 15; i++)
		{
			count = 0;
			for (int j = 0; j < cards.size(); j++)
			{
				if (cards.get(j).getValue() == i)
					count ++;
			}
			if (count == 3)
				return true;
		}
		return false;
	}
	
	public int threeOfAKindValue() // returns 0 if no three of a kind
	{
		int count = 0;
		for (int i = 2; i < 15; i++)
		{
			count = 0;
			for (int j = 0; j < cards.size(); j++)
			{
				if (cards.get(j).getValue() == i)
					count ++;
			}
			if (count == 3)
				return i;
		}
		return 0;
	}
	
	
	public boolean straight()
	{
		if (cards.size() < 5)
			return false;
		
		int sum = 0;
		int big = 0;
		int small = 5;
		for (int i1 = 0; i1 < cards.size() - 4; i1++)
		{
			for (int i2 = i1+1; i2 < cards.size() - 3; i2++)
			{
				for (int i3 = i2+1; i3 < cards.size() - 2; i3++)
				{
					for (int i4 = i3+1; i4 < cards.size() - 1; i4++)
					{
						for (int i5 = i4+1; i5 < cards.size(); i5++)
						{
							big = 0;
							small = 5;
							sum = 0;
							
							if (Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i2).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i3).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i3).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i3).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i3).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i4).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue());

							sum = Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()); 
									
							if (small == 1 && big == 4 && sum == 20)
								return true;
							if (small == 1 && big == 12 && sum == 52)
								return true;		
						}
					}
				}
			}
		}
		
		return false;
	}
	
	
	public Hand straightHand() // returns largest value straight possible
	{
		if (cards.size() < 5)
			return null;
		
		Hand s = new Hand();
		
		int large = 0;
		int sum = 0;
		int big = 0;
		int small = 5;
		for (int i1 = 0; i1 < cards.size() - 4; i1++)
		{
			for (int i2 = i1+1; i2 < cards.size() - 3; i2++)
			{
				for (int i3 = i2+1; i3 < cards.size() - 2; i3++)
				{
					for (int i4 = i3+1; i4 < cards.size() - 1; i4++)
					{
						for (int i5 = i4+1; i5 < cards.size(); i5++)
						{
							big = 0;
							small = 5;
							sum = 0;
							
							if (Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i2).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i3).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i1).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i3).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i2).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) > big)
								big = Math.abs(cards.get(i3).getValue()-cards.get(i4).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) < small)
								small = Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i3).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue());
							if (Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()) > big)
								big = Math.abs(cards.get(i4).getValue()-cards.get(i5).getValue());
							if (Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()) < small)
								small = Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue());

							sum = Math.abs(cards.get(i1).getValue() - cards.get(i2).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i3).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i1).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i3).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i2).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i3).getValue() - cards.get(i4).getValue()) + Math.abs(cards.get(i3).getValue() - cards.get(i5).getValue()) + Math.abs(cards.get(i4).getValue() - cards.get(i5).getValue()); 
									
							if (small == 1 && big == 4 && sum == 20)
							{
								if (cards.get(i1).getValue() > large)
								{
									large = cards.get(i1).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i2).getValue() > large)
								{
									large = cards.get(i2).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i3).getValue() > large)
								{
									large = cards.get(i3).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i4).getValue() > large)
								{
									large = cards.get(i4).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i5).getValue() > large)
								{
									large = cards.get(i5).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
							}
							if (small == 1 && big == 12 && sum == 52)
							{
								if (cards.get(i1).getValue() > large && cards.get(i1).getValue() != 14)
								{
									large = cards.get(i1).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i2).getValue() > large && cards.get(i2).getValue() != 14)
								{
									large = cards.get(i2).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i3).getValue() > large && cards.get(i3).getValue() != 14)
								{
									large = cards.get(i3).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i4).getValue() > large && cards.get(i4).getValue() != 14)
								{
									large = cards.get(i4).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
								if (cards.get(i5).getValue() > large && cards.get(i5).getValue() != 14)
								{
									large = cards.get(i5).getValue();
									s = new Hand();
									s.addCard(cards.get(i1));
									s.addCard(cards.get(i2));
									s.addCard(cards.get(i3));
									s.addCard(cards.get(i4));
									s.addCard(cards.get(i5));
								}
							}
							
						}
					}
				}
			}
		}
		return s;
	}
	
	
	public int straightValue()
	{
		int big = 0;
		for (int i = 0; i < straightHand().size(); i++)
		{
			if (straightHand().getCard(i).getValue() > big)
				big = straightHand().getCard(i).getValue();
		}
		
		return big;
	}
	
	
	
	
}
