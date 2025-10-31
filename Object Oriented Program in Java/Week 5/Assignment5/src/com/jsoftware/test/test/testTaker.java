package com.jsoftware.test.test;

import com.jsoftware.test.api.IQuestionFactory;
import com.jsoftware.test.api.IQuestionSet;
import com.jsoftware.test.impl.FillInBlanks;
import com.jsoftware.test.impl.MultipleChoice;
import com.jsoftware.test.impl.QuestionFactory;
import com.jsoftware.test.impl.ShortAnswerQuestion;
import com.jsoftware.test.impl.TrueFalseQuestion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class testTaker {
	
	public static Scanner input = new Scanner(System.in);
	
	public testTaker() throws IOException {
		loadTest();
	}
	
	public void loadTest() throws IOException{
		
		IQuestionFactory questionType = new QuestionFactory();
		System.out.println("\n\nWelcome to the TestTaker Program!");

		IQuestionSet question = questionType.load(0); //removed the need to send test name and grabbing the name in load method
		IQuestionSet store = question; //Allows all questions to be restored after a subsection has been declared below
		
		System.out.println("\nTest Loaded Successfully!");
		
		takeTest(questionType, question, store);
	}
	
	public void takeTest(IQuestionFactory questionType, IQuestionSet question, IQuestionSet store) {
		
		String choice;
		do {
			question = store; // resets question set
			// Useful if multiple subsets of questions are ran
			System.out.println("\n\nAre you taking the whole test, or a sample?");
			System.out.println("1) Entire Test\n2) Sample of test\n3) Exit");
			System.out.print("Choice: ");
			choice = input.nextLine();
			
			if (!choice.equals("3")) {
				question = getQuestions(question, store, choice);
				
				header();
				
				for (int i=0; i<question.size(); i++) {
					
					
					System.out.println("\n\nQuestion " + (i+1) + " of " + question.size());
					System.out.println("-------------------");
					
					if(question.getQuestion(i) instanceof FillInBlanks) {
						fillInBlankQuestion(question, i);
						
					}else if(question.getQuestion(i) instanceof MultipleChoice) {
						multipleChoiceQuestion(question, i);
						
					}else if(question.getQuestion(i) instanceof ShortAnswerQuestion) {
						shortAnswerQuestion(question, i);
						
					}else if(question.getQuestion(i) instanceof TrueFalseQuestion) {
						trueFalseQuestion(question, i);
						
					}
					
				}
			}

		}while(!choice.equals("3"));
		
		
	}

	//Returns the number of questions per user request -- all or subset ---------------------------------
	private IQuestionSet getQuestions(IQuestionSet question, IQuestionSet store, String choice) {

		if (choice.equals("2")) {
			
			int sampleQuestions;
			
			while(true) {
				sampleQuestions = 0;
				System.out.print("How many questions would you like to sample? ");
				String ans = input.nextLine();
				try {
					sampleQuestions = Integer.parseInt(ans);
					
					//Checks that the sample set is not more than available questions--
					if (question.size() > sampleQuestions) {
						break;
					}else {
						throw new TooManyQuestionsException();
					}
					//------------------------------------------------------------------
					
				} catch (NumberFormatException nfe) {
					System.out.println("Please enter a valid number");
				} catch (TooManyQuestionsException tmqe) {
					System.out.println("\nMore questions selected to sample than are avaialable.");
					System.out.println("Please enter a smaller number\n\n");
				}
			}				
			
			question = question.randomSample(sampleQuestions);
		}else {
			question = store;
		}
		return question;
	}
	//----------------------------------------------------------------------------------------------------


	//test starts header----------------------------------------------
	private void header() {
		System.out.println("--------------------------------");
		System.out.println("The test starts now!");
		System.out.println("--------------------------------");
	}
	//----------------------------------------------------------------

	
	// Fill in the Blank questions-----------------------------------------------------------------------------
	private void fillInBlankQuestion(IQuestionSet question, int i) {
		System.out.println(((FillInBlanks) question.getQuestion(i)).getQuestion());

		ArrayList<String> keyWordCheck = new ArrayList<>();
		System.out.print("Your answer: ");
		String ans = input.nextLine();

		for(String value: ans.split(" ")) {
			keyWordCheck.add(value);
		}

		if(((FillInBlanks) question.getQuestion(i)).checkAnswer(keyWordCheck)) {
			correctAnswer();
		}else {incorrectAnswer();}
		

	}
	// --------------------------------------------------------------------------------------------------------
	
	//Multiple Choice questions--------------------------------------------------------------------------------
	private void multipleChoiceQuestion(IQuestionSet question, int i) {
		System.out.println(((MultipleChoice) question.getQuestion(i)).getQuestion());
		
		ArrayList<String> multipleChoiceOptions = ((MultipleChoice) question.getQuestion(i)).getChoices();
		
		int counter = 1;
		for (String value: multipleChoiceOptions) {
			System.out.println(counter + ") " + value);
			counter++;
		}
		
		System.out.print("Your answer: ");
		String selection = input.nextLine();
		int answer;
		try {
			answer = Integer.parseInt(selection);
			if (((MultipleChoice) question.getQuestion(i)).checkAnswer(answer)) { //this would be the line
				correctAnswer();
			} else {
				incorrectAnswer();
			}
		} catch(NumberFormatException nfe) {
			incorrectAnswer();
		}
		
	}
	//--------------------------------------------------------------------------------------------------------
	
	// Fill in the Blank questions-----------------------------------------------------------------------------
	private void shortAnswerQuestion(IQuestionSet question, int i) {
		System.out.println(((ShortAnswerQuestion) question.getQuestion(i)).getQuestion());
		
		System.out.print("Your answer: ");
		String ans = input.nextLine();
		
		if(((ShortAnswerQuestion) question.getQuestion(i)).checkAnswer(ans)) {
			correctAnswer();
		}else {incorrectAnswer();}
		
		

	}
	// --------------------------------------------------------------------------------------------------------
	
	// Fill in the Blank questions-----------------------------------------------------------------------------
	private void trueFalseQuestion(IQuestionSet question, int i) {
		System.out.println(((TrueFalseQuestion) question.getQuestion(i)).getQuestion());

		System.out.print("Your answer: ");
		String answer = input.nextLine();
		
		if (((TrueFalseQuestion) question.getQuestion(i)).checkAnswer(answer)) { //this would be the line
			correctAnswer();
		}else {incorrectAnswer();}

	}
	// --------------------------------------------------------------------------------------------------------

	
	
	//Returns if the answer is correct or not---
	/*
	 * I would like to make this a list of responses
	 ** so when the response is called it returns a 
	 ** random variable. Or even allows for custom 
	 ** responses by the test maker
	 */
	
	private void incorrectAnswer() {
		System.out.println("Wrong!");
	}

	private void correctAnswer() {
		System.out.println("You got it!");
	}
	//------------------------------------------

	
}
