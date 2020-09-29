
public class Client {

	public static void main(String[] args) {
		
		
		int initialChips = 100;
		int inititalPot = 0;
		int tableAnte = 10;
		
		System.out.println("WELCOME TO POKER");
		System.out.println("TABLE ANTE: " + tableAnte);
		System.out.println();
	
		
		//Deck x = new Deck();
				
		//for (int i = 0 ; i < x.size(); i++)
		//	System.out.println(x.getCards().get(i).toString());
		
		
		//System.out.println();
		//x.shuffle();
		
		//for (int i = 0 ; i < x.size(); i++)
		//	System.out.println(x.getCards().get(i).toString());
		
		
		Game oneCard = new Game(initialChips, inititalPot);
		
		oneCard.play2Card2RoundAI(tableAnte);
		

	}

}
