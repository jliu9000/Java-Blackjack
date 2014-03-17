
public class User extends Player {
	private int playerNumber;
	User(int ID, Hand begHand)
	{
		playerNumber = ID;
		hand = begHand;
	}
	public void doubleDown(Deck deck)
	{
		this.hand.addCard(deck.dealCard());
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