package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.Assignment;
import webdev.models.BaseExamQuestion;
import webdev.models.Topic;

import webdev.models.Widget;
 
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.TopicRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*",maxAge=3600)
public class ExamService {
	@Autowired
	TopicRepository topicRepository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoicesQuestionRepository mutiRepo;

	@GetMapping("/api/topic/{topicId}/exam")
	public List<Exam> findAllExamsForTopic(@PathVariable("topicId") int topicId) {
		List<Exam> newExam=new ArrayList<Exam>();
		Optional<Topic> optionalTopic = topicRepository.findById(topicId);
		if(optionalTopic.isPresent()) {
			Topic topic = optionalTopic.get();
			List<Widget>widgets= topic.getWidgets();
			for(Widget widget:widgets) {
				if(widget instanceof Exam) {
					Exam exam  = (Exam)widget;
					if(!exam.getTitle().equals("")) {
						newExam.add(exam);
					}
				}
			}
			return newExam;
		}
		return null;
	}
	

	
	@PostMapping("/api/topic/{topicId}/exam")
	public Exam createExam(@PathVariable("topicId") int topicId,@RequestBody Exam newExam) {
		Optional<Topic> data = topicRepository.findById(topicId);
		System.out.println("…………………………………… create exam");
		if(data.isPresent()) {
			Topic topic = data.get();
			newExam.setTopic(topic);
			 System.out.println("%%%%%%%%%%%%%%5 create exam");
			return examRepository.save(newExam);
		}
		return null;	 
	}
	@DeleteMapping("/api/exam/{id}")
	public void deleteExamById(@PathVariable("id")int examId) {
		examRepository.deleteById(examId);
	}
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/Exam/{Id}")
	public Exam findExamById(@PathVariable("Id") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PutMapping("/api/exam/{Id}")
	public Exam updateExam(@PathVariable("Id") int examId,@RequestBody Exam newExam) {
		Exam examfind=findExamById(examId);
		if(examfind!=null) {
			examfind.setClassName(newExam.getClassName());
			examfind.setHeight(newExam.getHeight());
			examfind.setHrefLink(newExam.getHrefLink());
			examfind.setListItems(newExam.getListItems());
			examfind.setListType(newExam.getListType());
			examfind.setName(newExam.getName());
			examfind.setorderList(newExam.getorderList());
			examfind.setSize(newExam.getSize());
			examfind.setSrc(newExam.getSrc());
			examfind.setStyle(newExam.getStyle());
			examfind.setText(newExam.getText());
//			examfind.setTopic(newExam.getTopic());
			examfind.setWidgetType(newExam.getWidgetType());
			examfind.setWidth(newExam.getWidth());
			
			examfind.setTitle(newExam.getTitle());
			
			examfind.setQuestions(newExam.getQuestions());
			
			examfind.setDescription(newExam.getDescription());
		
			examRepository.save(examfind);
			
			return examfind;
			
		}
		return null;
	}
	
	
}