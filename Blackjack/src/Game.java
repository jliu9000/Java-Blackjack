import java.util.ArrayList;

public class Game {

	public ArrayList<User> users;
	public Dealer dealer;
	public Deck deck;
	ArrayList<Integer> winners = new ArrayList<Integer>();
	ArrayList<Integer> tie = new ArrayList<Integer>();

	Game(int numberOfPlayers) {
		dealer = new Dealer();
		users = new ArrayList<User>();
		for (int i = 1; i <= numberOfPlayers; i++) {
			users.add(new User());
		}
		deck = new Deck(52);
		deck.shuffleDeck();
		// dealer = new Dealer(this.deck.dealHand());
	}

	Game(int numberOfPlayers, int startingBank) {
		dealer = new Dealer();
		users = new ArrayList<User>();
		for (int i = 1; i <= numberOfPlayers; i++) {
			users.add(new User(startingBank));
		}
		deck = new Deck(52);
		deck.shuffleDeck();
		// dealer = new Dealer(this.deck.dealHand());
	}

	public void startingDeal() {
		for (User a : users) {
			if (a.startingBet > 0) {
				a.takeHand(deck.dealHand());
				a.hands.get(0).setBet(a.startingBet);
				a.takeBetFromBank();
			}
		}
		dealer.takeHand(deck.dealHand());
	}

	public void testStartingDeal() {
		for (User a : users) {
			if (a.startingBet > 0) {

				a.takeHand(deck.testDealCards(Suit.DIAMONDS, Face.ACE,
						Suit.HEARTS, Face.FIVE));
				a.hands.get(0).setBet(a.startingBet);
				a.takeBetFromBank();
			}
		}

		dealer.takeHand(deck.testDealCards(Suit.DIAMONDS, Face.SIX,
				Suit.HEARTS, Face.JACK));

	}

	private ArrayList<User> createUser(int size) {
		ArrayList<User> tempUser = new ArrayList<User>();

		for (int i = 0; i < size; i++) {
			tempUser.add(new User(i, this.deck.dealHand()));
		}
		return tempUser;
	}

	public void clearHands() {
		for (User u : users) {
			u.clearHand();
		}
		dealer.clearHand();
	}

	public void dealHand() {
		for (int i = 0; i < users.size(); i++) {
			users.get(i).hands.add(deck.dealHand());
		}
	}

	public void resetWinningsCounter() {
		for (User u : users) {
			u.clearWinnings();

		}
	}

	public void addUserWinnings() {
		for (User u : users) {
			u.collectWinnings();
		}
	}

	public String calculateWinners() {
		int dealerTotal;
		winners = new ArrayList<Integer>();
		tie = new ArrayList<Integer>();
		String winningMessage = "";
		dealerTotal = dealer.hands.get(0).getTotal();
		int i = 1;

		// dealer has blackjack on starting deal, end hand and calculate ties
		// (if any other players starting hand is 21)
		if (dealer.hands.get(0).getTotal() == 21
				&& dealer.hands.get(0).cards.size() == 2) {
			for (User u : users) {
				for (Hand h : u.hands) {

					if (h.getTotal() == dealerTotal) {
						if (!tie.contains(i)) {
							tie.add(i);
						}
						users.get(i).addMoneyWon(h.getBet());

					}
				}
				i++;
			}

		} else {

			// dealer busts, players who have no busted win.
			if (dealer.hands.get(0).isBust()) {
				for (User u : users) {
					for (Hand h : u.hands) {
						h.getTotal();
						if (!h.isBust()) {
							if (!winners.contains(i)) {
								winners.add(i);
							}
							if (h.getNumberOfCards() == 2
									&& h.getValueOfHand() == 21) {

								u.addMoneyWon(h.getBet() * 3 / 2 + h.getBet());
							} else {

								u.addMoneyWon(h.getBet() * 2);

							}
						}
					}
					i++;
				}
				winningMessage = "Dealer has busted!  Player(s) ";
				for (Integer j : winners) {
					winningMessage += j + " , ";
				}
				winningMessage = winningMessage.substring(0,
						winningMessage.length() - 3);
				winningMessage += " have won";
				return winningMessage;

			} else {

				// find users who have won or tied the dealer
				for (User u : users) {
					for (Hand h : u.hands) {

						if (h.getTotal() > dealerTotal && !h.isBust()) {
							if (!winners.contains(i)) {
								winners.add(i);
							}
							if (h.getNumberOfCards() == 2
									&& h.getValueOfHand() == 21) {

								u.addMoneyWon(h.getBet() * 3 / 2 + h.getBet());
							} else {

								u.addMoneyWon(h.getBet() * 2);

							}

						} else if (h.getTotal() == dealerTotal) {
							if (!tie.contains(i)) {
								tie.add(i);
							}
							u.addMoneyWon(h.getBet());

						}
					}
					i++;
				}
			}
		}

		// compute winning message
		if (!winners.isEmpty()) {
			winningMessage = "Player(s) ";
			for (Integer j : winners) {
				winningMessage += j + " , ";
			}
			winningMessage = winningMessage.substring(0,
					winningMessage.length() - 3);
			winningMessage += " have won";

			if (!tie.isEmpty()) {
				winningMessage += " Player(s) ";

				for (Integer j : tie) {
					winningMessage += i + " , ";
				}
				winningMessage = winningMessage.substring(0,
						winningMessage.length() - 3);

				winningMessage += " have tied with the Dealer";

			}

		} else if (!tie.isEmpty()) {
			winningMessage += "Player(s) ";

			for (Integer j : tie) {
				winningMessage += j + " , ";
			}

			winningMessage = winningMessage.substring(0,
					winningMessage.length() - 3);
			winningMessage += " have tied with the Dealer";
		} else {
			winningMessage = "Dealer has won!";
		}

		return winningMessage;

	}
}
