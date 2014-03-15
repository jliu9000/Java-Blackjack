import java.util.ArrayList;
public class Hand {

	private boolean bust = false;
	private boolean blackjack = false;
	private int total = 0;
	private ArrayList<Card> cards;
	Hand(Card card1, Card card2)
	{
		cards.add(card1);
		cards.add(card2);
		total = calculateTotal();
	}
	
	public void addCard(Card card)
	{
		cards.add(card);
		total = calculateTotal();
	}
	
	private int calculateTotal()
	{
		moveAces();
		int handSize = cards.size();
		boolean hasBlackjack = false;
		if(handSize == 2)
		{
			hasBlackjack = checkBlackjack();
		}
		int total = 0;
		Face temp;
		if(hasBlackjack == true)
		{
			blackjack = true;
			return 21;
		}
		for(int i = 0; i < handSize; i++)
		{
			temp = cards.get(i).getFace();
			switch (temp){
				case ACE:
					if(total <= 10 && i == handSize - 1)
					{
						total += 11;
					}
					else
					{
						total += 1;
					}
					break;
					
				default:
					total += temp.getValue();
			}
		}
		if(total > 21)
		{
			bust = true;
		}
		return total;
	}
	
	private boolean checkBlackjack()
	{
		if(cards.get(0).getFace() == Face.JACK && cards.get(1).getFace() == Face.ACE)
		{
			return true;
		}
		return false;
	}
	
	//Helper function for calculating total.
	//Moves aces to end of the hand so it is easier to determine the best value for any aces
	private void moveAces()
	{
		int numOfAces = 0;
		int handSize = cards.size();
		Card tempCard;
		Card currentCard;
		//Don't need to check the final card, regardless of it being an ace or not
		for(int i = 0; i < handSize - numOfAces - 1; i++)
		{
			currentCard = cards.get(i);
			if(currentCard.getFace() == Face.ACE)
			{
				tempCard = cards.get(handSize - numOfAces - 1);
				cards.remove(handSize - numOfAces - 1);
				cards.remove(i);
				cards.add(currentCard);
				cards.add(i, tempCard);
			}
		}
	}
	
	public int getNumberOfCards()
	{
		return cards.size();
	}
	
	public int getValueOfHand()
	{
		return total;
	}
	
	public boolean blackjackHand()
	{
		return blackjack;
	}
	
	public boolean bustHand()
	{
		return bust;
	}
}
