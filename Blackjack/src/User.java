
public class User extends Player {
	private int playerNumber;
	public Hand hand;
	User(int ID, Hand intialHand)
	{
		playerNumber = ID;
		hand = intialHand;
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