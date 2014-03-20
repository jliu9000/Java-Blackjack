import java.util.ArrayList;


public class Game {
	
	public ArrayList<User> users;
	public Dealer dealer;
	public Deck deck;
	
	
	Game(int numberOfPlayers)
	{
		dealer = new Dealer();
		users = new ArrayList<User>();
		for (int i = 1; i<= numberOfPlayers; i++){
			users.add(new User());
		}
		deck = new Deck(52);
		deck.shuffleDeck();
		//dealer = new Dealer(this.deck.dealHand());
	}
	
	public void startingDeal(){
		for (User a : users){
			a.takeHand(deck.dealHand());
		}
		dealer.takeHand(deck.dealHand());
	}

	private ArrayList<User> createUser(int size)
	{
		ArrayList<User> tempUser = new ArrayList<User>();

		for(int i =0; i < size; i++){
			tempUser.add(new User(i, this.deck.dealHand()));
		}
		return tempUser;
	}
	
	public void dealHand(){
		for (int i =0; i<users.size(); i++){
			users.get(i).hands.add(deck.dealHand());
		}
	}
}
