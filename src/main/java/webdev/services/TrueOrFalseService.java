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

import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
 
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueOrFalseService {
	@Autowired
	TrueFalseQuestionRepository TrueFlaseRepo;
	
	@Autowired
	ExamRepository examRepository;
	@PostMapping("/api/exam/{examId}/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseQuestion
	(@PathVariable("examId") int examId,@RequestBody TrueOrFalseExamQuestion newTrueFlaseQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in Mutil outer*******");
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueFlaseQuestion.setExam(exam);
        System.out.println("yes I am in create EXan*******");
			return TrueFlaseRepo.save(newTrueFlaseQuestion);
		}
		return null;	 
	}
	@GetMapping("/api/exam/{examId}/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTrueOrFalseQuestionsForExam
	(@PathVariable("examId") int examId) {
		List<TrueOrFalseExamQuestion> newTrueOrFalseQuestion = new ArrayList<TrueOrFalseExamQuestion>();
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if (optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> baseQuestion = exam.getQuestions();
			for (BaseExamQuestion question : baseQuestion) {
				if (question instanceof TrueOrFalseExamQuestion) {
					TrueOrFalseExamQuestion TrueOrFalsequestion = (TrueOrFalseExamQuestion) question;
					if (!TrueOrFalsequestion.getTitle().equals("")) {
						newTrueOrFalseQuestion.add(TrueOrFalsequestion);
					}
				}
			}
			return newTrueOrFalseQuestion;
		}
		return null;
	}

	@DeleteMapping("/api/truefalse/{id}")
	public void deleteTrueOrFalseQuestionById(@PathVariable("id") int TrueOrFalseId) {
		TrueFlaseRepo.deleteById(TrueOrFalseId);
	}

	@GetMapping("/api/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTrueOrFalseQuestions() {
		return (List<TrueOrFalseExamQuestion>) TrueFlaseRepo.findAll();
	}

	@GetMapping("/api/truefalse/{Id}")
	public TrueOrFalseExamQuestion findTrueOrFalseQuestionById(@PathVariable("Id") int TrueOrFalseId) {
		Optional<TrueOrFalseExamQuestion> data = TrueFlaseRepo.findById(TrueOrFalseId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}



	@PutMapping("/api/truefalse/{Id}")
	public TrueOrFalseExamQuestion updateTrueOrFalseQuestion
	(@PathVariable("Id") int TrueOrFalseId,@RequestBody TrueOrFalseExamQuestion newTrueOrFalseQuestion) {
		TrueOrFalseExamQuestion TrueOrFalseQuestionfind = findTrueOrFalseQuestionById(TrueOrFalseId);
		if (TrueOrFalseQuestionfind != null) {
			TrueOrFalseQuestionfind.setTitle(newTrueOrFalseQuestion.getTitle());

			TrueOrFalseQuestionfind.setDescription(newTrueOrFalseQuestion.getDescription());

//			TrueOrFalseQuestionfind.setExam(newTrueOrFalseQuestion.getExam());

			TrueOrFalseQuestionfind.setId(newTrueOrFalseQuestion.getId());

			TrueOrFalseQuestionfind.setInstructions(newTrueOrFalseQuestion.getInstructions());

			TrueOrFalseQuestionfind.setPoints(newTrueOrFalseQuestion.getPoints());

			TrueOrFalseQuestionfind.setIcon(newTrueOrFalseQuestion.getIcon());

			TrueOrFalseQuestionfind.setType(newTrueOrFalseQuestion.getType());
			

			TrueOrFalseQuestionfind.setTrue(newTrueOrFalseQuestion.isTrue());
		 
			
			 TrueFlaseRepo.save(TrueOrFalseQuestionfind);

			return TrueOrFalseQuestionfind;

		}
		return null;
	}
}
