package webdev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class OptionItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;


	@ManyToOne
	@JsonIgnore
//	@JsonIgnoreProperties("options")
	MultipleChoiceExamQuestion Multiplequestion;

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public MultipleChoiceExamQuestion getMultiplequestion() {
		return Multiplequestion;
	}
	public void setMultiplequestion(MultipleChoiceExamQuestion multiplequestion) {
		Multiplequestion = multiplequestion;
	}
	
	public void setName(String name) {
		this.name = name;
	}



}
