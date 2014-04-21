import java.util.ArrayList;

public class User extends Player {
	private int playerNumber;
	private int currentHand;
	private Bank bank;
	private double bet;


	User(int ID, Hand begHand) {
		// playerNumber = ID;
		// this.hands.add(begHand);
	}

	User() {
		hands = new ArrayList<Hand>();
		numberOfHands = 0;
		bank = new Bank();
	}

	public void takeHand(Hand h) {
		hands.add(h);
		numberOfHands++;
	}

	
	public void doubleDown(Deck deck) {
		//check to see if there is enough money to bet more
		if(bet > bank.checkMoney()){
			System.out.println("There isn't enough money to double down");
		}else{
			bank.removeMoney(bet);
			if (hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
				System.out.println("Can only double down on initial hand");
			} else {
				this.hands.get(numberOfHands - 1).addCard(deck.dealCard());
				stand();
			}
		}
	}

	public void split(Deck deck) {
		//check to see if there is enough money to bet more
		
		if(bet > bank.checkMoney()){
			System.out.println("There isn't enough money to split");
		}else{
			bank.removeMoney(bet);
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
	}

	public void clearHand() {
		hands.clear();
		numberOfHands = 0;
	}
}
