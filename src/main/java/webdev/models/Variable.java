package webdev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Variable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;



	@ManyToOne
	@JsonIgnore
//	@JsonIgnoreProperties("variable")
	FillInTheBlanksExamQuestion Fillquestion;

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public FillInTheBlanksExamQuestion getFillquestion() {
		return Fillquestion;
	}
	public void setFillquestion(FillInTheBlanksExamQuestion fillquestion) {
		Fillquestion = fillquestion;
	}

}
