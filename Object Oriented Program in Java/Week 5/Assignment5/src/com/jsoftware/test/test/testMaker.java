package com.jsoftware.test.test;

import com.jsoftware.test.api.IQuestion;
import com.jsoftware.test.api.IQuestionFactory;
import com.jsoftware.test.api.IQuestionSet;
import com.jsoftware.test.impl.QuestionFactory;
import com.jsoftware.test.impl.QuestionSet;

import java.util.ArrayList;
import java.util.Scanner;


public class testMaker {

	public static Scanner input = new Scanner(System.in);
	public testMaker(){
		//I would like to add a log in method that would prevent students from creating tests
		
		test();
	}
	
	//Starts the creation of a new test ----------------------------------------
	public void test(){
		
		IQuestionFactory questionType = new QuestionFactory();
		IQuestionSet question = new QuestionSet();
		String choice, testName;
		System.out.println("Welcome to the TestMaker program!");
		System.out.print("\nWhat would you like to call this test? ");
		testName = input.nextLine();
		
		do {
			System.out.println("\nWhat would you like to do?");
			System.out.println("\t1) add a multiple-choice question");
			System.out.println("\t2) add a true/false question");
			System.out.println("\t3) add a fill-in-the-blank question");
			System.out.println("\t4) add a short answer question");
			System.out.println("\t5) remove a question");
			System.out.println("\t6) exit the program");
			System.out.print("Your choice: ");
			choice = input.nextLine();
			
			action(choice, questionType, question, testName);
			
		}while (!choice.equals("6"));
		
		
	}
	//--------------------------------------------------------------------------

	
	//Selects the type of question based on user input ------------------
	public void action(String choice, IQuestionFactory questionType, IQuestionSet question, String testName) {
		switch(choice) {
		
		case "1":
			makeMCQ(questionType, question);
			break;
		case "2":
			makeTFQ(questionType, question);
			break;
		case "3":
			makeFITBQ(questionType, question);			
			break;
		case "4":
			makeSAQ(questionType, question);
			break;
		case "5":
			removeQuestion(question);
			break;
		case "6":
			System.out.println("\nDone!\n");
			questionType.save(question, testName + ".ser");
			break;
		default:
			System.out.println("Invalid choice please try again.");
					
		}
	}



	//--------------------------------------------------------------------

	
	//Adds a multiple choice question to the program --------------------------
	public void makeMCQ(IQuestionFactory questionType, IQuestionSet question) {
		ArrayList<String> mcQs = new ArrayList<>();
		System.out.println("\nWhat is your multiple choice question?");
		String questionPhrase = input.nextLine();//Stores question text
		
		String[] numbers = {"first", "second", "third", "fourth"}; //used in the for loop below
		//Prints out and stores the answer options-------------
		for(String value: numbers) {
			System.out.print("Please enter your " + value);
			if (value.equals("fourth")) {
				System.out.print(" and last choice: ");
			}else {
				System.out.print(" choice: ");
			}
			mcQs.add(input.nextLine());
		}
		//-----------------------------------------------------
	
		int answerChoice = 0;
		while(true) {
			System.out.print("What choice was the answer? (Enter #1-4): ");
			String ans = input.nextLine();
			try {
				answerChoice = Integer.parseInt(ans);
				if(answerChoice > 0 && answerChoice < 5) {
					break;
				} else {
					System.out.println("\nNot a valid answer choice.\n");
				}
			} catch(NumberFormatException nfe) {
				System.out.println("\nInvalid choice, please try again.\n");
			}
		}

		IQuestion mcQ = questionType.makeMultipleChoice(questionPhrase, mcQs, answerChoice);
		question.add(mcQ);
	}
	//--------------------------------------------------------------------------

	//Adds true/false question to program -------------------------------------------------
	private void makeTFQ(IQuestionFactory questionType, IQuestionSet question) {
		System.out.println("What is your True/False question?");
		String questionPhrase = input.nextLine();
		boolean answer;
		
		//Gets the true/false value of answer-----------------------------------
		do {
			System.out.println("\nWhat is your answer?");
			System.out.print("\nEnter 1 for true or 2 for false\nChoice: ");
			String ans = input.nextLine();
			if (ans.equals("1")) {
				answer = true;
				break;
			} else if (ans.equals("2")) {
				answer = false;
				break;
			}else {
				System.out.println("\nInvalid choice, please try again.\n");
			}
		}while(true); //runs until a correct value is entered
		//----------------------------------------------------------------------
		
		
		IQuestion tfQ = questionType.makeTrueFalse(questionPhrase, answer); 
		question.add(tfQ);
	}
	//--------------------------------------------------------------------------------------

	//Adds fill in the blank question to program ------------------------------------------------------
	private void makeFITBQ(IQuestionFactory questionType, IQuestionSet question) {
		ArrayList<String> fitbQs = new ArrayList<>();
		System.out.println("\nWhat is your fill in the blank question?");
		String questionPhrase = input.nextLine();
		System.out.println("What is the answer? Please separate answers with a comma and space.");
		String answer = input.nextLine();
		for (String value: answer.split(", ") ) {
			fitbQs.add(value);
		}
		
		IQuestion fitbQ = questionType.makeFillInBlank(questionPhrase, fitbQs);
		question.add(fitbQ);
	}
	//--------------------------------------------------------------------------------------------------

	//Adds short answer question to program-------------------------------------------------------------
	private void makeSAQ(IQuestionFactory questionType, IQuestionSet question) {
		ArrayList<String> saQs = new ArrayList<>();
		int numOfKeywords;
		System.out.println("What is your short answer question?");
		String questionPhrase = input.nextLine();
		
		//Checks that the users input is an integer so the program can continue--------------
		while(true) {
			System.out.println("How many keywords does your short answer question have?");
			String keywords = input.nextLine();
			
			try {
				numOfKeywords = Integer.parseInt(keywords);
				break;
			} catch(NumberFormatException nfe) {
				System.out.println("That was not a number, please try again.");
			}
		}
		//----------------------------------------------------------------------------------
		
		//Gets the number of keywords the user requested-------------------------------
		for (int i=0; i<numOfKeywords; i++) {
			System.out.print("What is a keyword in your short answer question? ");
			saQs.add(input.nextLine());
		}
		//-----------------------------------------------------------------------------
		
		IQuestion saQ = questionType.makeShortAnswer(questionPhrase, saQs);
		question.add(saQ);
	}
	//--------------------------------------------------------------------------------------------------

	//Removes a question from the program---------------------------------------------------------------
	private void removeQuestion(IQuestionSet question) {
		
		if (question.size() == 0) {
			System.out.println("\nNo questions to remove.");
		} else {
			System.out.println("Select the index of the question you would like to remove.");
			
			for (int i=0; i<question.size(); i++) {
				System.out.println(i + ") " + question.getQuestion(i).getQuestion());
			}
			int removeChoice = 0;
			
			while(true) {
				System.out.println("\nTo exit enter -1");
				System.out.print("Choice: ");
				String questionToRemove = input.nextLine();
				
				//Checks user input is an integer so program can continue------------
				try {
					removeChoice = Integer.parseInt(questionToRemove);
					
					if(removeChoice > -2 && removeChoice < question.size()) {
						break;					
					}else {
						System.out.println("\nNot a valid question choice, please try again.\n");
					}
					
				}catch (NumberFormatException nfe) {
					System.out.println("Invalid choice, please try again");
				}
				//-------------------------------------------------------------------
			}
			
			if (removeChoice != -1) {
				question.remove(removeChoice);//removes requested question
			}
		}
	}
	//--------------------------------------------------------------------------------------------------
	
	
}

