package atminterface.in.ineuron;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountHolder {
	
	private String firstName;  //The first name of the user
	
	private String lastName;   //The last name of the user
	
	private String uuid;  //uuid stand for univarsal unique id
	
	private byte pinHash[];  //The MD5 hash of the user's pin number
	
	private ArrayList<Account>accounts;  //List of the account for the user
	
	public AccountHolder(String firstName, String lastName,String pin, Namely theNamely) {
		
		this.firstName=firstName;
		this.lastName=lastName;
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");  //Give checkedException MessageDigest and message digest use for generate uuid
			
			this.pinHash=md.digest(pin.getBytes());
			
		}
		catch (NoSuchAlgorithmException e) {
	
			System.err.println("error,caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		this.uuid=theNamely.getNewUserUUID();
		
		this.accounts=new ArrayList<Account>();
		
		System.out.printf("New User %s, %s with UserID %s created,\n",firstName,lastName,this.uuid);
	}
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	
	public String getUUID() {
		
		return this.uuid;
	}

	public boolean validatePin(String aPin) {
		
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} 
		catch (NoSuchAlgorithmException e) {
			System.err.println("error,caught NoSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void printTransactionHistory() {
		System.out.printf("\n\n%s's transactionHistory\n", this.firstName);
		for(int i=0; i<this.accounts.size(); i++) {
			System.out.printf(" %d) %s\n",i+1, this.accounts.get(i).getTransactionHistoryLine());
		}
		System.out.println();
	}

	public int numAccounts() {
		return this.accounts.size();
	}

	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
	}

	public double getAcctBalance(int acctIdx) {
		return this.accounts.get(acctIdx).getBalance();
	}

	public String getAcctUUID(int acctIdx) {
		return this.accounts.get(acctIdx).getUUID(); //yha change 
	}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addBankTransaction(amount,memo);
		
	}

}
