
/* Week 5 - Partner Pair Assignment #4
 *  October 25, 2020
 */

package com.meritamerica.assignment4;
import java.util.ArrayList;
import java.util.List;


public class FraudQueue {
	
	static List<Transaction> transactions = new ArrayList<Transaction> ();

	 FraudQueue() {
		
	}
		
	//ADD TRANSACTIONS
	public void addTransaction(Transaction transaction) {
		transactions.add(transaction);

		
	}
	//LIST OF TRANSACTIONS
	public List <Transaction> getTransactions() {
		
		return transactions;
	}
	
}