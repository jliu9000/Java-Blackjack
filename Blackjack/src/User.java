
public class User extends Player {
	private int playerNumber;
	User(int ID, Hand begHand)
	{
		playerNumber = ID;
		this.hands.add(begHand);
	}
	public void doubleDown(Deck deck)
	{
		this.hands.get(numberOfHands-1).addCard(deck.dealCard());
		stand();
		numberOfHands--;
	}
	public void split(Deck deck)
	{
		if (this.hands.get(0).getNumberOfCards() != 2)
		{
			System.out.println("Can only split on initial hand");
		}
		else{
		Hand secondHand = this.hands.get(numberOfHands-1).splitHand(deck);
		this.hands.add(secondHand);
		numberOfHands++;
		}
	}
}
//split()
/**********
uses GameLogic to create new hand,
copy 1 card from hand,
delete card from original hand,
hit() on both hands automatically (fills hand),
play each hand regularly.
*****************/

//doubleDown()
/*******************
calls bet() and doubles the original bet
calls hit() to add one final card
calls stand() without user selecting to finishHand()
*******************/
//bet()
/*******************

**********************/