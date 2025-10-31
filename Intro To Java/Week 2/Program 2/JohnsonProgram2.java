/*
 * Author: Dylan Johnson
 * Date: 9/16/22
 * Class: CSS222
 * Assignment: Program 2
 * Description: Calculates test score information for any number of students
*/

import java.util.ArrayList;
import java.util.Scanner;


public class JohnsonProgram2 {

	public static void main(String[] args) {
		//Declaring variables ----------------------------------
		Scanner input = new Scanner(System.in);
		ArrayList<Double>examScores = new ArrayList<>();
		ArrayList<Double>examScoresHold = new ArrayList<>();
		String name;
		int check = 0;
		//------------------------------------------------------
		
		System.out.println("Welcome to GradeCalculator");
		System.out.print("\nPlease enter the number of students: ");
		int stuCount = input.nextInt();
		System.out.print("Please enter the number of exams: ");
		int examCount = input.nextInt();

	//Compiles all the input from the user ---------------------------------------------------------
		for (int i = 0; i<stuCount; i++) {
			System.out.print("\nEnter student " + (i+1) + "'s name: ");	
			input.nextLine(); //stops the loop so the prompt can be answered correctly
			name = input.nextLine();
		//get's test scores correctly-----------------------------------------------
			do {
				if (check == 0) {
					System.out.print("Enter exam scores: ");					
				}else {
					System.out.print("Invalid exam scores, reenter: ");
					input.nextLine();
				};
				for (int x = 0; x < examCount; x++) {
					double score = input.nextDouble();
					if (score > 0) {
						examScoresHold.add(score);
						check = 0;
					}else {
						check = 1;
						examScoresHold.clear();
						break;
					};
				};
				
			} while (examScoresHold.size() != examCount);
		//--------------------------------------------------------------------------
			
		//Adds finalized test scores to an array -----------------------------------	
			for (int f = 0; f<examCount; f++) {
				examScores.add(examScoresHold.get(f));
			};
		//--------------------------------------------------------------------------
			
			System.out.println("\nGrade statistics for " + name);
			double average = 0;
			for (int c = 0; c < examCount; c++) {
				average += examScoresHold.get(c);
			}
			average = average/examCount;
			System.out.format("Average: %.2f%n", average);
			if (average >= 90) {
				System.out.println("Letter grade: A");
				System.out.println(name + " gets 4 stars! ****" + "-".repeat(30));
			}else if (average >= 80) {
				System.out.println("Letter grade: B");
				System.out.println(name + " gets 3 stars! ***" + "-".repeat(31));
			}else if (average >= 70) {
				System.out.println("Letter grade: C");
				System.out.println(name + " gets 2 stars! **" + "-".repeat(32));
			}else if (average >= 60) {
				System.out.println("Letter grade: D");
				System.out.println(name + " gets 1 star! *" + "-".repeat(34));
			}else {
				System.out.println("Letter grade: F");
				System.out.println(name + " gets no stars! " + "-".repeat(34));
			};
			
			examScoresHold.clear(); //clears hold array for next run			
		};
	//----------------------------------------------------------------------------------------------

		input.close();
		
	//Class Stats ----------------------------------------------------------------------------------
		double min = 100, max = 0, average = 0;
		for (int i=0; i < examScores.size(); i++ ) {
			min = Math.min(min, examScores.get(i));
			max = Math.max(max,  examScores.get(i));
			average += examScores.get(i);
		}
		
		System.out.println("\n\nClass statistics:");
		System.out.format("Average: %.2f%n",  (average/examScores.size()));
		System.out.format("Lowest: %.2f%n", min);
		System.out.format("Highest: %.2f%n",  max);
		System.out.println("Done. Good bye!");
		
		
		

	}

}
