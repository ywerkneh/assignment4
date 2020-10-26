
/* Week 5 - Partner Pair Assignment #4
 *  October 25, 2020
 */

package com.meritamerica.assignment4;
import java.util.Date;

public class DepositTransaction extends Transaction {
	
	//INSTANCE VARIABLES
	private double amount;
	private BankAccount targetAccount;
	
	
	//CONSTRUCTORS
	public DepositTransaction (BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.date = new Date();
		
	}
}

	/*@Override
	public void process()
  throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
		
		if (amount < 0) {
			
			throw new NegativeAmountException("WARNING! Can not deposit a negative amount");
		}
	
		
		else if (targetAccount.getBalance() > 250000) {
			
			throw new ExceedsAvailableBalanceException("WARNING! Your deposit has exceeded the acceptable limit");
		}
		
		
		else if (amount > 1000) {
			
			throw new ExceedsFraudSuspicionLimitException("WARNING! We are sorry this transaction can not completed");
			
		}
		
		else {
			
			System.out.println("DEPOSIT AMOUNT:");
			
			targetAccount.deposit(amount);
		}
	}
}

	 */