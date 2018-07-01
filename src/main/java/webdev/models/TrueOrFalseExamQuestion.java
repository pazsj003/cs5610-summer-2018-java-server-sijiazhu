package webdev.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="SINGLE_TRUE_OR_FALSE_QUESTION")
public class TrueOrFalseExamQuestion  extends BaseExamQuestion {
	private boolean isTrue;
	@Column(name="IS_TRUE")
	public boolean isTrue() {
		return isTrue;
	}
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

}
