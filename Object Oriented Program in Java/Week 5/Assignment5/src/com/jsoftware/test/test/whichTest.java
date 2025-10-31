package com.jsoftware.test.test;

import java.io.IOException;
import java.util.Scanner;

public class whichTest {

	public whichTest() throws IOException {
		Scanner input = new Scanner(System.in);
		String choice = "";
		int counter = 0;
		
		System.out.println("Welcome to the testing software!");
		
		do {
			System.out.println("What task would you like to do?");
			System.out.println("\n0) Exit\n1) Make a test\n2) Take a test\n");
			System.out.print("Choice: ");
			choice = input.nextLine();

			if (choice.equals("1")) {
				new testMaker();
			} else if (choice.equals("2")) {
				new testTaker();
			} else if (choice.equals("0") || counter == 3) {
				
				if(counter == 3) {
					System.out.println("Too many failed attempts\nShutting down");
				}
				
				System.out.println("Powered off");
				break;
			} else {
				System.out.println("Invalid choice. Please try again.\n\n");
				counter++;
			} 
		}while (!choice.equals("0") || counter != 3);
		input.close();
		
	}
	
}
