package com.jsoftware.test.impl;

import com.jsoftware.test.api.IShortAnswerQuestion;
import java.io.Serializable;
import java.util.ArrayList;

public class ShortAnswerQuestion extends Question implements IShortAnswerQuestion, Serializable {

	private ArrayList<String> keywords;

	public ShortAnswerQuestion(String question, ArrayList<String> keywords) {
		super(question);
		this.keywords = keywords;
	}

	@Override
	public boolean checkAnswer(String answer) {
		for (int i = 0; i < keywords.size(); i++) {
			//converted to lower case so there is no case sensitivity.
			if (answer.toLowerCase().contains(keywords.get(i).toLowerCase())) {
				return true;
			}
		}
		return false;
	}

//Setkeyword and getkeyword
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

}