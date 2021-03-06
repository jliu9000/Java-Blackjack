import java.util.ArrayList;
public class Deck {

	private ArrayList<Card> cards;
	private Hand tempHand;
	
	Deck(int size)
	{
		cards = createDeck(size);
		shuffleDeck();
	}
	
	public void printDeckCards(){
		if (!cards.isEmpty()){
			int count = 1;
			for (Card c : cards){
				System.out.println(count +": " +c.toString());
				count++;
			}
		}
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
	public Hand dealHand()
	{
			if(cards.size()<2){
				cards = createDeck(52);
				shuffleDeck();

			}
			tempHand = new Hand(cards.get(0), cards.get(1));
		//Remove the top 2 cards from the deck
			cards.remove(0);
			cards.remove(0);
			return tempHand;
	}
	
	public Hand testDealCards(Suit s1, Face f1, Suit s2, Face f2) {
		Card c1 = new Card(s1, f1);
		Card c2 = new Card(s2, f2);
		Hand h = new Hand(c1, c2);
		
		return h;
	}
	
	public Card dealCard()
	{
			if(cards.size()<1){
				cards = createDeck(52);
				shuffleDeck();
			}


			Card tempCard = cards.get(0);
			cards.remove(0);
			return tempCard;
	}
}