//Dylan Johnson
//Program 3
//CSC322

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class eMart {

	//Main class that calles the start of the market----
	public static void main(String[] args) {
		
		new onlineStore().start();
		
		System.out.println("\n\nClosed");

	}
	//--------------------------------------------------
	

}


class onlineStore {
	//Global variables for the online store---------------------------------
	public static ArrayList<String> data = new ArrayList<>(); //array to hold all data inputed from text file
	public static ArrayList<String> temp = new ArrayList<>(); //temporary array to hold the split of each row item from the input file
	public static ArrayList<baseInventory> inventory = new ArrayList<>(); //array to hold each class object created 
	public static Scanner input = new Scanner(System.in); //basic scanner to get user input for the menu
	//----------------------------------------------------------------------
	
	public void start() {
		
		itemInventory();//pulls in all data from the designated input file

		printInventory();//prints out the available inventory based on what came in from input file

		System.out.println("Shutting Down..");
	}

	//Gets users menu choice and prints out accordingly
	private void printInventory() {
		int choice;
		do {
			choice = menu();
			if (choice != 5) {
				printHeader();
				printContents(choice);
				printFooter();
			}
		} while (choice != 5);
	}
	//-------------------------------------------------

	//Prints out menu display and returns the users choice---
	public static int menu() {
		int choice;
		System.out.println("Welcome to eMart");
		System.out.println("  1) Show all items");
		System.out.println("  2) Show only music CD");
		System.out.println("  3) Show only books");
		System.out.println("  4) Show only software");
		System.out.println("  5) Exit program");
		System.out.print("Choice: ");
		choice = input.nextInt();
		input.nextLine();
		System.out.println("\n\n");
		return choice;
	}
	//-------------------------------------------------------

	//Prints the table header specifically formatted for sample data--------------------------------------
	private static void printHeader() {
		System.out.printf("%75s\n", "-".repeat(75));
		System.out.printf("| %-30s | %-10s | %-10s | %-10s |\n", "Title", "Type", "Price", "Quantity");
		System.out.printf("%75s\n", "-".repeat(75));
	}
	//----------------------------------------------------------------------------------------------------
	
	//Prints out the "guts" of the table-------------------------------------------------------------------------------------------------------------------------------------------------
	private static void printContents(int choice) {
		ArrayList<String> printList = printList(choice);
		
		for (baseInventory inventoryItem: inventory) {//for every item from the input file
			if(printList.contains(inventoryItem.getType())) {//print it out if it is in the printList array
				System.out.printf("| %-30s | %10s | $%-10.2f | %-10.0f |\n", inventoryItem.getTitle(), inventoryItem.getType(), inventoryItem.getPrice(), inventoryItem.getQuantity());
			}
		}
		printList.clear();//clears the printList for the next call
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	//Prints out the footer of the table------------------
	private static void printFooter() {
		System.out.printf("%75s\n\n", "-".repeat(75));
	}
	//----------------------------------------------------

	//Assigns what to print out based on user input------------------------------------------------------
	private static ArrayList<String> printList(int choice) {
		ArrayList<String> printList = new ArrayList<>();
		
		if (choice == 1) {printList.add("music"); printList.add("software"); printList.add("book");}
			else if (choice == 2) {printList.add("music");}
			else if (choice == 3) {printList.add("book");}
			else if (choice == 4) {printList.add("software");}
		return printList;
	}
	//---------------------------------------------------------------------------------------------------

	//Pulls in data form input file and splits it up into the inventory array depending on it's type----------------------------------------------------
	public static void itemInventory() {
		ReadFile input = new ReadFile();
		
		input.readFile("data", data);
		
		//catches if the input file doesn't exist and stops the rest of the program from running
		if(data.get(0).equals("No Such File")) {
			
			System.out.println("Please enter a valid file name.");
			System.exit(0);
			
		}
		//--------------------------------------------------------------------------------------
		//If the input file does exist, it processes the information accordingly---------------------------------------------------------------------
		else {
			
			for(String inputItem: data) {//for every new line item --inputItem
				temp.clear();//clear out the temporary array
				for(String lineItem: inputItem.split("\\|")) {//for each individual line --lineItem-- split up the line item by the "|" 
															  //character and store each individual value in array temp
					temp.add(lineItem);//adds each split item to array, temp
				}
			
			
				if (temp.get(0).equals("music")) {//processes music information
					
					inventory.add(new baseInventory(temp.get(0), temp.get(1), Double.parseDouble(temp.get(8)), Integer.parseInt(temp.get(9))));
					
				}else if (temp.get(0).equals("software")) {//processes software information
					inventory.add(new baseInventory(temp.get(0), temp.get(1), Double.parseDouble(temp.get(3)), Integer.parseInt(temp.get(4))));
					
				}else if (temp.get(0).equals("book")) {//processes book information
					
					inventory.add(new baseInventory(temp.get(0), temp.get(1), Double.parseDouble(temp.get(6)), Integer.parseInt(temp.get(7))));
					
				}
			}
			
		}
		//--------------------------------------------------------------------------------------------------------------------------------------------
		
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------
	
}

//Class that stores all similar information of each different type of information---------
class baseInventory {
	String type;
	String title;
	double price;
	int quantity;
	
	public baseInventory(String type, String title, double price, int quantity) {
		this.type = type;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public double getPrice() {
		return price;
	}

	public double getQuantity() {
		return quantity;
	}
	
}
//----------------------------------------------------------------------------------------

//Fluff code that would be useful if more data is required which is why it's bare bones---------------------------------------------------------

//Processes the rest of music information if needed later------------------------------------------------
class music extends baseInventory {
	String artist;
	Date releaseDate;
	String label;
	String recordCompany;
	int albumLength;
	String genre;
	
	public music(String artist, Date releaseDate, String label, String recordCompany, 
			int albumLength, String genre, String type, String title, double price, int quantity) {
		super(type, title, price, quantity);
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.label = label;
		this.recordCompany = recordCompany;
		this.albumLength = albumLength;
		this.genre = genre;

	}
}
//---------------------------------------------------------------------------------------------------------

//Processes the rest of software information if needed later-----------------------------------------------
class software extends baseInventory {
	String version;
	
	public software(String version, String type, String title, double price, int quantity) {
		super(type, title, price, quantity);
		this.version = version;
	}
}
//---------------------------------------------------------------------------------------------------------

//Processes the rest of book information if needed later---------------------------------------------------
class book extends baseInventory {
	String author;
    String edition;
    String publisher;
    int pubYear;
    
    public book(String author, String edition, String publisher, int pubYear, String type, String title, double price, int quantity) {
    	super(type, title, price, quantity);
    	this.author = author;
    	this.edition = edition;
    	this.publisher = publisher;
    	this.pubYear = pubYear;

    }
}
//---------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------




