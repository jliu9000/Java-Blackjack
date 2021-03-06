import java.util.ArrayList;

public class Dealer extends Player {
	Dealer(Hand begHand) {
		this.hands.add(begHand);

	}

	Dealer() {
		hands = new ArrayList<Hand>();
	}

	public void takeHand(Hand h) {
		hands.add(h);
		numberOfHands++;
	}

	public void play(Deck deck) {

		this.hands.get(0).addCard(deck.dealCard());
	}
}

// play()
/***************
 * plays the dealer hand automatically through a function that follows the logic
 * if(calculateTotal() < 17) hit(); else finishHand(); finally distribute the
 * booty. compareHands(); payOut();
 * 
 * 
 * ****************/
