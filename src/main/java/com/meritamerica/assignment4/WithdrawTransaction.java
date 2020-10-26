
/* Week 5 - Partner Pair Assignment #4
 *  October 25, 2020
 */
	
package com.meritamerica.assignment4;

import java.util.Date;
	
	public class WithdrawTransaction extends Transaction{
	
	//INSTANCE VARIABLE
	private double amount;
	private BankAccount targetAccount;
		
	
	//CONSTRUCTOR
	WithdrawTransaction(BankAccount targetAccount, double amount){
		this.targetAccountNum = targetAccount;
		this.amount = amount;
		this.date = new Date();
		
	}
}
