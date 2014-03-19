
public class Dealer extends Player {
	Dealer(Hand begHand)
	{
		this.hands.add(begHand);
	}
	public void play(Deck deck)
	{
		if(this.hands.get(0).getValueOfHand() < 17){
			this.hands.get(0).addCard(deck.dealCard());
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
