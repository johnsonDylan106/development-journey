package com.jsoftware.test.impl;

import com.jsoftware.test.api.IQuestion;
import java.io.Serializable;
import com.jsoftware.test.api.IQuestionSet;
import java.util.ArrayList;

public class QuestionSet implements IQuestionSet, Serializable {

	ArrayList<IQuestion> list = new ArrayList<>();

	@Override
	public IQuestionSet emptyTestSet() {
		list.clear();
		return this;
	}

	@Override
	public IQuestionSet randomSample(int size) {
		IQuestionSet randomQuestionSet = new QuestionSet();
		randomQuestionSet.emptyTestSet();
		
		ArrayList<Integer> questions = new ArrayList<>();
		questions.clear();

		int i = 0, j = 0;

		while (i < size) {
			int random = ((int) (Math.random() * size())); //picks a random question number

			//If the question hasn't already been added, adds the random question-----------
			for (j = 0; j < size; j++) {
				if (!questions.contains(random)/*random != valuequestions.get(j)*/) {
					questions.add(random);
					randomQuestionSet.add(getQuestion(random));
					i++;
				}
			}
			//------------------------------------------------------------------------------
		}
		return randomQuestionSet;
	}

	@Override
	public boolean add(IQuestion question) {
		list.add(question);
		return true;
	}

	@Override
	public boolean remove(int index) {
		list.remove(index);
		return true;
	}

	@Override
	public IQuestion getQuestion(int index) {
		return list.get(index);
	}

	@Override
	public int size() {
		return list.size();
	}

}