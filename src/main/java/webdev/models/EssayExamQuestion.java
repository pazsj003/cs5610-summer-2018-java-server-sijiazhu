package webdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "SINGLE_ESSAY_QUESTION")
public class EssayExamQuestion extends BaseExamQuestion {
	@Column(name = "TEXT")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
