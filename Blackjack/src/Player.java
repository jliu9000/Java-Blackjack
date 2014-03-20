import java.util.ArrayList;



public class Player {
	public ArrayList<Hand> hands;
	public int numberOfHands;

	Player()
	{
		hands = createHand();
		numberOfHands = 1;
	}
	
	private ArrayList<Hand> createHand()
	{
		ArrayList<Hand> tempHand = new ArrayList<Hand>();
		return tempHand;
	}
	
	private boolean playerFinish()
	{
		if(numberOfHands!= 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public void clearHand(){
		hands.clear();
	}
	
	
	public void hit(Deck deck)
	{
		if (deck.getDeckSize() <= 0){
			System.out.println("Deck has no more cards");
		}
		else if(this.hands.get(numberOfHands-1).bustHand() || this.hands.get(numberOfHands-1).blackjackHand())
		{
			System.out.println("Cannot hit the hand is already busted or you have blackjack!");
		}
		else
		{
			this.hands.get(numberOfHands-1).addCard(deck.dealCard());
		}
	}
	public void stand()
	{
		numberOfHands--;
	}
}
//hit function adds card to hand
//stand ends hand and moves game to next player