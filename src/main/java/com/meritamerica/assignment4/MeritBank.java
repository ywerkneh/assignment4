package com.meritamerica.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MeritBank {
	static AccountHolder myAccountHolder[] = new AccountHolder[0];
	static CDOffering myCDOffering[] = new CDOffering[0];
	static CDAccount myCDAccount[] = new CDAccount[0];
	private static long nextAccount = 0;
	private static int counter = 0;
	static FraudQueue fraudQueue = new FraudQueue();
	

	public static void addAccountHolder(AccountHolder accountHolder) {

		if (counter == myAccountHolder.length) {

			AccountHolder[] myAccountHolder1 = new AccountHolder[counter + 1];

			for (int i = 0; i < counter; i++) {
				myAccountHolder1[i] = myAccountHolder[i];
			}

			myAccountHolder = myAccountHolder1;
		}
		myAccountHolder[counter++] = accountHolder;

	}

	public static AccountHolder[] getAccountHolders() {
		return myAccountHolder;

	}

	public static CDOffering[] getCDOfferings() {
		return myCDOffering;

	}

	public static CDOffering getBestCDOffering(double depositAmount) {
		double best = 00;
		CDOffering CDO = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > best) {
				CDO = myCDOffering[i];
				best = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDO;
	}

	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		double secondbest = 00;
		CDOffering CDO = null;
		CDOffering CDB = null;
		if (myCDOffering == null) {
			return null;
		}
		for (int i = 0; i < myCDOffering.length; i++) {
			if (futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm()) > secondbest) {
				CDB = CDO;
				CDO = myCDOffering[i];
				secondbest = futureValue(depositAmount, CDO.getInterestRate(), CDO.getTerm());
			}
		}
		return CDB;

	}

	public static void clearCDOfferings() {
		myCDOffering = null;

	}

	public static void setCDOfferings(CDOffering[] offerings) {
		myCDOffering = offerings;
	}

	public static long getNextAccountNumber() {
		return nextAccount;
	}

	public static double totalBalances() {
		double total = 0;
		for (int i = 0; i < myAccountHolder.length; i++) {
			total += myAccountHolder[i].getCombinedBalance();
		}
		return total;

	}

	public static double futureValue(double presentValue, double interestRate, int term) {
		double value = 0.00;
		double powered = Math.pow((1 + interestRate), term);
		value = presentValue * powered;
		return value;
	}

	static boolean readFromFile(String filename) {
		// CDOffering[] cdOffering = null; // CANT BE NULL
		AccountHolder accountHolders;
		try {

			// String accountHolder = br.readLine();

			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			nextAccount = Long.parseLong(br.readLine());

			int numberOfCDOfferings = Integer.parseInt(br.readLine());

			myCDOffering = new CDOffering[numberOfCDOfferings];
			for (int i = 0; i < numberOfCDOfferings; i++) {
				myCDOffering[i] = CDOffering.readFromString(br.readLine());
			}

			for (CDOffering o : myCDOffering) {
				System.out.println(o.getInterestRate());
			}

			int numberOfAccountHolders = Integer.parseInt(br.readLine());
			System.out.println("NUMBER OF ACCOUNT HOLDERS " + numberOfAccountHolders);

			for (int i = 0; i < numberOfAccountHolders; i++) {

				String x = br.readLine();
				addAccountHolder(accountHolders = AccountHolder.readFromString(x));
				System.out.println("String: " + x);

				int numberOfCheckingAccount = Integer.parseInt(br.readLine());

				for (int j = 0; j < numberOfCheckingAccount; j++) {
					accountHolders.addCheckingAccount(CheckingAccount.readFromString(br.readLine()));
					System.out.println("CHECKING BALANCE HERE:" + accountHolders.getCheckingBalance());
					//ASSIGNMENT 4 - ADDING CHECKING TRANSACTIONS - START
					int numberOfCheckingAccountTransaction = Integer.parseInt(br.readLine());
					for (int  k= 0; k < numberOfCheckingAccountTransaction; k++) {
						accountHolders.getCheckingAccounts()[j].addTransaction(Transaction.readFromString(br.readLine()));
						
					}
					//ASSIGNMENT 4 - ADDING CHECKING TRANSACTIONS - END
				}
				

				int numberOfSavingsAccounts = Integer.parseInt(br.readLine());
				for (int k = 0; k < numberOfSavingsAccounts; k++) {
					accountHolders.addSavingsAccount(SavingsAccount.readFromString(br.readLine()));
					//ASSIGNMENT 4 - ADDING SAVINGS TRANSACTIONS - START
					int numberOfSavingsTransactions = Integer.parseInt(br.readLine());
					for (int j = 0; j < numberOfSavingsTransactions; j++) {
						accountHolders.getSavingsAccounts()[k].addTransaction(Transaction.readFromString(br.readLine()));
					}
					//ASSIGNMENT 4 - ADDING SAVINGS TRANSACTIONS - END
				}
	
				int numberOfCDAccount = Integer.parseInt(br.readLine());
				for (int p = 0; p < numberOfCDAccount; p++) {
					accountHolders.addCDAccount(CDAccount.readFromString(br.readLine()));
					//ASSIGNMENT 4 - ADDING CD TRANSACTIONS - START
					int numberOfCDTransactions = Integer.parseInt(br.readLine());
					for (int j = 0; j < numberOfCDTransactions; j++) {
						accountHolders.getCDAccounts()[p].addTransaction(Transaction.readFromString(br.readLine()));
					}
					//ASSIGNMENT 4 - ADDING CD TRANSACTIONS - END
				}
			}
			br.close();
			return true;
		}
		catch (Exception e) {
			System.out.println("Oops Sorry Not Here");
			return false;
		}
	}
	

	static boolean writeToFile(String filename) {
		try {

			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(String.valueOf(nextAccount));
			bw.newLine();
			bw.write(String.valueOf(myCDOffering.length));
			bw.newLine();
			for (int i = 0; i < myCDOffering.length; i++) {
				bw.write(myCDOffering[i].toString());
				bw.newLine();
			}
			bw.write(String.valueOf(myAccountHolder.length));
			bw.newLine();
			for (int i = 0; i < myAccountHolder.length; i++) {
				bw.write(myAccountHolder[i].writeToString());
				bw.newLine();
				bw.write(myAccountHolder[i].getNumberOfCheckingAccounts());
				for (int j = 0; j < myAccountHolder[i].getNumberOfCheckingAccounts(); j++) {
					bw.write(String.valueOf(myAccountHolder[i].getCheckingAccounts()[j].toString()));
				}
				for (int k = 0; k < myAccountHolder[i].getNumberOfSavingsAccounts(); k++) {
					bw.write(String.valueOf(myAccountHolder[i].getSavingsAccounts()[k].toString()));
				}
				for (int g = 0; g < myAccountHolder[i].getNumberOfCDAccounts(); g++) {
					bw.write(String.valueOf(myAccountHolder[i].getCDAccounts()[g].writeToString()));
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println("No you big dum");
			return false;
		}
	}

	static AccountHolder[] sortAccountHolders() {
		Arrays.sort(myAccountHolder);
		
		for(AccountHolder a: myAccountHolder) {
			System.out.println("BALANCE HERE:" + a.getCombinedBalance());
		}
		
		System.out.println("INDEX AMOUNT: " + myAccountHolder.length);
		return myAccountHolder;
		
	}

	static void setNextAccountNumber(long nextAccountNumber) {

	}

	
	
	//NEW ASSIGNMENT 4 METHODS 
	
		
	/* Existing FuturValue methods that used to call Math.pow() should now call this method*/
public static double recursiveFutureValue(double amount, int years, double interestRate) {

	if(years > 0) {
		double newAmount = amount + (amount * interestRate);
		return recursiveFutureValue(newAmount, years - 1, interestRate);
	}
	return amount;
}

	
		

public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException{

	BankAccount source = transaction.getSourceAccount();
	BankAccount target = transaction.getTargetAccount();
	
	if(source == null) {
		if(transaction instanceof WithdrawTransaction) {
			if(transaction.getAmount() < 0) {
				throw new NegativeAmountException("Can not withdraw a negative amount");
			}
			if(transaction.getAmount() + target.getBalance() < 0) {
				throw new ExceedsAvailableBalanceException("Insufficient Balance");
			}
			if(transaction.getAmount() < -1000) {
				fraudQueue.addTransaction(transaction);
				throw new ExceedsFraudSuspicionLimitException("Transaction exceeds $1000.00 and must be reviewed prior to processing");
			}

			return true;
		}
		if(transaction.getAmount() < 0) {
			throw new NegativeAmountException("Can not deposit a negative amount");
		}
		if(transaction.getAmount() > 1000) {
			fraudQueue.addTransaction(transaction);
			throw new ExceedsFraudSuspicionLimitException("Transaction exceeds $1000.00 and must be reviewed prior to processing");
		}
		return true;
	}
	if(source.getBalance() < transaction.getAmount()) {
		throw new ExceedsAvailableBalanceException("Insufficient Balance");
	}
	if(transaction.getAmount() < 0) {
		throw new NegativeAmountException("Can not transfer a negative amount");
	}
	if(transaction.getAmount() > 1000) {
		fraudQueue.addTransaction(transaction);
		throw new ExceedsFraudSuspicionLimitException("Transaction exceeds $1000.00 and must be reviewed prior to processing");
	}
	else{
		source.withdraw(transaction.amount);
		target.deposit(transaction.amount);

	}
	return true;
		
}



/*If transaction does not violate any constraints, deposit/withdraw values from the relevant BankAccounts and add the transaction to the relevant BankAccounts
If the transaction violates any of the basic constraints (negative amount, exceeds available balance) the relevant exception should be thrown and the processing should terminate
If the transaction violates the $1,000 suspicion limit, it should simply be added to the FraudQueue for future processing */


public static FraudQueue getFraudQueue() {
	return fraudQueue;
}
	
	public static BankAccount getBankAccount(long accountId) {
		
		BankAccount matchedBAccount = null;
		for(AccountHolder ah : getAccountHolders()) {
			for(CDAccount cdA : ah.getCDAccounts()) {
				if(cdA.getAccountNumber() == accountId) {
					matchedBAccount = cdA;
				}
			}
			for(CheckingAccount ca : ah.getCheckingAccounts()) {
				if(ca.getAccountNumber() == accountId) {
					matchedBAccount = ca;
				}
			}
			for(SavingsAccount sa : ah.getSavingsAccounts()) {
				if(sa.getAccountNumber() == accountId) {
					matchedBAccount = sa;
				}
			}
		}
		return matchedBAccount;

	}
		
	}
	

