import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> deck;
	Deck(int size)
	{
		deck = createDeck(size);
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
		for(int i = 0; i < deck.size(); i++)
		{
			int switchIndex = (int)Math.floor((Math.random() * (deck.size() - 1)));
			Card tempSwitch = deck.get(switchIndex);
			Card tempI = deck.get(i);
			deck.remove(i);
			deck.add(i, tempSwitch);
			deck.remove(switchIndex);
			deck.add(switchIndex, tempI);
		}
	}
	
	public int getDeckSize()
	{
		return deck.size();
	}
	
	public void dealCard()
	{
		
	}
}