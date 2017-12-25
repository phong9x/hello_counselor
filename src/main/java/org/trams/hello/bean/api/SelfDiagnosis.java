package org.trams.hello.bean.api;

import java.util.List;

import org.trams.hello.bean.Question;
import org.trams.hello.bean.SelfDiagnosisAnswer;

public class SelfDiagnosis {
	private Question question;
	private List<SelfDiagnosisAnswer> selfDiagnosisAnswers;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<SelfDiagnosisAnswer> getSelfDiagnosisAnswers() {
		return selfDiagnosisAnswers;
	}

	public void setSelfDiagnosisAnswers(List<SelfDiagnosisAnswer> selfDiagnosisAnswers) {
		this.selfDiagnosisAnswers = selfDiagnosisAnswers;
	}

}
