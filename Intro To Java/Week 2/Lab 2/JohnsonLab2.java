/*
 * Author: Dylan Johnson
 * Date: 9/16/22
 * Class: CSS222
 * Assignment: Lab 2
 * Description: Calculate the type and area of a Triangle
*/

import java.util.Scanner;

public class JohnsonLab2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		double side1, side2, side3;
		boolean checker = true;
		int counter = 1;
		
		do {
			
			if (checker == false) {
				System.out.println("The measurement given does not form a triangle.\nPlease try again.");
				counter++;
			};
			
			System.out.print("Side 1: ");
			side1 = input.nextDouble();
			System.out.print("Side 2: ");
			side2 = input.nextDouble();
			System.out.print("Side 3: ");
			side3 = input.nextDouble();
			checker = (((side1 + side2) > side3) & ((side2 + side3) > side1) & ((side1 + side3) > side2));
			if (counter==2 & checker == false) {
				counter++;
				break;
			};
			
		} while (checker == false);
		
		input.close();
		
		if (counter == 3) {
			System.out.println("The measurement given does not form a triangle.\nGoodbye.");
		} else {
			if (side1 == side2 & side1 == side3) {
				System.out.println("Equilateral");
			}else if (side1 == side2 | side1 == side3 | side2 == side3) {
				System.out.println("Isoceles");
			}else {
				System.out.println("Scalene");
			}
			
			double perimeter = side1 + side2 + side3;
			double semiPerimeter = (side1 + side2 + side3) / 2;
			double area = Math.sqrt(semiPerimeter*(semiPerimeter-side1)*(semiPerimeter-side2)*(semiPerimeter-side3));
			
			System.out.format("Triangle Perimeter: %.2f%n", perimeter);
			System.out.format("Triangle Area: %.2f%n", area);

		};

	}

}
