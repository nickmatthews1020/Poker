
public class HandRankClient {

	public static void main(String[] args) {
	
	
	Hand testPair = new Hand();
	
	testPair.addCard(new Card(14,1));
	testPair.addCard(new Card(14,2));
	testPair.addCard(new Card(13,2));
	testPair.addCard(new Card(12,2));
	testPair.addCard(new Card(11,2));
	
	for (int i = 0; i < testPair.size(); i++)
		System.out.print(testPair.getCard(i) + " ");
	System.out.println();
	
	System.out.println(testPair.pair());
	System.out.println(testPair.pairValue());
	
	
	Hand test2Pair = new Hand();
	
	test2Pair.addCard(new Card(14,1));
	test2Pair.addCard(new Card(14,2));
	test2Pair.addCard(new Card(13,2));
	test2Pair.addCard(new Card(13,2));
	test2Pair.addCard(new Card(11,2));
	
	for (int i = 0; i < test2Pair.size(); i++)
		System.out.print(test2Pair.getCard(i) + " ");
	System.out.println();
	
	System.out.println(test2Pair.twoPair());
	
	for (int i = 0; i < test2Pair.twoPairValue().length; i++)
		System.out.print(test2Pair.twoPairValue()[i] + " ");
	System.out.println();
	
	
	Hand test3 = new Hand();
	
	test3.addCard(new Card(14,1));
	test3.addCard(new Card(14,2));
	test3.addCard(new Card(12,3));
	test3.addCard(new Card(12,2));
	test3.addCard(new Card(12,4));
	
	for (int i = 0; i < test3.size(); i++)
		System.out.print(test3.getCard(i) + " ");
	System.out.println();
	
	System.out.println(test3.threeOfAKind());
	System.out.println(test3.threeOfAKindValue());
	
	
	
	Hand testStraight = new Hand();
	
	testStraight.addCard(new Card(14,1));
	testStraight.addCard(new Card(2,2));
	testStraight.addCard(new Card(3,3));
	testStraight.addCard(new Card(2,2));
	testStraight.addCard(new Card(5,4));
	testStraight.addCard(new Card(6,2));
	testStraight.addCard(new Card(4,2));

	
	for (int i = 0; i < testStraight.size(); i++)
		System.out.print(testStraight.getCard(i) + " ");
	System.out.println();
	
	System.out.println(testStraight.straight());
	for (int i = 0; i < testStraight.straightHand().size(); i++)
	{
		System.out.print(testStraight.straightHand().getCard(i) + " ");
		
	}
	System.out.println()
	System.out.println(testStraight.straightValue());
	
	
	
	
	}
}
