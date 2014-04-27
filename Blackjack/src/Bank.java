
public class Bank {
	private int totalMoney;
	
	Bank(){
		totalMoney =1000;
	}
	
	Bank(int m){
		totalMoney = m;
	}
	
	public void add(int i){
		totalMoney += i;
	}
	
	public void addMoney(int bet){
		totalMoney += 2*bet; //bet is won double the original bet
	}
	
	public void returnMoney(int bet){
		totalMoney += bet; // hand was pushed return original bet
	}
	
	public void addBlackJack(int bet){
		totalMoney += bet+(3/2)*bet; //player has blackjack
	}
	
	public void removeMoney(int bet){
			totalMoney -= bet;
	}
	
	public int checkMoney(){
		return totalMoney;
	}
	

	
	
	
	
}
