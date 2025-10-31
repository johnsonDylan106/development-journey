/*
 * Author: Dylan Johnson
 * Date: 10/12/22
 * Class: CSS222
 * Assignment: Program 4
 * Description: GradeBook Statistics
*/

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.Comparator;

public class JohnsonProgram4 {

	private static Scanner input = new Scanner(System.in); // Global scanner
	private static ArrayList<gradeBook> students = new ArrayList<>(); //Global access to gradeBook array
	private static int stuCount = 0; //Global access to amount of students processed
	public static DecimalFormat f = new DecimalFormat("#0.00"); //global access to number formatting

	public static void main(String[] args) {

		int[][] scoreInfo = new int[3][3]; //add scoring information 
		greeting();
		scoreInfo(scoreInfo); //fills in score info and provides details for arrays
		
		//Leaving line 21 for the default values in the example if you would like.
		//int[][] scoreInfo = { { 3, 100, 60 }, { 3, 10, 30 }, { 4, 10, 10 } };//sample run for line 16/18

		
		int choice;//used for below do-while loop
		do {
			choice = displayMenu(); //provides list of what program can do

			menuChoices(choice, scoreInfo); //processes the users choice
			
		} while (choice != 5); //will run until the user decides to exit
		
		System.out.println("Goodbye!"); //closing

	}

	//Processes users choice ---------------------------------------------------
	private static void menuChoices(int choice, int[][] scoreInfo) {
		switch (choice) {
		case 1:
			createStudentInfo(scoreInfo);
			break;
		case 2:
			gradesStats(scoreInfo);
			break;
		case 3:
			classStats(scoreInfo);
			break;
		case 4:
			plotPoints(scoreInfo);
			break;
		case 5:
			break;
		default:
			System.out.println("Invalid choice, please try again");

		}
	}
	//---------------------------------------------------------------------------

	//Creates plot graph of grades as they stand ----------------------------------------------
	private static void plotPoints(int[][] scoreInfo) {
		ArrayList<Double> plot = new ArrayList<>();
		for (int i=0; i<stuCount; i++) {
			plot.add(students.get(i).getScores(scoreInfo, 0));
		}
		int zero, ten, twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety;
		zero = ten = twenty = thirty = forty = fifty = sixty = seventy = eighty = ninety = 0;
		for (double value: plot) {
			if (value < 10) {
				zero++;
			}else if (value < 20) {
				ten++;
			}else if (value < 30) {
				twenty++;
			}else if (value < 40) {
				thirty++;
			}else if (value < 50) {
				forty++;
			}else if (value < 60) {
				fifty++;
			}else if (value < 70) {
				sixty++;
			}else if (value < 80) {
				seventy++;
			}else if (value < 90) {
				eighty++;
			}else {
				ninety++;
			}
		}
		System.out.println("00-09  |" + "*".repeat(zero));
		System.out.println("10-19  |" + "*".repeat(ten));
		System.out.println("20-29  |" + "*".repeat(twenty));
		System.out.println("30-39  |" + "*".repeat(thirty));
		System.out.println("40-49  |" + "*".repeat(forty));
		System.out.println("50-59  |" + "*".repeat(fifty));
		System.out.println("60-69  |" + "*".repeat(sixty));
		System.out.println("70-79  |" + "*".repeat(seventy));
		System.out.println("80-89  |" + "*".repeat(eighty));
		System.out.println("90-100 |" + "*".repeat(ninety));
	}
	//----------------------------------------------------------------------------------------
	
	//Min, Max, Average scores for exams, quizzes, homework, and final grade------------------------------------------------------------
	private static void classStats(int[][] scoreInfo) {
		ArrayList<Double> eScores = new ArrayList<>();
		ArrayList<Double> qScores = new ArrayList<>();
		ArrayList<Double> hwScores = new ArrayList<>();
		double maxExamScore = 0, minExamScore = 100, averageExamScore = 0;
		double maxQuizScore = 0, minQuizScore = 100, averageQuizScore = 0;
		double maxHwScore = 0, minHwScore = 100, averageHwScore = 0;
		double maxScore = 0, minScore = 100, averageScore = 0;
		for (int i=0; i<stuCount; i++) {
			maxScore = Math.max(students.get(i).getScores(scoreInfo, 0), maxScore);
			minScore = Math.min(students.get(i).getScores(scoreInfo, 0), minScore);
			averageScore += students.get(i).getScores(scoreInfo, 0);
			eScores = students.get(i).getExamScore();
			for (int j=0; j<eScores.size(); j++) {
				maxExamScore = Math.max(maxExamScore, eScores.get(j));
				minExamScore = Math.min(minExamScore, eScores.get(j));
				averageExamScore += eScores.get(j);
			}
			qScores = students.get(i).getQuizScore();
			for (int j=0; j<qScores.size(); j++) {
				maxQuizScore = Math.max(maxQuizScore, qScores.get(j));
				minQuizScore = Math.min(minQuizScore, qScores.get(j));
				averageQuizScore += qScores.get(j);						
			}
			hwScores = students.get(i).getHwScore();
			for (int j=0; j<hwScores.size(); j++) {
				maxHwScore = Math.max(maxHwScore, hwScores.get(j));
				minHwScore = Math.min(minHwScore, hwScores.get(j));
				averageHwScore += hwScores.get(j);						
			}
			
		}
		averageExamScore = averageExamScore / (stuCount * scoreInfo[0][0]);
		averageQuizScore = averageQuizScore / (stuCount * scoreInfo[1][0]);
		averageHwScore = averageHwScore / (stuCount * scoreInfo[2][0]);
		averageScore = averageScore / stuCount;
		System.out.println("\tMin\tMax\tAverage");
		System.out.println("Exam\t" + f.format(minExamScore) + "\t" + f.format(maxExamScore) + "\t" + f.format(averageExamScore));
		System.out.println("Quiz\t" + f.format(minQuizScore) + "\t" + f.format(maxQuizScore) + "\t" + f.format(averageQuizScore));
		System.out.println("HW\t" + f.format(minHwScore) + "\t" + f.format(maxHwScore) + "\t" + f.format(averageHwScore));
		System.out.println("Grade\t" + f.format(minScore) + "\t" + f.format(maxScore) + "\t" + f.format(averageScore));
	}
	//-----------------------------------------------------------------------------------------------------------------------------------

