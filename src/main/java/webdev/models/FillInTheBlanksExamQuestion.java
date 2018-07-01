package webdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "SINGLE_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {
	@Column(name = "VARIABLES")
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}
