/*
 * 
1.	Finish the sentence 
      Relationships between classes are an important part of Object Oriented Programming because it enhances the usage ability/features of a given class 
 
2.	Describe an Association, in general terms.  
	Association is the connection between 2 sets of data, that can be repeated an infinite amount of times.
 
3.	List the four types of Associations between two classes.
	1-1, 1-many, many-1, many-many
 
4.	Illustrate each of these relationships by writing a sentence for each one between an Employee class and a Company class.  
	1-1: Every company (1) has a CEO (1).
	1-Many: Every company (1) has employees (many)
	many-1: Every employee (many) has a single company given ID (1).
	many-many: Every employee (many) could have multiple job titles (many).
 
5.	These Associations mean that objects from one class can communicate with objects of another class, enabling them to access data.  Describe how a HAS-a relationship is created in OOP. 
	A "has-a" relationship is like saying an employee "has a(n)" employee ID. This is created by designating a part of a class to storing that information.
	For example, in Class Employee there would be a public/private int ID. Then there would be the subclass of public Employee that would require the creation of the ID as part of the creation.
	class Employee {
		private String name;
		private int ID;
	
		public Employee(String name, int ID) {
			this.name = name;
			this.ID = ID;
		}
	}
 
6.	The two forms of Association are Aggregation and Composition.  Describe both, highlighting the differences. 
	Aggregation is more of the "Has-A" relationship where Composition is "belongs-to". Composition is a more solid connection where Aggregation is a weaker association. 
	Say, for example, Company A has employee B. If Company A no longer exists, employee B is still a person and is out there, but is no longer associated with Company A. This is an Aggregation relationship.
	However, if you were to break down Employee B into what a human needs (heart, brain, etc). Employee B would not be able to exist without those aspects. This is a composition relationship.

 */

import java.util.ArrayList;


public class Assignment1Part1 {

	public static ArrayList<Employee> Employees = new ArrayList<>();
	public static ArrayList<Company> Companies = new ArrayList<>();
	
	public static void main(String[] args) {
		
		//Creates instances of companies---------------
		Companies.add(new Company("Unemployed", 0));
		Companies.add(new Company("Google", 1));
		Companies.add(new Company("Apple", 2));
		//---------------------------------------------
		//Creates instances of employees--------------
		Employees.add(new Employee("DJ", 1000, 1));
		Employees.add(new Employee("SF", 1000, 2));
		Employees.add(new Employee("JW", 1001, 1));
		Employees.add(new Employee("MB", 1001, 6)); //sample employee with a company ID that does not currently exist
		//-------------------------------------------
		//Prints information of each employee instance-----------------------------------------------------------------
		for (Employee value: Employees) {	
			System.out.println("Employee: " + value.getName());
			System.out.println("Works for: " + Companies.get(value.getCompanyID(Companies)).getCompany() + "\n");
		}
		//-------------------------------------------------------------------------------------------------------------
		//Prints number of employees at each company-------------------------------------------------------------------
		for (Company value: Companies) {
			if (value.companyID != 0) {
				System.out.println("Number of employees at " + value.getCompany() + " = " + value.getNumOfEmployees());							
			}
		}
		//-------------------------------------------------------------------------------------------------------------

	}

}


//Employees are people, people can exist without being employees
class Employee {
	public String name;
	public int ID;
	public int indCompanyID;

	public Employee(String name, int ID, int indCompanyID) {
		this.name = name;
		this.ID = ID;
		this.indCompanyID = indCompanyID;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	
	//If the provided company doesn't exist, it returns the employee is unemployed
	public int getCompanyID(ArrayList<Company> Companies) {
		for(Company value: Companies) {
			if (indCompanyID == value.companyID) {
				Companies.get(indCompanyID).newEmployee();
				return indCompanyID;
			}
		}
		return 0;
	}
	//----------------------------------------------------------------------------
}


//Companies can't exist without having at least one employee
class Company {
	public String companyName;
	public int companyID;
	public int numOfEmployees;
	
	public Company(String companyName, int companyID) {
		this.companyName = companyName;
		this.companyID = companyID;
	}
	
	public String getCompany() {
		return companyName;
	}
	
	public int getNumOfEmployees() {
		return numOfEmployees;
	}
	
	public void newEmployee() {
		numOfEmployees++;
	}
}