	//Displays all scores entered for each student------------------------------------------
	private static void gradesStats(int[][] scoreInfo) {
		System.out.print("Name\t\t");
		System.out.println("E\t".repeat(scoreInfo[0][0]) + "Q\t".repeat(scoreInfo[1][0])
				+ "H\t".repeat(scoreInfo[2][0]) + "G\tL");
		Collections.sort(students, new sort());
		for (int i = 0; i < stuCount; i++) {
			students.get(i).printStuName();
			students.get(i).getScores(scoreInfo, 1);
		}
	}
	//-------------------------------------------------------------------------------------

	//Creates elements of the students ArrayList--------------------------------------------------
	private static void createStudentInfo(int[][] scoreInfo) {
		String newStudentInput = "";
		while (!(newStudentInput.equalsIgnoreCase("done"))) {
			double[] exam = new double[scoreInfo[0][0]], quiz = new double[scoreInfo[1][0]],
					hw = new double[scoreInfo[2][0]];
			if (stuCount == 199) {
				break;
			}
			fillScores(exam, quiz, hw);
			int examc = 0, quizc = 0, hwc = 0;
			System.out.print("Data> ");
			newStudentInput = input.nextLine();
			String[] newStudent = newStudentInput.split(": ");
			try {
				String[] newStudentGrades = newStudent[1].split(" ");
				for (int i = 0; i < newStudentGrades.length; i++) {
					if (newStudentGrades[i].charAt(0) == 'e') {
						String[] hold = newStudentGrades[i].split("e");
						exam[examc] = Double.parseDouble(hold[1]);
						examc++;
					} else if (newStudentGrades[i].charAt(0) == 'q') {
						String[] hold = newStudentGrades[i].split("q");
						quiz[quizc] = Double.parseDouble(hold[1]);
						quizc++;
					} else if (newStudentGrades[i].charAt(0) == 'h') {
						String[] hold = newStudentGrades[i].split("h");
						hw[hwc] = Double.parseDouble(hold[1]);
						hwc++;
					}
				}

			} catch (ArrayIndexOutOfBoundsException nfe) {
				if (newStudentInput.equalsIgnoreCase("done")) {
					continue;
				}
			}
			;

			students.add(new gradeBook(newStudent[0], exam, quiz, hw));
			stuCount++;
		}
		;
		if (stuCount == 199) {
			System.out.println("Max capacity of GradeBook");
		}
	}
	//-------------------------------------------------------------------------------------------

	//Fills blank score arrays for the amount of assignments provided at the beginning----
	private static void fillScores(double[] exam, double[] quiz, double[] hw) {
		for (int i = 0; i < exam.length; i++) {
			exam[i] = 0;
		}
		for (int i = 0; i < quiz.length; i++) {
			quiz[i] = 0;
		}
		for (int i = 0; i < hw.length; i++) {
			hw[i] = 0;
		}
	}
	//------------------------------------------------------------------------------------

	//User menu to hcoose from-------------------------------------------
	private static int displayMenu() {
		int choice;
		System.out.println("\nWhat would you like to do?\n");
		System.out.println("1. Add Student Data");
		System.out.println("2. Display Student Grades & Statistics");
		System.out.println("3. Display Class Statistics");
		System.out.println("4. Plot Grade Distribution");
		System.out.println("5. Quit");
		System.out.print("\nEnter Choice: ");
		choice = input.nextInt();
		input.nextLine();
		return choice;
	}
	//-------------------------------------------------------------------

