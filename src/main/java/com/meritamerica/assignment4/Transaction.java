/* Week 5 - Partner Pair Assignment #4
 *  October 25, 2020
 */
 

package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public abstract class Transaction {

	
	//Instance Variables
	private BankAccount sourceAccountNum;
	private BankAccount targetAccountNum;
	double amount;
	private java.util.Date date;
	boolean isProcessed = false;
	String rejectionReason;
	static FraudQueue fraud;
	
	
	
	
	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	//Source Account
	public BankAccount getSourceAccount() {
		return sourceAccountNum;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccountNum = sourceAccount;
	}
	
	//Target Account
	public BankAccount getTargetAccount() {
		return targetAccountNum;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccountNum = targetAccount;
		
	}
	
	//Amount
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	//Date
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	//String writeToString
	public static Transaction readFromString(String transactionDataString) {

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

		try {

			String chd[] = transactionDataString.split(",");
//			
			long targetAccountNum = Long.parseLong(chd[0]);
			long sourceAccountNum = Long.parseLong(chd[1]);
			double amount = Double.parseDouble(chd[2]);
			Date date = dateFormatter.parse(chd[3]);
			// targetAccountNum is -1 for both withdraw & deposit - use this parameter to create either 
			// then accountNum is positive for DEPOSIT & negative for WITHDRAW			

			if (targetAccountNum == -1) {
				if (amount < 0) {
					WithdrawTransaction newTransaction = new WithdrawTransaction(MeritBank.getBankAccount(sourceAccountNum) , amount);
					return newTransaction;
				}
				DepositTransaction newTransaction = new DepositTransaction(MeritBank.getBankAccount(sourceAccountNum), amount);
				return newTransaction;
			}
			// Transfer - transaction class non -1 targetAccountNum - use parameter to create class
			TransferTransaction newTransaction = new TransferTransaction(MeritBank.getBankAccount(sourceAccountNum), 
					MeritBank.getBankAccount(targetAccountNum), amount);
			return newTransaction;
		}
		catch (ParseException e) {
			return null;
		}
	}

	
		//EXCEPTIONS THROWN
		public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException,ExceedsFraudSuspicionLimitException;
	
		
		// PROCESSED BY FRAUD TEAM
		
		public boolean isProcessedByFraudTeam() {
			return isProcessed;
		}
		
		public void setProcessedByFraudTeam( boolean isProcessed) {
			this.isProcessed = isProcessed;
		}
		public String getRejectionReason() {
			return rejectionReason;
			
		}
		public void setRejectionReason(String rejectReason) {
			this.rejectionReason = rejectionReason;
		}
	}

