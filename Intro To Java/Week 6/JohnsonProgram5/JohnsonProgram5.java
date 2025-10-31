/*
 * Author: Dylan Johnson
 * Date: 10/15/22
 * Class: CSS222
 * Assignment: Program 5
 * Description: Pets Database
*/

import java.util.ArrayList;
import java.util.Scanner;

public class JohnsonProgram5 {//Was this supposed to be called PetDatabase? I would not have been able to call the file the required name if so?
							  //Or was it supposed to be in a different class and only have the main element in this section? Wasn't sure so I went with it this way.
							  //I can modify to whatever the instructions are needed to be but ran out of time at this point to make any changes.
	public static Scanner input = new Scanner(System.in);//global scanner access
	public static ArrayList<Pet> Pets = new ArrayList<>();//global array access
	public static int numOfPets;//global access to number of pets
	
	public static void main(String[] args) {
		System.out.println("Pet Database Program");
		//My sample pet data---------------
//		Pets.add(new Pet("Ryder", 11));
//		Pets.add(new Pet("Ziva", 5));
//		Pets.add(new Pet("Greta", 2));
//		Pets.add(new Pet("Nelly", 1));
//		Pets.add(new Pet("Frank", 10));
//		Pets.add(new Pet("Pickles", 3));
//		Pets.add(new Pet("Nico", 3));
//		Pets.add(new Pet("Binx", 3));
//		Pets.add(new Pet("Jethro", 9));
//		Pets.add(new Pet("Teddy", 3));
//		Pets.add(new Pet("Louie", 1));
//		Pets.add(new Pet("Jabber", 6));
//		Pets.add(new Pet("Cocka", 1));
//		Pets.add(new Pet("Sugar", 8));
		//----------------------------------
		int choice;//initialized for do/while loop
		
		do {
			choice = menu();
			execute(choice);
		}while(choice != 7);


	}

	public static int menu() {//Should've been getUserChoice but I felt menu was more appropriate
		System.out.println("\n\nWhat would you like to do?");
		System.out.println("1) View all pets");
		System.out.println("2) Add more pets");
		System.out.println("3) Update an existing pet");
		System.out.println("4) Remove an existing pet");
		System.out.println("5) Search pets by name");
		System.out.println("6) Search pets by age");
		System.out.println("7) Exit program\n");
		System.out.print("Your choice: ");
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
				updatePet(0);
				break;
			case 4:
				updatePet(1);
				break;
			case 5:
				System.out.print("Enter name to search: ");
				String name = input.nextLine();
				showAllPets(1, name, 0);
				break;
			case 6:
				System.out.print("Enter age to search: ");
				int age = input.nextInt();
				input.nextLine();
				showAllPets(2, null, age);
				break;
			case 7:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
		}
	}

	
	public static int petCount() {
		return numOfPets;
	}

	//adds items to the Pets array----------------------------------------------------
	private static void addPets() {
		String newPet = "";
		do {
			System.out.print("add pet (name, age): ");
			newPet = input.nextLine();
			if (!(newPet.equalsIgnoreCase("done"))) {//if not done, add new item
				String[] data = newPet.split(" ");//splits the input by a space 
				int age = Integer.parseInt(data[1]);//makes the age an integer
				Pets.add(new Pet(data[0], age));//adds to the array
				numOfPets++;//shows the number of pets
			}
		}while(!(newPet.equalsIgnoreCase("done")));//adds data until the user is done
	}
	//---------------------------------------------------------------------------------
	
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
	private static void updatePet(int choice) {//choice int allows for the difference between update and remove
							 				   //Instructions didn't have different method name for remove and
											   ///update so incorporated it all here. 	
		showAllPets(0, null, 0);
		if (choice == 0) {
			System.out.print("Enter the ID of the pet you would like to update: ");
		}else if (choice == 1) {
			System.out.print("Enter the pet ID to remove: ");
		}
		int petID = input.nextInt();
		input.nextLine();
		if (choice == 0) {//updates record
			System.out.print("Enter new name and age: ");
			String name = input.next();
			int age = input.nextInt();
			System.out.print(Pets.get(petID).getName() + " " + Pets.get(petID).getAge() + " changed to ");
			Pets.get(petID).setName(name);
			Pets.get(petID).setAge(age);
			System.out.println(Pets.get(petID).getName() + " " + Pets.get(petID).getAge());
		}else if (choice == 1) {//deletes record
			System.out.println(Pets.get(petID).getName() + " " + Pets.get(petID).getAge() + " has been removed.");
			Pets.remove(Pets.get(petID));
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	
	
}

//Pet class for the program ------------------
class Pet {
	private String name;
	private int age;
	
	public Pet(String name, int age) {
		this.name = name;
		this.age = age;
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
		this.age = age;
	}
	
}
//----------------------------------------------

