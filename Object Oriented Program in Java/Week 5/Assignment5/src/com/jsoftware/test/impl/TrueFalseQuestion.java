package com.jsoftware.test.impl;

import com.jsoftware.test.api.ITrueFalseQuestion;
import java.io.Serializable;

public class TrueFalseQuestion extends Question implements ITrueFalseQuestion, Serializable {

	private boolean answer;

//Constructor
	public TrueFalseQuestion(String question, boolean answer) {
		super(question);
		this.answer = answer;
	}

//Check Answer
	@Override
	public boolean checkAnswer(String choice) {
		
		if (choice.equalsIgnoreCase("true")) {
			return true;
		}else {
			return false;
		}
		
	}

//setAnswer method
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

//getAnswer method
	public boolean getAnswer() {
		return answer;
	}
}