package webdev.models;

import java.util.List;

import javax.persistence.Column;
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
@Table(name="SINGLE_TRUE_OR_FALSE_QUESTION")
@JsonIdentityInfo
(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class TrueOrFalseExamQuestion  extends BaseExamQuestion {
	private boolean isTrue;
//	 @JsonManagedReference
	
	@OneToMany
	(mappedBy = "TrueFalsequestion")
//	@JsonIgnoreProperties("TrueFalsequestion")
	@JsonIgnore
	private List <TrueFalseList> TrueFalseList;
	public List<TrueFalseList> getTrueFalseList() {
		return TrueFalseList;
	}
	public void setTrueFalseList(List<TrueFalseList> trueFalseList) {
		TrueFalseList = trueFalseList;
	}
	@Column(name="IS_TRUE")
	public boolean isTrue() {
		return isTrue;
	}
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

}
