package com.jsoftware.test.impl;

import com.jsoftware.test.api.IQuestion;
import com.jsoftware.test.api.IQuestionFactory;
import com.jsoftware.test.api.IQuestionSet;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class QuestionFactory implements IQuestionFactory, Serializable {

	public static Scanner input = new Scanner(System.in);
	
	@Override
	public IQuestion makeMultipleChoice(String question, ArrayList<String> choices, int answer) {
		IQuestion mcQ = new MultipleChoice(question, choices, answer);
		return mcQ;
	}

	@Override
	public IQuestion makeTrueFalse(String question, boolean answer) {
		IQuestion tf = new TrueFalseQuestion(question, answer);
		return tf;
	}

	@Override
	public IQuestion makeFillInBlank(String question, ArrayList<String> answers) {
		IQuestion fib = new FillInBlanks(question, answers);
		return fib;
	}

	@Override
	public IQuestion makeShortAnswer(String question, ArrayList<String> keywords) {
		IQuestion SA = new ShortAnswerQuestion(question, keywords);
		return SA;
	}

	@Override
	public IQuestionSet load(int loadAttempt) throws IOException {
		
		System.out.print("\nWhat test are you taking? ");
		String testName = input.nextLine();
		
		try {
			FileInputStream fs = new FileInputStream(testName + ".ser");
			ObjectInputStream in = new ObjectInputStream(fs);
			Object testObject = in.readObject();
			in.close();
			if (testObject instanceof IQuestionSet) {
				return (IQuestionSet) testObject;
			}
		} catch (FileNotFoundException | ClassNotFoundException cnfe) {
			if(loadAttempt >= 3) {
				System.out.println("Test not available. Please enter a different test name.");
				System.out.println("Please ensure test exists before continuing.\n\n");
				return null;
			}else {
				System.out.println("Test not available. Please enter a different test name.\n\n");				
			}
			
			loadAttempt++;
			IQuestionSet reRun = load(loadAttempt);
			return reRun;
		}
		return null;
	}

	@Override
	public boolean save(IQuestionSet questionSet, String filename) {

		try {
			FileOutputStream fs = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fs);
			out.writeObject(questionSet);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public IQuestionSet makeEmptyQuestionSet() {
		return null;
	}
	
}