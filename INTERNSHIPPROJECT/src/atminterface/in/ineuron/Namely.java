package atminterface.in.ineuron;

import java.util.ArrayList;
import java.util.Random;

public class Namely {
	
	private String name;
	
	private ArrayList<AccountHolder> user;
	
	private ArrayList<Account> accounts;
	
	public Namely(String name) {
		this.name=name;
		this.user=new ArrayList<AccountHolder>();
		this.accounts=new ArrayList<Account>();
	}
	public String getNewUserUUID() {
		
		String uuid;
		Random rng=new Random();
		int len=6;
		boolean nonUnique;
		
		do {
			uuid="";
			for(int i=0;i<len;i++) {
				uuid=uuid+((Integer)rng.nextInt(10)).toString();
			}
			
			nonUnique=false;
			for(AccountHolder a:this.user) 
			{
				if(uuid.compareTo(a.getUUID())==0) {
					nonUnique=true;
					break;
				}
			}
		}
		while(nonUnique);
		return uuid;
	
	}
	public String getNewAccountUUID() {
		
		String uuid;
		Random rng=new Random();
		int len=10;
		boolean nonUnique;
		
		do {
			uuid="";
			for(int i=0;i<len;i++) {
				uuid=uuid+((Integer)rng.nextInt(10)).toString();
			}
			
			nonUnique=false;
			for(Account a:this.accounts) 
			{
				if(uuid.compareTo(a.getUUID())==0) {
					nonUnique=true;
					break;
				}
			}
		}
		while(nonUnique);
		return uuid;
		
	}
	
	public AccountHolder addAccountHolder(String firstName, String lastName, String pin) {
		AccountHolder newAccountHolder=new AccountHolder(firstName, lastName, pin,this);
		this.user.add(newAccountHolder);
		
		Account newAccount=new Account("Savings",newAccountHolder,this);
		newAccountHolder.addAccount(newAccount);
		this.accounts.add(newAccount);
		
		return newAccountHolder;
	}
	
	public AccountHolder AccountHolderLogin(String userId, String pin) {
		for(AccountHolder u:this.user) {
			if(u.getUUID().compareTo(userId)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
		
	}

}
