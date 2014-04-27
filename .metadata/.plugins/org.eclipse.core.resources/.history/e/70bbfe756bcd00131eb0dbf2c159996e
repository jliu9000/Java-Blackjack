
public class Bank {
	private double totalMoney;
	Bank(){
		totalMoney =1000;
	}
	
	public void addMoney(double bet){
		totalMoney += 2*bet; //bet is won double the original bet
	}
	
	public void returnMoney(double bet){
		totalMoney += bet; // hand was pushed return original bet
	}
	
	public void addBlackJack(double bet){
		totalMoney += bet+(3/2)*bet; //player has blackjack
	}
	
	public void removeMoney(double bet){
			totalMoney -= bet;
	}
	
	public double checkMoney(){
		return totalMoney;
	}
}
