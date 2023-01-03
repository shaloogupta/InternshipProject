package atminterface.in.ineuron;

import java.util.Scanner;

public class BankAndParticularATMofTheBank {
	
	public static void main(String[]args) {
		
		Scanner sc=new Scanner(System.in);
		
		Namely theNamely=new Namely("Bank Of Baroda");
		
		AccountHolder aAccountHolder =theNamely.addAccountHolder("Shaloo", "Gupta", "1234");
		
		Account newAccount=new Account("Fixed deposit account",aAccountHolder,theNamely);
		aAccountHolder.addAccount(newAccount);
		theNamely.addAccount(newAccount);
		
		AccountHolder curAccountHolder;
		while(true) {
			curAccountHolder=BankAndParticularATMofTheBank.mainMenuPrompt(theNamely,sc);
			
			BankAndParticularATMofTheBank.printAccountHolderMenu(curAccountHolder,sc);
			
		}
		
	}
	
	public static AccountHolder mainMenuPrompt(Namely theNamely, Scanner sc) {
		
		String userId;
			String pin;
			AccountHolder authUser;
			
			do {
				System.out.printf("\n\nWelcome to %s\n\n",theNamely.getName());
				System.out.print("Enter User ID: ");
				userId=sc.nextLine();
				System.out.print("Enter pin: ");
				pin=sc.nextLine();
				
				authUser=theNamely.AccountHolderLogin(userId,pin);
				if(authUser==null) {
					System.out.println("Invalid user ID or Pin combination. " + "Please try again. ");
				}
			}
			while(authUser==null);
	
			return authUser;
	}
	
	public static void printAccountHolderMenu(AccountHolder theAccountHolder, Scanner sc) {
		
		theAccountHolder.printTransactionHistory();
		
		int choice;
		
		do {
			System.out.printf("Welcome %s, Which operation you want to perform ?\n" ,theAccountHolder.getFirstName());
			
			System.out.println(" 1:- Show account transaction history ");
			System.out.println(" 2:- Withdraw ");
			System.out.println(" 3:- Deposit ");
			System.out.println(" 4:- Transfer ");
			System.out.println(" 5:- Quit ");
			System.out.println();
			System.out.print("Enter Choice: ");
			choice=sc.nextInt();
			
			if(choice<1 || choice>5) {
				System.out.println("Invalid choice. Please choose 1 To 5");
			}
		}
		
		while(choice<1 || choice>5);
		
		switch(choice) {
		
		case 1:
		{
			BankAndParticularATMofTheBank.showTranHistory(theAccountHolder,sc);
			break;
		}
		case 2:
		{
			BankAndParticularATMofTheBank.withdrawlfunds(theAccountHolder,sc);
			break;
		}
		case 3:
		{
			BankAndParticularATMofTheBank.depositFunds(theAccountHolder,sc);
			break;
		}
		case 4:
		{
			BankAndParticularATMofTheBank.transferFunds(theAccountHolder,sc);
			break;
		}
		case 5:
		{
			System.out.println("ThankYou For Using The ATM ");
			sc.nextLine();
			System.exit(1);
		}
			
		}
		if(choice !=5) {
			BankAndParticularATMofTheBank.printAccountHolderMenu(theAccountHolder, sc);
						
		}
	}

	private static void depositFunds(AccountHolder theAccountHolder, Scanner sc) {
		
		int toAcct;
		double amount;
		double acctBal;
		String memo;
		
		do {
			System.out.printf("Enter the number (1 to %d) of the account\n" + "to deposit in: ",theAccountHolder.numAccounts());
			toAcct=sc.nextInt()-1;
			if(toAcct<0 || toAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account. Please Try again.");
			}
		}
		while(toAcct<0 || toAcct>=theAccountHolder.numAccounts());
		acctBal=theAccountHolder.getAcctBalance(toAcct);
		
		do {
			System.out.printf("Enter the Amount to deposit(max Rupees%.02f): Rupees", acctBal);
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Entered amount must not be greater than zero.");
			}
		}
		while(amount<0);
		
		sc.nextLine();
		System.out.print("Enter a memo: ");
		memo=sc.nextLine();
		
		theAccountHolder.addAcctTransaction(toAcct, amount, memo);
		
	}

	private static void withdrawlfunds(AccountHolder theAccountHolder, Scanner sc) {
	
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		do {
			System.out.printf("Enter the number (1 to %d) of the account\n" + "to withdraw from: ",theAccountHolder.numAccounts());
			fromAcct=sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account. Please Try again.");
			}
		}
		while(fromAcct<0 || fromAcct>=theAccountHolder.numAccounts());
		acctBal=theAccountHolder.getAcctBalance(fromAcct);
		
		do {
			System.out.printf("Enter the Amount to withdraw (max Rupees%.02f): Rupees", acctBal);
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Entered amount must be greater than zero.");
			}
			else if(amount>acctBal) {
				System.out.printf("Entered amount must not be greater than \n"+"balance of Rupees%.02f\n",acctBal);
			}
		}
		while(amount<0 || amount>acctBal);
		
		sc.nextLine();
		System.out.println("Enter a memo: ");
		memo=sc.nextLine();
		
		theAccountHolder.addAcctTransaction(fromAcct, -1*amount, memo);
		
	}

	private static void transferFunds(AccountHolder theAccountHolder, Scanner sc) {
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		do {
			System.out.printf("Enter the number (1 to %d) of the account\n" + "to transfer from: ",theAccountHolder.numAccounts());
			fromAcct=sc.nextInt()-1;
			if(fromAcct<0 || fromAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account. Please Try again.");
			}
		}
		while(fromAcct<0 || fromAcct>=theAccountHolder.numAccounts());
		acctBal=theAccountHolder.getAcctBalance(fromAcct);
		
		do {
			System.out.printf("Enter the number (1 to %d) of the account\n" + "to transfer to: ",theAccountHolder.numAccounts());
			toAcct=sc.nextInt()-1;
			if(toAcct<0 || toAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account. Please Try again.");
			}
		}
		while(toAcct<0 || toAcct>=theAccountHolder.numAccounts());
		
		do {
			System.out.printf("Enter the Amount to transfer(max Rupees%.02f): Rupees", acctBal);
			amount=sc.nextDouble();
			if(amount<0) {
				System.out.println("Entered amount must be greater than zero.");
			}
			else if(amount>acctBal) {
				System.out.printf("Entered amount must not be greater than \n"+"balance of Rupees%.02f.\n",acctBal);
			}
		}
		while(amount<0 || amount>acctBal);
		
		theAccountHolder.addAcctTransaction(fromAcct, -1*amount,String.format("Transfer to account %s", 
				theAccountHolder.getAcctUUID(toAcct)));
		
		theAccountHolder.addAcctTransaction(toAcct, amount,String.format("Transfer to account %s", 
				theAccountHolder.getAcctUUID(fromAcct)));
		
	}

	public static void showTranHistory(AccountHolder theAccountHolder, Scanner sc) {
		int theAcct;
		do {
			System.out.printf("Enter the Number(1 to %d) of the account" + " Whose transactionHistory you want to see: ",
					theAccountHolder.numAccounts());
			theAcct=sc.nextInt()-1;
			if(theAcct<0 || theAcct>=theAccountHolder.numAccounts()) {
				System.out.println("Invalid account. Please Try again.");
			}		
		}
		while(theAcct<0 || theAcct>=theAccountHolder.numAccounts());
		theAccountHolder.printAcctTransHistory(theAcct);	
	}
}