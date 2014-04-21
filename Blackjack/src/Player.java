import java.util.ArrayList;

import javax.swing.JOptionPane;



public class Player {
	public ArrayList<Hand> hands;
	public int numberOfHands;

	Player()
	{
		hands = createHand();
		numberOfHands = 1;
	}
	
	private ArrayList<Hand> createHand()
	{
		ArrayList<Hand> tempHand = new ArrayList<Hand>();
		return tempHand;
	}
	
	private boolean playerFinish()
	{
		if(numberOfHands!= 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public void clearHand(){
		hands.clear();
	}
	
	
	public void hit(Card card)
	{
		if(this.hands.get(numberOfHands-1).bustHand() || this.hands.get(numberOfHands-1).blackjackHand())
		{
			JOptionPane
			.showMessageDialog(null,
					"You have BlackJack, Try standing!!");
		}
		else
		{
			this.hands.get(numberOfHands-1).addCard(card);
		}
	}
	public void stand()
	{
		numberOfHands--;
	}
}
//hit function adds card to hand
//stand ends hand and moves game to next player