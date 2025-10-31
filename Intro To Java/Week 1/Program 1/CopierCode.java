import java.util.ArrayList;
import java.util.Scanner;


public class CopierCode {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int pin, leadingDigits, lastDigit, checkAgainst, sumOfLeadingNumbers;
        ArrayList<Integer>leadingDigitsList = new ArrayList<Integer>();
        
		do {
			
			pin = leadingDigits = lastDigit = checkAgainst = sumOfLeadingNumbers = 0; // sets all variables to 0 in the event that multiple runs happen
			leadingDigitsList.clear(); // clears the array in the event multiple runs happen
	        
			System.out.print("Enter the ID: ");
	        pin = scanner.nextInt();
	        scanner.close();
	        
	        if (String.valueOf(pin).length() == 3) { //attempt at extra credit
	        	
	        	lastDigit = pin % 10; //grabs the last digit
	        	leadingDigits = pin / 10; //takes off the last digit
	        	
	        	//builds the array
	        	while (leadingDigits > 0) {
	        		leadingDigitsList.add(leadingDigits % 10); //similar to above, takes the last digit and assigns it to a value in the array
	        		leadingDigits = leadingDigits / 10; //removes the digit assigned above from the integer value
	        	}
	        	
	        	if ((leadingDigitsList.get(0) + leadingDigitsList.get(1)) % 7 == lastDigit) { // doesn't need the 0 but if 3 digits works, the ID number passes.
	        		
	        		break; // breaks the loop and finishes the program IF a 3 digit pin works
	        		
	        	}else { // if it doesn't, not proceeds like there was not enough digits added.
	        		System.out.println("***Invalid ID number. Too few digits***");
		        	System.out.println("Please enter your given PIN");
	        	}
	        	
	        }else if (String.valueOf(pin).length() == 4) {
	        	// this section is essentially repeating if integer length = 3
	        	// added because I am checking if a 3 digit code works directly in.
	        	// could be replaced by functions ... not going that far
	        	
	        	lastDigit = pin % 10; //grabs the last digit
	            leadingDigits = pin / 10; //takes off the last digit

	            //builds the array
	        	while (leadingDigits > 0) {
	        		leadingDigitsList.add(leadingDigits % 10); //similar to above, takes the last digit and assigns it to a value in the array
	        		leadingDigits = leadingDigits / 10; //removes the digit assigned above from the integer value
	        	}
	        
	        // error messages	
	        }else if (String.valueOf(pin).length() < 4) {
	        	System.out.println("***Invalid ID number. Too few digits***");
	        	System.out.println("Please enter your given PIN");
	        }else if (String.valueOf(pin).length() > 4) {
	        	System.out.println("***Invalid ID number. Too many digits***");
	        	System.out.println("Please enter your given PIN");
	        }else if (pin == -1);{
	        	break;
	        }
	        
        }while(String.valueOf(pin).length() != 4);

		if (pin == -1) {
			System.out.println("Goodbye!");
		}else {
			for (int i = 0; i != String.valueOf(pin).length()-1; i++) {
				sumOfLeadingNumbers = sumOfLeadingNumbers + leadingDigitsList.get(i);
			}
		
	        checkAgainst = sumOfLeadingNumbers % 7; //generates the number to check against the "check digit"
			
	
	        if (checkAgainst == lastDigit) {
	            System.out.println("***Your ID is valid. You are authorized to use the copier***");
	        }
	        else {
	            System.out.println("***Invalid ID. Check digit doesn't match the sum. Good bye!***");
	        }
		}
		

		
	}

}


