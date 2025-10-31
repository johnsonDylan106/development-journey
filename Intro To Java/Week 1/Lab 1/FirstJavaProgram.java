import java.util.Scanner;

public class FirstJavaProgram {

	public static void main(String[] args) {

		 Scanner scanner = new Scanner(System.in);
		 
		 System.out.print("Name: ");
		 String name = scanner.nextLine();
		 
		 
		 // initializing variables
		 float grossPay, taxRate;
		 grossPay = taxRate = 0.0f;
		 
		 do {
			 System.out.print("Pay: ");
			 String Pay = scanner.nextLine();
			 try {
				 grossPay = Float.parseFloat(Pay);
			 }catch(NumberFormatException nfe) { // ensures user input is an integer that can be used
				 System.out.println("Compliments are great, but time is money. Please enter a number for payment.");
			 }
		 }while(grossPay == 0); // ensures a pay rate is added
		 
		 
		 do {
			 System.out.print("Tax percentage as whole number: ");
			 String rate = scanner.nextLine();
			 try {
				 taxRate = Float.parseFloat(rate);
			 } catch(NumberFormatException nfe) { // ensures user input is an integer that can be used
				 System.out.println("Please enter a valid percentage.");
			 }
			 
		 }while(taxRate <= 0 || taxRate > 100); // ensures can be used as an actual percentage
		 
		 scanner.close();
		 
		 // math functions
		 float taxPercentage = taxRate / 100;
		 float taxPaid = grossPay * taxPercentage;
		 float netPay = grossPay - taxPaid;
		 
		 // output for user with formatting
		 System.out.println("\n\nName:\t\t" + name);
		 System.out.format("Gross Pay:\t$%.2f%n", grossPay);
		 System.out.format("Tax Rate:\t%.2f%%%n", taxRate);
		 System.out.format("Tax Paid:\t$%.2f%n", taxPaid);
		 System.out.format("Net Pay:\t$%.2f%n", netPay);
		 
		 System.out.println("\nIsn't learning Java fun! :)");
		
	}

}
