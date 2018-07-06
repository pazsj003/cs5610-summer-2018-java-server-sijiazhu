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
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.models.Topic;
import webdev.models.Widget;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EssayExamQuestionService {
	@Autowired
	EssayExamQuestionRepository EssayRepo;

	@Autowired
	ExamRepository examRepository;

	@PostMapping("/api/exam/{examId}/essay")
	public EssayExamQuestion createEssayQuestion(@PathVariable("examId") int examId,
			@RequestBody EssayExamQuestion newEssayQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		System.out.println("yes I am in ESSAY outer*******");
		if (data.isPresent()) {
			Exam exam = data.get();
			newEssayQuestion.setExam(exam);
			System.out.println("yes I am in create EXan*******");
			return EssayRepo.save(newEssayQuestion);
		}
		return null;
	}

	@GetMapping("/api/exam/{examId}/essay")
	public List<EssayExamQuestion> findAllEssayQuestionsForExam(@PathVariable("examId") int examId) {
		List<EssayExamQuestion> newEssayquestion = new ArrayList<EssayExamQuestion>();
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if (optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseExamQuestion> baseQuestion = exam.getQuestions();
			for (BaseExamQuestion question : baseQuestion) {
				if (question instanceof EssayExamQuestion) {
					EssayExamQuestion Essayquestion = (EssayExamQuestion) question;
					if (!Essayquestion.getTitle().equals("")) {
						newEssayquestion.add(Essayquestion);
					}
				}
			}
			return newEssayquestion;
		}
		return null;
	}

	@DeleteMapping("/api/essay/{id}")
	public void deleteEssayQuestionById(@PathVariable("id") int EssayquestionId) {
		EssayRepo.deleteById(EssayquestionId);
	}

	@GetMapping("/api/essay")
	public List<EssayExamQuestion> findAllEssayQuestions() {
		return (List<EssayExamQuestion>) EssayRepo.findAll();
	}

	@GetMapping("/api/essay/{Id}")
	public EssayExamQuestion findEssayQuestionById(@PathVariable("Id") int EssayquestionId) {
		Optional<EssayExamQuestion> data = EssayRepo.findById(EssayquestionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	// @PostMapping("/api/exam/{examId}/essay")
	// public void saveEssayQuestion(@PathVariable("examId") int examId,
	// @RequestBody List<EssayExamQuestion> newEssayQuestion) {
	// Optional<Exam> data = examRepository.findById(examId);
	// if(data.isPresent()) {
	// Exam exam = data.get();
	// List<EssayExamQuestion> eassyQuestion=findAllEssayQuestionsForExam(examId);
	// for(EssayExamQuestion EassyQuestion : eassyQuestion) {
	// deleteEssayQuestionById(EassyQuestion.getId());
	// }
	// for (EssayExamQuestion EassyQuestion : newEssayQuestion) {
	// EassyQuestion.setExam(exam);
	// System.out.println("EassyQuestionID is
	// ***"+EassyQuestion.getId()+"EassyQuestion text
	// ******"+EassyQuestion.getTitle());
	// EssayRepo.save(EassyQuestion);
	// }
	// }
	// }

	@PutMapping("/api/essay/{Id}")
	public EssayExamQuestion updateEssayQuestion(@PathVariable("Id") int essayQuestionId,
			@RequestBody EssayExamQuestion newEssayExamQuestion) {
		EssayExamQuestion EssayQuestionfind = findEssayQuestionById(essayQuestionId);
		if (EssayQuestionfind != null) {
			EssayQuestionfind.setTitle(newEssayExamQuestion.getTitle());

			EssayQuestionfind.setDescription(newEssayExamQuestion.getDescription());

//			EssayQuestionfind.setExam(newEssayExamQuestion.getExam());

			EssayQuestionfind.setId(newEssayExamQuestion.getId());

			EssayQuestionfind.setInstructions(newEssayExamQuestion.getInstructions());

			EssayQuestionfind.setPoints(newEssayExamQuestion.getPoints());
			
			EssayQuestionfind.setIcon(newEssayExamQuestion.getIcon());
			 

			EssayQuestionfind.setType(newEssayExamQuestion.getType());

			EssayQuestionfind.setText(newEssayExamQuestion.getText());
			
			EssayRepo.save(EssayQuestionfind);
			
			return EssayQuestionfind;

		}
		return null;
	}
}
