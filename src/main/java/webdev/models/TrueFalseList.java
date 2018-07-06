package webdev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class TrueFalseList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;

	@ManyToOne
	@JsonIgnore
	@JsonIgnoreProperties("TrueFalseList")
	TrueOrFalseExamQuestion TrueFalsequestion;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public TrueOrFalseExamQuestion getTrueFalsequestion() {
		return TrueFalsequestion;
	}
	public void setTrueFalsequestion(TrueOrFalseExamQuestion trueFalsequestion) {
		TrueFalsequestion = trueFalsequestion;
	}
	public void setName(String name) {
		this.name = name;
	}

}