	//Opening Greeting------------------------------------------------------------------------------------------------------------------------------------
	private static void greeting() {
		System.out.println("Welcome to Gradebook!\n");
		System.out.println("Please provide grade item details:\nAll in format (number of assignments, Points possible, weight %age of assignment)\n");
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------

	//Holds the total amount of exams, quizzes, and homework assignments--
	private static void scoreInfo(int[][] scoreInfo) {
		System.out.print("Exams (number, points, weight): ");
		for (int i = 0; i < scoreInfo.length; i++) {
			scoreInfo[0][i] = input.nextInt();
		}
		System.out.print("Quizzes (number, points, weight): ");
		for (int i = 0; i < scoreInfo.length; i++) {
			scoreInfo[1][i] = input.nextInt();
		}
		System.out.print("Homework (number, points, weight): ");
		for (int i = 0; i < scoreInfo.length; i++) {
			scoreInfo[2][i] = input.nextInt();
		}
	}
	//---------------------------------------------------------------------

}

class gradeBook {

	public String stuName;
	public double examScore[];
	public double quizScore[];
	public double hwScore[];

	//creates student--------------------------------------------------------------------------------
	public gradeBook(String stuName, double[] examScore, double[] quizScore, double[] hwScore) {
		this.stuName = stuName;
		this.examScore = examScore;
		this.quizScore = quizScore;
		this.hwScore = hwScore;
	}
	//----------------------------------------------------------------------------------------------

	//Returns all exam scores for a particular student in an array---
	public ArrayList<Double> getExamScore() {
		ArrayList<Double> eScores = new ArrayList<>();
		for(double value: examScore) {
			if (value != 0) {
				eScores.add(value);
			}
		}
		return eScores;
	}
	//---------------------------------------------------------------

	//Returns all quiz scores for a particular student in an array---
	public ArrayList<Double> getQuizScore() {
		ArrayList<Double> qScores = new ArrayList<>();
		for(double value: quizScore) {
			if (value != 0) {
				qScores.add(value);
			}
		}
		return qScores;
	}
	//---------------------------------------------------------------
	
	//Returns all HW scores for a particular student in an array---
	public ArrayList<Double> getHwScore() {
		ArrayList<Double> hwScores = new ArrayList<>();
		for(double value: hwScore) {
			if (value != 0) {
				hwScores.add(value);
			}
		}
		return hwScores;
	}
	//---------------------------------------------------------------

	//Processes all E/Q/HW scores, prints out if wanted, returns weighted grade----------------
	public double getScores(int[][] scoreInfo, int toPrint) {
		double eTotal = 0, qTotal = 0, hTotal = 0; 
		double weight[] = new double[3];
		for (double value : examScore) {
			if(toPrint == 1) {System.out.print(value + "\t");}
			eTotal += value;
		}
		for (double value : quizScore) {
			if(toPrint == 1) {System.out.print(value + "\t");}
			qTotal += value;
		}
		for (double value : hwScore) {
			if(toPrint == 1) {System.out.print(value + "\t");}
			hTotal += value;
		}
		double weightedGrade = weigthtPercentage(scoreInfo, eTotal, qTotal, hTotal, weight);
		if (toPrint == 1) {
			System.out.printf("%.2f", weightedGrade);
			System.out.print("\t");
			letterGrade(weightedGrade);
		}
		return weightedGrade;
	}
	//-----------------------------------------------------------------------------------------

	//Prints out letter grade if desired------------------
	private void letterGrade(double weightedGrade) {
		if (weightedGrade >= 90) {
			System.out.println("A");
		}else if (weightedGrade >= 80) {
			System.out.println("B");
		}else if (weightedGrade >= 70) {
			System.out.println("C");
		}else if (weightedGrade >= 60) {
			System.out.println("D");
		}else {
			System.out.println("F");
		}
	}
	//---------------------------------------------------

	//Calculates the weighted grade--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private double weigthtPercentage(int[][] scoreInfo, double eTotal, double qTotal, double hTotal, double[] weight) {
		weight[0] = scoreInfo[0][2];
		weight[1] = scoreInfo[1][2];
		weight[2] = scoreInfo[2][2];
		double weightedGrade = ((eTotal/(scoreInfo[0][0]*scoreInfo[0][1]))*(weight[0]/100)) + ((qTotal/(scoreInfo[1][0]*scoreInfo[1][1]))*(weight[1]/100)) + ((hTotal/(scoreInfo[2][0]*scoreInfo[2][1]))*(weight[2]/100));
		return weightedGrade*100;
	}
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//Prints out students name formatted for sample data---
	public void printStuName() {
		if (this.stuName.contains(" ")) {
			System.out.print(this.stuName + "\t");
		} else {
			System.out.print(this.stuName + "\t\t");
		}
	}
	//-----------------------------------------------------

	//Returns students name--------
	public String getStuName() {
		return this.stuName;
	}
	//-----------------------------

}

//added to sort output of students
class sort implements Comparator<gradeBook> {

    @Override
    public int compare(gradeBook one, gradeBook two) {
        return one.getStuName().compareTo(two.getStuName());
    }
}
