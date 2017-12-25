package org.trams.hello.bean.api;

import java.util.List;

import org.trams.hello.bean.Answer;
import org.trams.hello.bean.Question;

public class QAMindCheck {
	private Question question;
	private List<Answer> answers;
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
