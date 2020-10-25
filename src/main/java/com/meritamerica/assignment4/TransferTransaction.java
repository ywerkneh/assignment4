/* Week 5 - Partner Pair Assignment #4
 *  October 25, 2020
 */

package com.meritamerica.assignment4;
import java.util.Date;

public class TransferTransaction extends Transaction {
	
//INSTANCE VARIABLES
	private double amount;
	private BankAccount targetAccount;
	private BankAccount sourceAccount;

//CONSTRUCTOR
	 public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
	 }
}