import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards;
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
	
	public void dealCard()
	{
		
	}
}