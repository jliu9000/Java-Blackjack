import java.util.ArrayList;

import javax.swing.JOptionPane;

public class User extends Player {
	private int playerNumber;
	private int currentHand;
	private Bank bank;
	public int startingBet = 0;
	private int moneyWon = 0;

	public int getMoneyWon() {
		return moneyWon;
	}

	public void clearWinnings() {
		moneyWon = 0;
	}

	public void addMoneyWon(int i) {
		moneyWon += i;
	}

	User(int ID, Hand begHand) {
		// playerNumber = ID;
		// this.hands.add(begHand);
	}

	public Bank getBank() {
		return bank;
	}

	public void collectWinnings() {
		bank.add(moneyWon);
	}

	public void takeBetFromBank() {
		bank.removeMoney(startingBet);
	}

	public void increaseBet() {
		if (startingBet < bank.checkMoney()) {
			startingBet += 10;
		} else {
			startingBet += bank.checkMoney() - startingBet;

		}
	}

	public void decreaseBet() {
		if (startingBet > 0) {
			startingBet -= 10;
		}
	}

	User() {
		hands = new ArrayList<Hand>();
		numberOfHands = 0;
		bank = new Bank();
	}

	User(int startingBank) {
		hands = new ArrayList<Hand>();
		numberOfHands = 0;
		bank = new Bank(startingBank);
	}

	public void takeHand(Hand h) {
		hands.add(h);
		numberOfHands++;
	}

	public void bet(int bet) {
		if (bet > 1 && bet < bank.checkMoney()) {
			this.hands.get(numberOfHands - 1).setBet(bet);
		}
	}

	public boolean doubleDown(Deck deck) {
		// check to see if there is enough money to bet more
		int currentBet = this.hands.get(numberOfHands - 1).getBet();
		if (currentBet > bank.checkMoney()) {
			// Display message to the screen
		} else {
			if (hands.get(numberOfHands - 1).getNumberOfCards() != 2) {
				// Display message to screen that you can only double down with
				// 2 cards
			} else {
				bank.removeMoney(currentBet);
				this.hands.get(numberOfHands - 1).setBet(2 * currentBet);
				this.hands.get(numberOfHands - 1).addCard(deck.dealCard());
				stand();
				return true;
			}
		}
		return false;
		
		
	}
	
	public boolean doubleDown(Deck deck, int currentHand) {
		// check to see if there is enough money to bet more
		int currentBet = this.hands.get(currentHand).getBet();
		if (currentBet > bank.checkMoney()) {
			// Display message to the screen
		} else {
			if (hands.get(currentHand).getNumberOfCards() != 2) {
				// Display message to screen that you can only double down with
				// 2 cards
			} else {
				bank.removeMoney(currentBet);
				this.hands.get(currentHand).setBet(2 * currentBet);
				this.hands.get(currentHand).addCard(deck.dealCard());
				return true;

			}
		}
		
		return false;

	}


	public void split(Deck deck) {
		// check to see if there is enough money to bet more
		int currentBet = this.hands.get(numberOfHands - 1).getBet();

		if (currentBet > bank.checkMoney()) {
			System.out.println("There isn't enough money to split");
		} else {
			if (this.hands.get(numberOfHands - 1).getNumberOfCards() != 2
					|| numberOfHands > 1) {
				System.out.println("Can only split on initial hand");
			} else {
				if (this.hands.get(0).cards.get(0).getFace() == this.hands
						.get(0).cards.get(1).getFace()) {
					Hand secondHand = new Hand();
					secondHand = this.hands.get(0).splitHand(deck);
					secondHand.setBet(currentBet);
					bank.removeMoney(currentBet);
					this.hands.add(secondHand);
					numberOfHands++;
				} else {
					System.out
							.println("The card faces are not the same, you cannot split");
				}
			}
		}
	}

	public void hit(Card card, int hand) {

		this.hands.get(hand).addCard(card);

	}

	public void clearHand() {
		hands.clear();
		numberOfHands = 0;
	}
}
