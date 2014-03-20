import java.util.ArrayList;


public class User extends Player {
	private int playerNumber;
	
	
	User(int ID, Hand begHand)
	{
		//playerNumber = ID;
		//this.hands.add(begHand);
	}
	
	User(){
		hands = new ArrayList<Hand>();
	}
	
	public void takeHand(Hand h){
		hands.add(h);
	}
	
	
	public void doubleDown(Deck deck)
	{
		if(hands.get(numberOfHands-1).getNumberOfCards() != 2){
			System.out.println("Can only double down on initial hand");
		}
		else{
		this.hands.get(numberOfHands-1).addCard(deck.dealCard());
		stand();
		numberOfHands--;
		}
	}
	public void split(Deck deck)
	{
		if (this.hands.get(numberOfHands-1).getNumberOfCards() != 2)
		{
			System.out.println("Can only split on initial hand");
		}
		else{
		Hand secondHand = this.hands.get(numberOfHands-1).splitHand(deck);
		this.hands.add(secondHand);
		numberOfHands++;
		}
	}
}


//doubleDown()
/*******************
calls bet() and doubles the original bet
calls hit() to add one final card
calls stand() without user selecting to finishHand()
*******************/
//bet()
/*******************

**********************/