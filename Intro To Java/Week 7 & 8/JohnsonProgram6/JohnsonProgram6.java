/*
 * Author: Dylan Johnson
 * Date: 10/20/22
 * Class: CSS222
 * Assignment: Program 6
 * Description: Pets Copier and exceptions
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JohnsonProgram6 {//Was this supposed to be called PetDatabase? I would not have been able to call the file the required name if so?
							  //Or was it supposed to be in a different class and only have the main element in this section? Wasn't sure so I went with it this way.
							  //I can modify to whatever the instructions are needed to be but ran out of time at this point to make any changes.
	public static Scanner input = new Scanner(System.in);//global scanner access
	public static ArrayList<Pet> Pets = new ArrayList<>();//global array access
	public static int numOfPets;//global access to number of pets
	public static int CAPACITY = 15;
	
	public static void main(String[] args) {
		System.out.println("Pet Database Program");
		int choice;//initialized for do/while loop
		
		loadDatabase();
		
		do {
			choice = menu();
			execute(choice);
		}while(choice != 4);
		
		saveDatabase();
		input.close();


	}



	public static int menu() {//Should've been getUserChoice but I felt menu was more appropriate
		System.out.println("\n\nWhat would you like to do?");
		System.out.println(" 1) View all pets");
		System.out.println(" 2) Add new pets");
		System.out.println(" 3) Remove a pet");
		System.out.println(" 4) Exit Program");
		System.out.print("Your Choice: ");
		int choice = input.nextInt();
		input.nextLine();
		System.out.println();
		return choice;
	}
	
	public static void execute(int choice) {//was to be part of main method but wanted to separate it out.
		switch(choice) {
			case 1:
				showAllPets(0, null, 0);
				break;
			case 2:
				addPets();
				break;
			case 3:
				removePet();
				break;
			case 4:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
		}
	}

	
	public static int petCount() {
		return numOfPets;//returns the amount of pets in the database
	}
	
	//adds items to the Pets array------------------------------------------------------------------------------
	private static void loadDatabase() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("pets.txt"));
			String line;
			while((line = reader.readLine()) != null) {
				try {
					String[] lineItem = line.split(" ");
					if (lineItem.length != 2) {
						throw new InvalidArgumentException();
					}else {
						addPet(lineItem);
					}
				} catch (InvalidArgumentException | ArrayIndexOutOfBoundsException AIOB) {
					System.out.println("InvalidArgumentException: " + line + " is not valid input.");
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addPets() {
		String newPet = "";
		do {
			System.out.print("add pet (name, age): ");
			newPet = input.nextLine();
			String[] data;
			if (!(newPet.equalsIgnoreCase("done"))) {//if not done, add new item
				try {
					data = newPet.split(" ");//splits the input by a space
					if (data.length != 2) {
						throw new InvalidArgumentException();
					}else {
						addPet(data);
					}
				} catch (InvalidArgumentException | ArrayIndexOutOfBoundsException AIOB) {
					System.out.println("InvalidArgumentException: " + newPet + " is not valid input.");
				}
			}
		}while(!(newPet.equalsIgnoreCase("done")));//adds data until the user is done
	}

	private static void addPet(String[] data) {
		try {
			if (petCount() == CAPACITY) {
				throw new FullDatabaseException();
			} else {
				numOfPets++;//shows the number of pets	
				int age = Integer.parseInt(data[1]);//makes the age an integer
				Pets.add(new Pet(data[0], age));//adds to the array
			} 
		} catch (FullDatabaseException FDE) {
			System.out.println("FullDatabaseException: Database is full.");
		}
	}
	//----------------------------------------------------------------------------------------------------------
	
	//All how the table is printed--------------------------------------------------------------
	private static void showAllPets(int choice, String name, int age) {
	//Choice allows for different ways to print the table
	//0 prints everything, 1 is name search, 2 is age search
	//name and age variables are used for the search
	//instructions had different method names for the search functions, however,
	///my idea utilized a similar method as the print so I incorporated it in here
		int num = 0;
		printTableHeader();
		for (Pet value: Pets) {
			if (num%10==0 && num != 0) {//reprints header for every 10 line items
				printTableHeader();
			}
			if (choice == 0) {//print all in Pets array
				printTableRow(Pets.indexOf(value), value.getName(), value.getAge());
				num++;
			}else if (choice == 1) {//search for name in array
				if (value.getName().equalsIgnoreCase(name)) {
					printTableRow(Pets.indexOf(value), value.getName(), value.getAge());
					num++;
				}
			}else if (choice == 2) {//search for age in array
				if (value.getAge() == age) {
					printTableRow(Pets.indexOf(value), value.getName(), value.getAge());
					num++;
				}
			}
		}
		printTableFooter(num);
	}
	
	private static void printTableHeader() {
		System.out.println("+----------------------+");
		System.out.printf("| %-3s|", "ID");//left aligns items
		System.out.printf(" %-10s|", "NAME");
		System.out.printf(" %-4s|%n", "AGE");
		System.out.println("+----------------------+");
	}
	
	private static void printTableRow(int id, String name, int age) {
		System.out.printf("|%3.3s |", id);//right aligns
		System.out.printf("%-10.10s |", name);//left aligns
		System.out.printf("%4.4s |%n", age);//right aligns
		
	}
	
	private static void printTableFooter(int num) {
		System.out.println("+----------------------+");
		System.out.println(num + " rows in set.");
	}
	//------------------------------------------------------------------------------------------
	
	//Any modifications to the Pets array happens here--------------------------------------------------------------
	private static void removePet() {//choice int allows for the difference between update and remove
							 				   //Instructions didn't have different method name for remove and
											   ///update so incorporated it all here. 	
		showAllPets(0, null, 0);
		while (true) {
			System.out.print("Enter the pet ID to remove: ");
			int petID = input.nextInt();
			input.nextLine();
			try {
				if(petID < 0 || petID > numOfPets-1) {
					throw new InvalidIDException();
				}else {
					System.out.println(Pets.get(petID).getName() + " " + Pets.get(petID).getAge() + " has been removed.");
					Pets.remove(Pets.get(petID));
					break;					
				}
			} catch (InvalidIDException IIE) {
				System.out.println("InvalidIDException: ID " + petID + " does not exist.");
			}
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	//Saves the database to a text file--------------------------------------------------
	private static void saveDatabase() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("pets.txt"));
			for (Pet value: Pets) {
				writer.write(value.getName() + " " + value.getAge());
				writer.write("\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------
	
	
}

//Pet class for the program ------------------
class Pet {
	private String name;
	private int age;
	
	public Pet(String name, int age) {
		setName(name);
		setAge(age);
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		while(true) {
			try {
				if (age < 1 || age > 50) {
					throw new InvalidAgeException();
				}else {
					this.age = age;
					break;
				}
			}catch(InvalidAgeException IAE) {
				System.out.println(age + " is not a valid age");
				Scanner input = new Scanner(System.in);
				System.out.print("Enter a valid age: ");
				age = input.nextInt();
				input.nextLine();
			}
		}
	}
	
}
//----------------------------------------------

//custom exception-----------------------------------------
class InvalidAgeException extends RuntimeException{} //Runtime to check as program is running not when compiling
class InvalidArgumentException extends RuntimeException{} //Runtime to check as program is running not when compiling
class InvalidIDException extends RuntimeException{}
class FullDatabaseException extends RuntimeException{}
//---------------------------------------------------------

