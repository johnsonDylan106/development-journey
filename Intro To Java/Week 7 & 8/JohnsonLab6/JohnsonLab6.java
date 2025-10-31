/*
 * Author: Dylan Johnson
 * Date: 10/19/22
 * Class: CSS222
 * Assignment: Lab 6
 * Description: Triangle exception
 */

import java.util.Scanner;

public class JohnsonLab6 {

	public static void main(String[] args) {
		System.out.println("Is Valid Triangle?");
		Scanner input = new Scanner(System.in);//input
		
		//Get side lengths ------------------------
		System.out.print("Enter side length: ");
		double side1 = input.nextDouble();
		System.out.print("Enter side length: ");
		double side2 = input.nextDouble();
		System.out.print("Enter side length: ");
		double side3 = input.nextDouble();
		//----------------------------------------
		//Minimal input sort-------------
		do {
			double hold;
			if (side1 > side2) {
				hold = side2;
				side2 = side1;
				side1 = hold;
			}else if (side2 > side3) {
				hold = side3;
				side3 = side2;
				side2 = hold;
			}else {
				break;
			}
		}while(true);
		//-------------------------------
		Triangle t1 = new Triangle (side1, side2, side3);//creates the triangle to test
		//t1.print(); //added to show sort is working as expected
		
		
		input.close();//closes scanner

	}
}

class Triangle {
	public double sideOne;
	public double sideTwo;
	public double sideThree;
	
	public Triangle(double sideOne, double sideTwo, double sideThree) {
		this.sideOne = sideOne;
		this.sideTwo = sideTwo;
		this.sideThree = sideThree;
		
		
		if (sideOne + sideTwo <= sideThree) {//since variables are sorted, this is all that needs to be tested
			throw new IllegalTriangleException("Side lengths do not make a valid Triangle");
		}else {
			System.out.println("Valid Triangle");
		}
	}
	
	public void print() {
		System.out.println(sideOne);
		System.out.println(sideTwo);
		System.out.println(sideThree);
	}

}

//custom exception-----------------------------------------
class IllegalTriangleException extends RuntimeException{ //Runtime to check as program is running not when compiling
	public IllegalTriangleException() {};
	public IllegalTriangleException(String message) { //Prints provided message
		super(message);
	}
	public IllegalTriangleException(Throwable cause) { //Checks for outside exception
		super(cause);
	}
	
}
//---------------------------------------------------------