package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Exam;
import webdev.models.Lesson;

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

//	@GetMapping("/api/multi/{questionId}")
//	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
//		Optional<MultipleChoiceQuestion> optional = mutiRepo.findById(questionId);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}
//
//	@GetMapping("/api/truefalse/{questionId}")
//	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
//		Optional<TrueFalseQuestion> optional = trueFalseRepository.findById(questionId);
//		if(optional.isPresent()) {
//			return optional.get();
//		}
//		return null;
//	}
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
		 
		if(data.isPresent()) {
			Topic topic = data.get();
			newExam.setTopic(topic);
        
			return examRepository.save(newExam);
		}
		return null;	 
	}
	
}