package atminterface.in.ineuron;

import java.util.Date;

public class BankTransaction {
	
	private double amount;  //Amount for transaction
	
	private Date timestamp;  //Time and date of the Last Transaction
	
	private String memo;  
	
	private Account inAccount;  //Account in which Transaction was performed
	
	public BankTransaction(double amount, Account inAccount) {
		this.amount=amount;
		this.inAccount=inAccount;
		this.timestamp=new Date();
		this.memo="";
	}
	
	public BankTransaction(double amount, String memo, Account inAccount) {
		this(amount, inAccount);
		this.memo=memo;
	}

	public double getAmount() {
		return this.amount;
	}

	public String getTransactionHistoryLine() {
		if(this.amount>=0) {
			return String.format("%s : Rupees%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
		}
		else {
			return String.format("%s : Rupees(%.02f) : %s", this.timestamp.toString(), -this.amount,this.memo);
		}
	}
}
