package com.meritamerica.assignment4;

import java.text.SimpleDateFormat;

public abstract class Transaction {

	
	//Instance Variables
	BankAccount sourceAccountNum;
	BankAccount targetAccountNum;
	java.util.Date date;
	boolean isProcessed = false;
	String rejectionReason;
	static FraudQueue fraud;
	private double amount;
	
	
	
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
	public String writeToString() {
		System.out.println ("TRANSACTION- source, target,amount,date");
	return "TargetAccount: " + this.sourceAccountNum + this.targetAccountNum + amount + this.amount + date + this.date;
	}
	
	public static Transaction readFromString(String transactionDataString) {
		
		
		Date d;
		
		try {
		    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			String Array1[] =transactionDataString.split(",");
			double amount = Double.parseDouble(Array1[0]);
			long sourceAccountNum = Integer.parseInt(Array1[1]);
			long targetAccountNum = Integer.parseInt(Array1[2]);
			Date d = dateFormatter.parse(Array1[3]);
		
// targetAccountNum is -1 for both Withdraw & Deposit transaction
// 		
			if (targetAccountNUm == -1) {
				if (amount < 0) {
					WithdrawTransaction newTransaction = new WithdrawTransaction(amount, sourceAccountNum,targetAccountNum, Date d);
				
					return newTransaction;
				}
				
				DepositTransaction newTransaction = new DepositTransaction (amount, sourceAccountNum,targetAccountNum,Date d);
				
				return newTransaction;
			
				}
				TransferTransaction newTransaction = new TransferTransaction (amount, sourceAccountNum, targetAccountNum,Date d);
		
				return newTransaction;
		
		
		} catch (Exception e){
			return null;
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

