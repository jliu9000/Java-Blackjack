import java.util.ArrayList;



public class Player {
	public ArrayList<Hand> hands;
	public int numberOfHands;
	
	Player()
	{
		hands = createHand();
	}
	
	private ArrayList<Hand> createHand()
	{
		ArrayList<Hand> tempHand = new ArrayList<Hand>();
		return tempHand;
	}
	public void hit(Deck deck)
	{
		if(this.hands.get(0).bustHand() || this.hands.get(0).blackjackHand())
		{
			System.out.println("Cannot hit the hand is already busted or you have blackjack!");
		}
		else
		{
			this.hands.get(0).addCard(deck.dealCard());
		}
	}
	public void stand()
	{
		//finishHand;
	}
}
//hit function adds card to hand
//stand ends hand and moves game to next player