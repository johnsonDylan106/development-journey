package com.jsoftware.test.impl;

import java.io.Serializable;
import java.util.ArrayList;

import com.jsoftware.test.api.IFillInBlanksQuestion;

public class FillInBlanks extends Question implements IFillInBlanksQuestion, Serializable {

	private ArrayList<String> answer;

//Constructor
	public FillInBlanks(String question, ArrayList<String> answer) {
		super(question);
		this.answer = answer;
	}

//CheckAnswer
	@Override
	public boolean checkAnswer(ArrayList<String> keywords) {
		
		if (answer.size() != keywords.size()) {
			return false;
		}
		
		for (int i = 0; i < answer.size(); i++) {
			if (keywords.get(i).equalsIgnoreCase(answer.get(i))) {
				return true;
			}
		}
		return false;
	}
}