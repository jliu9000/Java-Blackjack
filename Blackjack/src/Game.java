import java.util.ArrayList;


public class Game {
	private ArrayList<User> users;
	private Dealer dealer;
	public Deck deck = new Deck(52);
	Game(int numberOfPlayers)
	{
		users = createUser(numberOfPlayers);
		dealer = new Dealer(deck.dealHand(numberOfPlayers+1));
	}

	private ArrayList<User> createUser(int size)
	{
		ArrayList<User> tempUser = new ArrayList<User>();

		for(int i =0; i < size; i++){
			User createdUser = new User(i, deck.dealHand(i));
			tempUser.add(createdUser);
		}
		return tempUser;
	}
}
