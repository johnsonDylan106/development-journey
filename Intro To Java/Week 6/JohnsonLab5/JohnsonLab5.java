/*
 * Author: Dylan Johnson
 * Date: 10/13/22
 * Class: CSS222
 * Assignment: Lab 5
 * Description: Super and sub class creations
*/


public class JohnsonLab5 {

	public static void main(String[] args) {
		//sample data------------------------------------------------------------------------------------------------------------------
		Individual person1 = new Individual("Dylan", 25, "12345 Sample Street", "johnsond47@csp.edu", "123-456-7890");
		person1.print();
		
		Individual person2 = new Individual("John", 19, "54321 Sample Street", "appleseedj10@csp.edu", "098-765-4321");
		person2.print();
		
		TennisPro pro1 = new TennisPro("Jane", 30, "67890 Sample Street", "doej10@csp.edu", "765-098-4321", 6, 5, "Club 1", true);
		pro1.print();
		
		TennisPro pro2 = new TennisPro("Brian", 26, "56783 Sample Street", "greenb01@csp.edu", "456-123-7890", 3, 2, "Club 2", false);
		pro2.print();
		//-----------------------------------------------------------------------------------------------------------------------------
		
		//Compares 2 sets of data on who is older, currently hard coded, can be easily modified.
		boolean age = person1.isOlder(person1, person2);
		if (age == true) {
			System.out.println(person1.name + " is older than " + person2.name);
		}else {
			System.out.println(person2.name + " is older than " + person1.name);
		}
		//------------------------------------------------------------------------------------
		
		//Compares 2 sets of data on who is the higher level, currently hard coded, can be easily modified.
		boolean proLevel = pro1.isHigherLevelPro(pro1, pro2);
		if (proLevel == true) {
			System.out.println(pro1.name + " has a higher certificate then " + pro2.name);
		}else {
			System.out.println(pro2.name + " has a higher certificate then " + pro1.name);
		}
		//------------------------------------------------------------------------------------------------

	}

}

class Individual {
	public String name;
	public int age;
	public String address;
	public String email;
	public String cellPhone;
	public static int individuals;
	
	
	public Individual(String name, int age, String address, String email, String cellPhone) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.email = email;
		this.cellPhone = cellPhone;
		numIndObjects();
	}
	
	public void numIndObjects() {
		individuals++;//keeps track of number of instances
	}
	
	public void print() {
		System.out.println();
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Address: " + address);
		System.out.println("Email: " + email);
		System.out.println("Cell Phone: " + cellPhone);
		System.out.println("Number of Individuals: " + individuals);
	}
	
	public boolean isOlder(Individual ind1, Individual ind2) {
		if (ind1.age > ind2.age) {
			return true;
		}else {
			return false;
		}
	}
	
}

class TennisPro extends Individual {
	
	public int usptaCertificateLevel;
	public int yearsTeaching;
	public String clubAffiliation;
	public boolean proShopBuyer;
	public static int tennisPros;
	
	public TennisPro(String name, int age, String address, String email, String cellPhone, 
			int certLevel, int yearsTeaching, String clubAffiliation, boolean proShopBuyer) {
		super(name, age, address, email, cellPhone);//inherits required info from Individual class
		this.usptaCertificateLevel = certLevel;
		this.yearsTeaching = yearsTeaching;
		this.clubAffiliation = clubAffiliation;
		this.proShopBuyer = proShopBuyer;
		numTennisProObjects();
	}
	
	public void numTennisProObjects() {
		tennisPros++;//keeps track of number of instances
	}
	
	@Override
	public void print() {//overrides print function for tennis pros.
		super.print();
		System.out.println("USPTA Certificate Level: " + usptaCertificateLevel);
		System.out.println("Years Teaching: " + yearsTeaching);
		System.out.println("Club Affiliation: " + clubAffiliation);
		System.out.print("Pro Shop Buyer: ");
		if(proShopBuyer==true) {
			System.out.println("Yes");
		}else{
			System.out.println("No");
		}
		System.out.println("Number of Tennis Pros: " + tennisPros);
		System.out.println();
	}
	
	public boolean isHigherLevelPro(TennisPro ind1, TennisPro ind2) {
		if (ind1.usptaCertificateLevel > ind2.usptaCertificateLevel) {
			return true;
		}else {
			return false;
		}
	}
	
}
