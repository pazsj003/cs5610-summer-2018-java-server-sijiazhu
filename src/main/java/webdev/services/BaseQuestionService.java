package webdev.services;

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

import webdev.models.Assignment;
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;
 

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BaseQuestionService {
	@Autowired
	BaseExamQuestionRepository baseQuestionRepository;
	@Autowired
	ExamRepository examRepository;
	
	@GetMapping("/api/exam/{examId}/base")
	public List<BaseExamQuestion> findAllBaseQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}
	
	@PostMapping("/api/exam/{examId}/base")
	public BaseExamQuestion createBaseQuestion(@PathVariable("examId") int examId,@RequestBody BaseExamQuestion newBaseQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in Base outer*******");
		if(data.isPresent()) {
			Exam exam = data.get();
			newBaseQuestion.setExam(exam);
        System.out.println("yes I am in create EXan*******");
			return baseQuestionRepository.save(newBaseQuestion);
		}
		return null;	 
	}
	
	
	@DeleteMapping("/api/base/{id}")
	public void deleteBaseQuestionById(@PathVariable("id")int baseQuestionId) {
		baseQuestionRepository.deleteById(baseQuestionId);
	}
	
	@GetMapping("/api/base")
	public List<BaseExamQuestion> findAllBaseQuestions() {
		return (List<BaseExamQuestion>) baseQuestionRepository.findAll();
	}
	
	@GetMapping("/api/base/{Id}")
	public BaseExamQuestion findBaseExamQuestionById(@PathVariable("Id") int baseQuestionId) {
		Optional<BaseExamQuestion> data = baseQuestionRepository.findById(baseQuestionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
//	@PostMapping("/api/exam/{examId}/base")
//	public void saveBaseExamQuestion(@PathVariable("examId") int examId, @RequestBody List<BaseExamQuestion> newBaseExamQuestion) {
//		Optional<Exam> data = examRepository.findById(examId);
//		if(data.isPresent()) {
//			Exam exam = data.get();
//			List<BaseExamQuestion> baseQuestion=findAllBaseQuestionsForExam(examId);
//			for(BaseExamQuestion basequestion : baseQuestion) {
//				deleteBaseQuestionById(basequestion.getId());
//			}
//			for (BaseExamQuestion basequestion : newBaseExamQuestion) {
//				basequestion.setExam(exam);
//			System.out.println("basequestionID is ***"+basequestion.getId()+"basequestion text ******"+basequestion.getTitle());
//			baseQuestionRepository.save(basequestion);
//			}
//		} 
//	}
	
	@PutMapping("/api/base/{Id}")
	public BaseExamQuestion updateBaseQuestion(@PathVariable("Id") int baseQuestionId,@RequestBody BaseExamQuestion newBaseExamQuestion) {
		BaseExamQuestion baseQuestionfind=findBaseExamQuestionById(baseQuestionId);
		if(baseQuestionfind!=null) {
			baseQuestionfind.setTitle(newBaseExamQuestion.getTitle());
			
			baseQuestionfind.setDescription(newBaseExamQuestion.getDescription());
			
//			baseQuestionfind.setExam(newBaseExamQuestion.getExam());
			
			baseQuestionfind.setId(newBaseExamQuestion.getId());
			
			baseQuestionfind.setInstructions(newBaseExamQuestion.getInstructions());
			
			baseQuestionfind.setPoints(newBaseExamQuestion.getPoints());
			
			baseQuestionfind.setSubtitle(newBaseExamQuestion.getSubtitle());
			
			 
			baseQuestionfind.setType(newBaseExamQuestion.getType());
			baseQuestionRepository.save(baseQuestionfind);
 
			return baseQuestionfind;
			
		}
		return null;
	}
	
	
}
