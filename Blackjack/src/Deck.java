import java.util.ArrayList;
public class Deck {

	private ArrayList<Card> cards;
	private ArrayList<Hand> hands;
	
	Deck(int size)
	{
		cards = createDeck(size);
		shuffleDeck();
	}
	
	private ArrayList<Card> createDeck(int size)
	{
		ArrayList<Card> unshuffledDeck = new ArrayList<Card>();
		for(int i = 0; i < size/52; i++)
		{
			for(Suit suit: Suit.values())
			{
				for(Face face: Face.values())
				{
					Card createdCard = new Card(suit, face);
					unshuffledDeck.add(createdCard);
				}
			}
		}
		return unshuffledDeck;
	}
	
	public void shuffleDeck()
	{
		int size = cards.size();
		for(int i = 0; i < size; i++)
		{
			int switchIndex = (int)Math.floor((Math.random() * (size - 1)));
			Card tempSwitch = cards.get(switchIndex);
			Card tempI = cards.get(i);
			cards.remove(i);
			cards.add(i, tempSwitch);
			cards.remove(switchIndex);
			cards.add(switchIndex, tempI);
		}
	}
	
	public int getDeckSize()
	{
		return cards.size();
	}
	
	//Not sure about this parameter
	public void dealHand(int playerNumber)
	{
		if(cards.size() > 1)
		{
			Hand hand = new Hand(cards.get(0), cards.get(1));
			hands.add(playerNumber, hand);
			//Remove the top 2 cards from the deck
			cards.remove(0);
			cards.remove(0);
		}
	}
	
	public void dealCard(int playerNumber)
	{
		if(cards.size() > 0)
		{
			hands.get(playerNumber).addCard(cards.get(0));
			cards.remove(0);
		}
	}

	public ArrayList<Hand> getHands() {
		return hands;
	}


}