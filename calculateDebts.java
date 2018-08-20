/*
Sarah rents a car for the trip - she pays $400 for the car, which is used by Alice, John, Bob and herself. 
Later in the trip, John went out and bought groceries for $100, which was used only by Alice and Bob.

Now, the trip is over and everyone wants to get paid back what they are owed - 
print out the list of transactions that would settle everyone's debts.
*/

import java.util.*;
public class calculateDebts {
	public static class Transaction{
		double amount;
		String lender;
		String[] borrower;
				
		Transaction(double amount, String lender, String[] borrower){
			this.amount = amount;
			this.lender = lender;
			this.borrower = borrower;
		}
		
		public double getAmount(){
			return amount;
		}
		
		public String[] getBorrower(){
			return borrower;
		}
		
		public String getLender(){
			return lender;
		}
	}
	
	public void getTransactionDetails(Transaction t){
		String[] borrower = t.getBorrower();
		int len = borrower.length;
		double amount = t.getAmount();
		String lender = t.getLender();
		
		for(int i = 0; i < len; i++){
			double share = amount / len;
			if(borrower[i] != lender)
				System.out.println(borrower[i] + " needs to pay " + share + " to " + lender);
		} 
	}
	
	
	/**********************follow up: reduce transaction******************************/
	
	String[] person = {"Alice", "John", "Bob", "Sarah"};
	//HashMap<String, String> debts = new HashMap<>(); //borrower => lender
	HashMap<HashMap, Double> balances = new HashMap<>();  //(borrower => lender) => amount
	
	
	public void setBalances(double amount, String lender, String[] borrower) {
		for(int i = 0; i < borrower.length; i++) {
			double share = amount / borrower.length;
			HashMap<String, String> debts = new HashMap<>(); //borrower => lender
			debts.put(borrower[i], lender);
			if(balances.containsKey(debts)) {
				balances.put(debts, share + balances.get(debts));
			}
			else
				balances.put(debts, share);
			
		}
	}
	
	public void getBalances() {
		
		for(HashMap key : balances.keySet()) {
			System.out.println(key.toString() + balances.get(key));
		}
	}
	

	
	public static void main(String[] args){
		calculateDebts obj = new calculateDebts();
		
		//Transaction t1 = new Transaction(400, "Sarah", new String[] {"Alice", "John", "Bob", "Sarah"});
		//Transaction t2 = new Transaction(100, "Sarah", new String[] {"Alice", "Bob"});
		//obj.getTransactionDetails(t1); 
		//obj.getTransactionDetails(t2);
		
		obj.setBalances(400, "Sarah", new String[] {"Alice", "John", "Bob", "Sarah"});
		obj.setBalances(100, "John", new String[] {"Alice", "Bob"});
		obj.getBalances();
	}
}

