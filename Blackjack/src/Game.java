import java.util.ArrayList;

public class Game {

	public ArrayList<User> users;
	public Dealer dealer;
	public Deck deck;

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

	public void startingDeal() {
		for (User a : users) {
			a.takeHand(deck.dealHand());
		}
		dealer.takeHand(deck.dealHand());
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

	public String calculateWinners() {
		int dealerTotal;
		ArrayList<Integer> winners = new ArrayList<Integer>();
		ArrayList<Integer> tie = new ArrayList<Integer>();
		String winningMessage = "";
		dealerTotal = dealer.hands.get(0).getTotal();
		int i = 1;

		//dealer busts, players who have no busted win.
		if (dealer.hands.get(0).isBust()) {
			for (User u : users) {
				for (Hand h : u.hands) {
					h.getTotal();
					if (!h.isBust()) {
						winners.add(i);
					}
				}
				i++;
			}
			winningMessage = "Dealer has busted!  Player(s) ";
			for (Integer j : winners) {
				winningMessage += j + " , ";
			}
			winningMessage = winningMessage.substring(0, winningMessage.length() - 3);
			winningMessage += " have won";
			return winningMessage;
			
		}

		// find users who have won or tied the dealer
		for (User u : users) {
			for (Hand h : u.hands) {

				if (h.getTotal() > dealerTotal && !h.isBust()) {
					winners.add(i);
				} else if (h.getTotal() == dealerTotal) {
					tie.add(i);
				}
			}
			i++;
		}

		if (!winners.isEmpty()) {
			winningMessage = "Player(s) ";
			for (Integer j : winners) {
				winningMessage += j + " , ";
			}
			winningMessage = winningMessage.substring(0,
					winningMessage.length() - 3);
			winningMessage += " have won";

			if (!tie.isEmpty()) {
				winningMessage += " Player(s)";

				for (Integer j : tie) {
					winningMessage += i + " , ";
				}
				winningMessage = winningMessage.substring(0,
						winningMessage.length() - 3);

				winningMessage += " have tied with the Dealer";

			}


		} else if (!tie.isEmpty()) {
			winningMessage += "Players";

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
