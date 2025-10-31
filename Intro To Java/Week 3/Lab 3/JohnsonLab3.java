/*
 * Author: Dylan Johnson
 * Date: 9/21/22
 * Class: CSS222
 * Assignment: Lab3
 * Description: Identify a program using a switch statement.
*/

import java.util.Scanner;
public class JohnsonLab3 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a product you would like identified: ");
		String product = input.nextLine();
		
		
		product = product.toLowerCase();
		
		switch(product) {
		
		case "access":
			System.out.println("ACCESS is a Microsoft Product.");
			break;
		case "docs":
			System.out.println("DOCS is a Google Product.");
			break;
		case "excel":
			System.out.println("EXCEL is a Microsoft Product.");
			break;
		case "keynote":
			System.out.println("KEYNOTE is an Apple Product.");
			break;
		case "notepad":
			System.out.println("NOTEPAD is a Microsoft Product.");
			break;
		case "numbers":
			System.out.println("NUMBERS is an Apple Product.");
			break;
		case "pages":
			System.out.println("PAGES is an Apple Product.");
			break;
		case "powerpoint":
			System.out.println("POWERPOINT is a Microsoft Product.");
			break;
		case "sheets":
			System.out.println("SHEETS is a Google Product.");
			break;
		case "slides":
			System.out.println("SLIDES is a Google Product.");
			break;
		case "textedit":
			System.out.println("TEXTEDIT is an Apple Product.");
			break;
		case "word":
			System.out.println("WORD is a Microsoft Product.");
			break;
		default:
			System.out.println("\nEither this product is not currently in our catelog or\nThe product was name was entered in wrong.\nPlease try again.\nGoodbye.");
			
		}
		
		input.close();
		
		

	}

}
