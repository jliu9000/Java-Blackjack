

public class Player {
	public Hand hand;
	
	Player()
	{

	}
	public void hit(Deck deck)
	{
		if(this.hand.bustHand() || this.hand.blackjackHand())
		{
			System.out.println("Cannot hit the hand is already busted or you have blackjack!");
		}
		else
		{
			this.hand.addCard(deck.dealCard());
		}
	}
	public void stand()
	{
		
	}
}
//hit function adds card to hand
//stand ends hand and moves game to next player