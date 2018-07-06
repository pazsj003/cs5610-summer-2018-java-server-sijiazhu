package webdev.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "SINGLE_FILL_IN_THE_BLANK_QUESTION")
@JsonIdentityInfo
(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class FillInTheBlanksExamQuestion extends BaseExamQuestion {

	@OneToMany(mappedBy = "Fillquestion")
//	@JsonIgnoreProperties("Fillquestion")
	@JsonIgnore
	private List<Variable> variable;

	public List<Variable> getVariable() {
		return variable;
	}

	public void setVariable(List<Variable> variable) {
		this.variable = variable;
	}
	
}
