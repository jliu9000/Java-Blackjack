import java.util.ArrayList;


public class Game {
	private ArrayList<User> users;
	private Dealer dealer;
	public Deck deck = new Deck(52);
	Game(int numberOfPlayers)
	{
		users = createUser(numberOfPlayers);
		dealer = new Dealer(this.deck.dealHand());
	}

	private ArrayList<User> createUser(int size)
	{
		ArrayList<User> tempUser = new ArrayList<User>();

		for(int i =0; i < size; i++){
			User createdUser = new User(i, this.deck.dealHand());
			tempUser.add(createdUser);
		}
		return tempUser;
	}
}
