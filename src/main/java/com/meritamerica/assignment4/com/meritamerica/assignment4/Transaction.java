package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Transaction {


	com.meritamerica.assignment4.BankAccount account;
	com.meritamerica.assignment4.BankAccount targetAccount;
	double amount;
	Date date;

	public com.meritamerica.assignment4.BankAccount getSourceAccount() {
		return account;
	}

	public void setSourceAccount(com.meritamerica.assignment4.BankAccount account) {
		this.account = account;
	}

	public com.meritamerica.assignment4.BankAccount getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(com.meritamerica.assignment4.BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransactionDate(){
		return date;
	}

	public void setTransactionDate(Date date) {
		this.date = date;
	}
	/**
	 * A method that writes information that writes the date passed through into a string.
	 * Reformatted date to be simpler.
	 * @return
	 */
	public String writeToString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		StringBuilder toString = new StringBuilder();
		if(account == null) {
			toString.append(-1);
		}
		else {
			toString.append(account.getAccountNumber());
		}
		toString.append(",");
		toString.append(targetAccount.getAccountNumber());
		toString.append(",");
		toString.append(amount);
		toString.append(",");
		toString.append(dateFormat.format(date));
		return toString.toString();
	}
	/**
	 *Read from String , gets data thats being passed through, and returns Transaction with the information
	 * @param transactionDataString
	 * @return
	 * @throws ParseException
	 */
	public static Transaction readFromString(String transactionDataString) throws ParseException {
		String[] temp = transactionDataString.split(",");

		com.meritamerica.assignment4.BankAccount source;
		if(temp[0].equals("-1")) {
			source = null;
		}
		else {
			source = com.meritamerica.assignment4.MeritBank.getBankAccount(Long.valueOf(temp[0]));
		}

		com.meritamerica.assignment4.BankAccount target = com.meritamerica.assignment4.MeritBank.getBankAccount(Long.valueOf(temp[1]));

		double amount = Double.valueOf(temp[2]);
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(temp[3]);

		if(Integer.valueOf(temp[0]) == -1) {
			if(Double.valueOf(temp[2]) < 0) {
				com.meritamerica.assignment4.WithdrawTransaction transaction = new com.meritamerica.assignment4.WithdrawTransaction(target, amount);
				transaction.setTransactionDate(date);
				System.out.println(transaction.writeToString());
				return transaction;
			}
			else {
				com.meritamerica.assignment4.DepositTransaction transaction = new com.meritamerica.assignment4.DepositTransaction(target, amount);
				transaction.setTransactionDate(date);
				System.out.println(transaction.writeToString());
				return transaction;
			}
		}
		else {
			com.meritamerica.assignment4.TransferTransaction transaction = new com.meritamerica.assignment4.TransferTransaction(source, target, amount);
			System.out.println(transaction.writeToString());
			return transaction;
		}
	}
}