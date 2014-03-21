import java.util.ArrayList;

public class User extends Player {
	private int playerNumber;
	private int currentHand;

	User(int ID, Hand begHand) {
		// playerNumber = ID;
		// this.hands.add(begHand);
	}

	User() {
		hands = new ArrayList<Hand>();
		numberOfHands = 0;
	}

	public void takeHand(Hand h) {
		hands.add(h);
		numberOfHands++;
	}

	public void doubleDown(Deck deck) {
		if (hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
			System.out.println("Can only double down on initial hand");
		} else {
			this.hands.get(numberOfHands - 1).addCard(deck.dealCard());
			stand();
		}
	}

	public void split(Deck deck) {
		if (this.hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
			System.out.println("Can only split on initial hand");
		} else {
			if (this.hands.get(0).cards.get(0).getFace() == this.hands.get(0).cards
					.get(1).getFace()) 
			{
				Hand secondHand = new Hand();
				secondHand = this.hands.get(0).splitHand(deck);
				this.hands.add(secondHand);

				numberOfHands++;
			} else {
				System.out.println("cant split, suits not the same");
			}
		}
	}

	public void clearHand() {
		hands.clear();
		numberOfHands = 0;
	}
}

// doubleDown()
/*******************
 * calls bet() and doubles the original bet calls hit() to add one final card
 * calls stand() without user selecting to finishHand()
 *******************/
// bet()
/*******************

**********************/
