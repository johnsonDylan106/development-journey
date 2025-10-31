/*
 * Author: Dylan Johnson
 * Date: 10/12/22
 * Class: CSS222
 * Assignment: Lab 4
 * Description: Make Shift ATM
*/

import java.util.Scanner;

public class JohnsonLab4 {
	
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		//Creates the 10 accounts per instructions-----------
		Account[] accounts = new Account[10];
		for (int i = 0; i < 10; i++) {
			accounts[i] = new Account(i, 100);
		}
		//---------------------------------------------------
		int choice, accountNumber;//easy access to variables
		
		//This does not close and keeps the "ATM" open---------------------------------------------------------------------------------------------
		do {
			String login = login();
			//Added this part to utilize the interest rate part of the assignment
			//Are you able to access it??
			//It was good to take my mind of things and keep working so feel free to ignore to as this is not part of the assignment by any means.
			if (login.equals(admin)) {
				admin(accounts);
			}else if (login.equals("exit")) {
				break;
			}
			//---------------------------------------------------------------------------------------
			//Added this because of the "admin" section above...--------
			while(true) {
				try {
					accountNumber = Integer.parseInt(login);
					break;
				}
				catch(NumberFormatException nfe) {
					login = login();
				}
			}
			//----------------------------------------------------------
			if (accountNumber < 0 || accountNumber > 9) {//Checks if account number selected is available
				incorrectID(input, accountNumber);//requests a new account number if not
			}
			// allows the user to do whatever they would like until they "logout"----
			do {
				choice = menu(input);
				
				menuChoices(choice, accounts, accountNumber);
				
			}while(choice != 5);
			//------------------------------------------------------------------------
			
		

		}while(true);
		//-------------------------------------------------------------------------------------------------------------------------------------------------
	}
	//again, this was not needed but was good to keep going and have my mind off things....--------
	private static void admin(Account[] accounts) {
		double rate;
		System.out.println("Hello, Admin.");
		System.out.println("Enter account to modify interest rate: ");
		int mod = input.nextInt();
		if (accounts[mod].getMonthlyInterestRate()==0) {
			System.out.print("Enter interest rate: ");
			rate = input.nextDouble();
		}else {
			System.out.print("Enter updated interest rate: ");
			rate = input.nextDouble();
		}
		accounts[mod].setAnnualInterestRate(rate);
		System.out.println(accounts[mod].getMonthlyInterestRate());
		System.out.println("You are logged out as admin");
	}
	//----------------------------------------------------------------------------------------------
	//Asks the user to enter their account number------
	private static String login() {
		System.out.print("Enter the account id: ");
		String login = input.nextLine();
		return login;
	}
	//-------------------------------------------------
	//Another attempt to enter account number if failed the first time------
	private static void incorrectID(Scanner input, int accountNumber) {
		System.out.println("Invalid ID, try again.");
		while(accountNumber < 0 || accountNumber > 9) {
			System.out.print("Enter your ID: ");
			accountNumber = input.nextInt();
		}
	}
	//----------------------------------------------------------------------
	//Provides the menu choices for the ATM ----------------------------------------------------------------
	private static void menuChoices(int choice, Account[] accounts, int id) {
		System.out.println("\n");
		switch(choice) {
			case 1:
				System.out.printf("The balance is $%.2f%n", accounts[id].getBalance());
				break;
			case 2:
				withdrawn(accounts, id);
				break;
			case 3:
				System.out.print("Please enter the amount to deposit: ");
				accounts[id].deposit(input.nextDouble());
				System.out.printf("Balance after deposit is: $%.2f%n", accounts[id].getBalance());
				break;
			case 4:
				System.out.println("Account was created on " + accounts[id].getDateCreated());
				System.out.printf("Account interest rate is: %.2f", accounts[id].getMonthlyInterestRate());
				System.out.println("%");
				System.out.printf("Account Balance is: $%.2f%n", accounts[id].getBalance());
				break;
			case 5:
				System.out.println("Logged out");
				break;
			default:
				System.out.println("Wrong choice, try again");
				break;
		}
	}
	//-------------------------------------------------------------------------------------------------------
	//Extra code for when a user withdraws $$, could be built into the Account class--------------------
	private static void withdrawn(Account[] accounts, int id) {
		System.out.print("Please enter the amount to withdraw: ");
		double withdrawl = input.nextDouble();
		if (withdrawl > accounts[id].getBalance()) {
			System.out.println("Your account does not allow overdraft.");
			System.out.printf("Amount withdrawn is: $%.2f%n", accounts[id].getBalance());
			accounts[id].withdraw(accounts[id].getBalance());
		}else {
			accounts[id].withdraw(withdrawl);
			System.out.printf("Amount withdrawn is: $%.2f%n", withdrawl);
		}
	}
	//--------------------------------------------------------------------------------------------------
	//Prints the ATM menu and asks user for their choice---------------
	private static int menu(Scanner input) {
		int choice;
		System.out.println("\n\nMain Menu\n\n");
		System.out.println("1. Check Balance");
		System.out.println("2. Withdraw");
		System.out.println("3. Deposit");
		System.out.println("4. Account Information");
		System.out.println("5. Exit (Choose a different account)");
		System.out.print("Please make a selection: ");
		choice = input.nextInt();
		input.nextLine();
		return choice;
	}
	//----------------------------------------------------------------
	private static String admin = "*********";//hmm?
}
//account class built to specifications of the assignment
class Account {
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    private java.util.Date dateCreated;

    public Account() {
        dateCreated = new java.util.Date();
    }

    public Account(int id, double balance) {
        this();
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return this.id;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public String getDateCreated() {
        return this.dateCreated.toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {//part of admin
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100) / 12 ;
    }

    public double getMonthlyInterest() {//extra
        return balance * getMonthlyInterestRate();
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
	


