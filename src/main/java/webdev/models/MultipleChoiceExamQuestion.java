package webdev.models;

import java.util.ArrayList;
import java.util.Collection;
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
@Table(name="SINGLE_MULTI_QUESTION")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class MultipleChoiceExamQuestion extends BaseExamQuestion {
	
   
	
	@OneToMany(mappedBy = "Multiplequestion")
//	@JsonIgnoreProperties("Multiplequestion")
	@JsonIgnore
	private List <OptionItem> options;
 
	private int correctOption;
	public List<OptionItem> getOptions() {
		return options;
	}
	public void setOptions(List<OptionItem> options) {
		this.options = options;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}

}







//@Entity
//@Table(name="SINGLE_MULTI_QUESTION")
//public class MultipleChoiceExamQuestion extends BaseExamQuestion {
//	String option;
//	int correctOption;
//	public String[] getOption() {
//		if (option == null) return null;
//		return option.split(",");
//	}
//	public void setOption(String[] option) {
//		StringBuilder sb = new StringBuilder();
//		for (String s : option) {
//			sb.append(s).append(s).append(",");
//		}
//		this.option = sb.toString();
//	}
//	public int getCorrectOption() {
//		return correctOption;
//	}
//	public void setCorrectOption(int correctOption) {
//		this.correctOption = correctOption;
//	}
//	
//	
//}