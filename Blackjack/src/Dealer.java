
public class Dealer extends Player {
	Dealer(Hand begHand)
	{
		this.hand= begHand;
	}
	public void play(Deck deck)
	{
		if(this.hand.getValueOfHand() < 17){
			this.hand.addCard(deck.dealCard());
		}else{
			stand();
		}
	}
}

//play()
/***************
 * plays the dealer hand automatically through a function that 
 * follows the logic
 * if(calculateTotal() < 17) hit();
 * else finishHand();
 * finally distribute the booty.
 * compareHands();
 * payOut();
 * 
 * 
 * ****************/
