import java.util.ArrayList;

public class User extends Player {
	private int playerNumber;
	private int currentHand;
	private Bank bank;


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
	
	public void bet(double bet){
		if(bet > 1.0 && bet < bank.checkMoney())
		{
			this.hands.get(numberOfHands - 1).setBet(bet);
		}
	}
	
	public void doubleDown(Deck deck) {
		//check to see if there is enough money to bet more
		double currentBet = this.hands.get(numberOfHands - 1).getBet();
		if(currentBet > bank.checkMoney()){
			//Display message to the screen
		}else{
			if(hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
				//Display message to screen that you can only double down with 2 cards
			} else {
				bank.removeMoney(currentBet);
				this.hands.get(numberOfHands - 1).setBet(2*currentBet);
				this.hands.get(numberOfHands - 1).addCard(deck.dealCard());
				stand();
			}
		}
	}

	public void split(Deck deck) {
		//check to see if there is enough money to bet more
		double currentBet = this.hands.get(numberOfHands - 1).getBet();
		
		if(currentBet > bank.checkMoney()){
			System.out.println("There isn't enough money to split");
		}else{
			if (this.hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
				System.out.println("Can only split on initial hand");
			} else {
				if (this.hands.get(0).cards.get(0).getFace() == this.hands.get(0).cards
						.get(1).getFace()) 
				{
					Hand secondHand = new Hand();
					secondHand = this.hands.get(0).splitHand(deck);
					secondHand.setBet(currentBet);
					bank.removeMoney(currentBet);
					this.hands.add(secondHand);
					numberOfHands++;
				} else {
					System.out.println("The card faces are not the same, you cannot split");
				}
			}
		}
	}

	public void clearHand() {
		hands.clear();
		numberOfHands = 0;
	}
}
