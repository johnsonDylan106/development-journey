package com.jsoftware.test.impl;

import java.io.Serializable;
import com.jsoftware.test.api.IQuestion;

public class Question implements IQuestion, Serializable {

	private String question;

	public Question(String question) {
		this.question = question;
	}

//Setter
	private void setQuestion(String question) {
		this.question = question;
	}

//getter
	public String getQuestion() {
		return question;
	}

}