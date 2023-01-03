package atminterface.in.ineuron;

import java.util.ArrayList;

public class Account {
	
	private String name;  //The name of the account
		
	private String uuid;  //the account id no
	
	private AccountHolder holder;  //AccountHolder object,that owns this account
	
	private ArrayList<BankTransaction> transactions; //The list of Banktransaction for this account
	
	public Account(String name, AccountHolder holder, Namely theNamely) {
		this.name=name;
		this.holder=holder;
		this.uuid=theNamely.getNewAccountUUID();
		
		this.transactions=new ArrayList<BankTransaction>();
		
	}
	
	public String getUUID() {
		return this.uuid;
	}

	public String getTransactionHistoryLine() {
		double balance=this.getBalance();
		if(balance>=0) {
			return String.format("%s : Rupees%.02f : %s", this.uuid, balance, this.name);
		}
		else {
			return String.format("%s : Rupees(%.02f) : %s", this.uuid, balance, this.name);
		}
	}

	public double getBalance() {
		double balance=0;
		for(BankTransaction t:this.transactions) {
			balance=balance+t.getAmount();
		}
		return balance;
	}

	public void printTransHistory() {
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for(int i=this.transactions.size()-1; i>=0; i--) {
			System.out.println(this.transactions.get(i).getTransactionHistoryLine());
		}
		System.out.println();
	}

	public void addBankTransaction(double amount, String memo) {
			BankTransaction newBankTrans=new BankTransaction(amount,memo,this);
			this.transactions.add(newBankTrans);
		
	}
}