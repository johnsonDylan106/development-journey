package com.jsoftware.test.impl;

import com.jsoftware.test.api.IMultipleChoiceQuestion;
import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoice extends Question implements IMultipleChoiceQuestion, Serializable {

	private ArrayList<String> choices = new ArrayList<>();
	private int answer;

//Constructor
	MultipleChoice(String question, ArrayList<String> choices, int answer) {
		super(question);
		this.choices = choices;
		this.answer = answer;
	}

//CheckAnswer
	@Override
	public boolean checkAnswer(int answerChoice) {
		if (answer == answerChoice) {
			return true;
		}
		return false;
	}

//setChoices method
	public void setChoices(ArrayList<String> choices) {
		this.choices = choices;
	}

//getChoices method
	public ArrayList<String> getChoices() {
		return choices;
	}

//setAnswer method
	public void setAnswer(int answer) {
		this.answer = answer;
	}

//getAnswer method
	public int getAnswer() {
		return answer;
	}

}