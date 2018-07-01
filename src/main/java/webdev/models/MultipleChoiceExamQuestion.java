package webdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SINGLE_MULTI_QUESTION")
public class MultipleChoiceExamQuestion extends BaseExamQuestion {
	@Column(name = "OPTIONS")
	private String options;
	@Column(name = "CORRECTOPTION")
	private int correctOption;
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}

}
