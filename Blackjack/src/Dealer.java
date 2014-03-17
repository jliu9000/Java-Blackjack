
public class Dealer extends Player {
	public Hand initialHand;
	Dealer(Hand hand)
	{
		initialHand = hand;
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
