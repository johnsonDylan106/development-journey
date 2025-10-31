//Dylan Johnson
//Week 2 Programming Assignment

import java.util.*;
import java.util.ArrayList;


public class Assignment2 {
	
	public static ArrayList<String> info = new ArrayList<String>();
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String args[]) {
		
		int choice = 0;
		
		while (choice != 4) {
			System.out.print("1.Student\n2.Faculty\n3.Staff\n4.Exit\n\nChoice: ");
			choice = scanner.nextInt();
			scanner.nextLine();
			
			if (choice != 4) {
				getInfo(scanner);
			}
			
			switch (choice) {

			case 1:
				Student(info, scanner);
				break;
			case 2:
				Faculty(info, scanner);
				break;
			case 3:
				Staff(info, scanner);
				break;
			case 4:
				break;
			}
			info.clear();
		}
		
		System.out.println("Done");
	}

	private static void getInfo(Scanner scanner) {
		System.out.print("Enter name: ");
		info.add(scanner.nextLine());
		
		System.out.print("Enter Address: ");
		info.add(scanner.nextLine());
		
		System.out.print("Enter phone number: ");
		info.add(scanner.nextLine());
		
		System.out.print("Enter mail address: ");
		info.add(scanner.nextLine());
	}

	public static void Staff(ArrayList<String> info, Scanner scanner) {
		System.out.println("Enter officer number");
		int officerNumber_s = scanner.nextInt();
		
		System.out.print("Enter salary: ");
		int salary_s = scanner.nextInt();
		
		System.out.print("Enter Date: ");
		String date_s = scanner.nextLine();
		
		MyDate dt_s = new MyDate(date_s);
		
		System.out.print("Enter title of staff: ");
		String title = scanner.nextLine();
		
		Staff staff = new Staff(info.get(0), info.get(1), info.get(2), info.get(3), officerNumber_s, salary_s, dt_s, title);
		System.out.println(staff.toString());
	}

	public static void Faculty(ArrayList<String> info, Scanner scanner) {
		System.out.print("Enter officer number: ");
		int officerNumber = scanner.nextInt();
		
		System.out.print("Enter salary: ");
		int salary = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter Date: ");
		String date = scanner.nextLine();
		
		MyDate dt = new MyDate(date);
		
		System.out.println("Enter number of hours worked: ");
		String hrs = scanner.nextLine();
		
		System.out.println("Enter Rank: ");
		String rank = scanner.nextLine();
		
		Faculty f = new Faculty(info.get(0), info.get(1), info.get(2), info.get(3), officerNumber, salary, dt, hrs, rank);
		System.out.println(f.toString());
	}

	public static void Student(ArrayList<String> info, Scanner scanner) {
		System.out.print("Enter student status: ");
		String status = scanner.nextLine();
		
		Student student = new Student(info.get(0), info.get(1), info.get(2), info.get(3), status);
		System.out.println(student.toString());
	}
}





//Person --------------------------------------------------------------------------------
class Person {
	String name, address, phoneNumber, emailAddress;

	Person(String name, String address, String phoneNumber, String emailAddress) {																	
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;

	}

}
//----------------------------------------------------------------------------------------

//Student --------------------------------------------------------------------------------
class Student extends Person  {
	final String status; //per instructions

	Student(String name, String address, String phoneNumber, String emailAddress, String status) {
		super(name, address, phoneNumber, emailAddress);
		this.status = status;
	}

	public String toString() {
		return "\nStudent\nName: " + name + "\nAddress: " + address + "\nPhone number: " + phoneNumber+ "\nEmail Address: " + emailAddress + "\nStatus: " + status + "\n";
	}

}
//----------------------------------------------------------------------------------------

//Employee -------------------------------------------------------------------------------
class Employee extends Person {
	int officerNumber, salary;
	MyDate date;

	Employee(String name, String address, String phoneNumber, String emailAddress, int officerNumber, int salary, MyDate date) {
		super(name, address, phoneNumber, emailAddress);
		this.officerNumber = officerNumber;
		this.salary = salary;
		this.date = date;
	}
}
//----------------------------------------------------------------------------------------

// Faculty -------------------------------------------------------------------------------
class Faculty extends Employee {
	String hours, rank;

	Faculty(String name, String address, String phoneNumber, String emailAddress, int officerNumber, int salary, MyDate date, String hours, String rank) {
		super(name, address, phoneNumber, emailAddress, officerNumber, salary, date);
		this.hours = hours;
		this.rank = rank;
	}

	public String toString() {
		return "\nFaculty\nName: " + name + "\nAddress: " + address + "\nPhone number: " + phoneNumber
				+ "\nEmail Address: " + emailAddress + "\nOfficer number" + officerNumber + "\nSalary: " + salary
				+ "\nDate: " + date.getDate() + "\nHours: " + hours + "\nRank: " + rank + "\n";
	}
}
//----------------------------------------------------------------------------------------

// Staff ---------------------------------------------------------------------------------
class Staff extends Employee {
	String title;

	Staff(String name, String address, String phoneNumber, String emailAddress, int officerNumber, int salary,
			MyDate date, String title) {
		super(name, address, phoneNumber, emailAddress, officerNumber, salary, date);
		this.title = title;
	}

	public String toString() {
		return "\nStaff\nName: " + name + "\nAddress: " + address + "\nPhone number: " + phoneNumber + "\nEmail Address: "
				+ emailAddress + "\nOfficer number: " + officerNumber + "\nSalary: " + salary + "\nDate: "
				+ date.getDate() + "\nTitle: " + title + "\n";
	}
}
//----------------------------------------------------------------------------------------

// MyDate --------------------------------------------------------------------------------
class MyDate {
	private String date;

	public MyDate(String date) {
		this.date = date;

	}

	public String getDate() {
		return date;
	}
}
//----------------------------------------------------------------------------------------

