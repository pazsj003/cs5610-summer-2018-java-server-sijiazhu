package webdev.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Table(name ="SINGLE_EXAM")
@Entity
public class Exam extends Widget {
	@Column(name = "POINTS")
	private String points;
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToMany(mappedBy="exam")
	@JsonIgnore
	@Column(name = "QUESTIONS")
	private List<BaseExamQuestion> questions;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}
}