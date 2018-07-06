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

import webdev.models.FillInTheBlanksExamQuestion;
import webdev.models.Lesson;
import webdev.models.MultipleChoiceExamQuestion;
import webdev.models.OptionItem;
import webdev.models.Topic;
import webdev.models.TrueFalseList;
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.CourseRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;
import webdev.repositories.MultipleChoicesQuestionRepository;
import webdev.repositories.OptionItemRepositry;
import webdev.repositories.TopicRepository;
import webdev.repositories.TrueFalseQuestionRepository;
import webdev.repositories.TrueFlaseListRepository;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueFalseListService {
	@Autowired
	TrueFalseQuestionRepository TrueFlaseRepo;
	@Autowired
	TrueFlaseListRepository OptionItemRepo;
	
	
	
	
	

	@PostMapping("/api/truefalse/{truefalseId}/truefalseList")
	public TrueFalseList createTrueFalseListforTrueFalse
	(@PathVariable("truefalseId") int truefalseId,@RequestBody TrueFalseList newOptionItem) {
		System.err.println("no truefalse *******true false Id "+truefalseId);
		
		Optional<TrueOrFalseExamQuestion> data = TrueFlaseRepo.findById(truefalseId);
		System.err.println("yes I am in truefalse *******");
		if(data.isPresent()) {
			TrueOrFalseExamQuestion question = data.get();
			newOptionItem.setTrueFalsequestion(question);
        System.out.println("yes I am in create truefalseId*******");
			return OptionItemRepo.save(newOptionItem);
		}
		return null;	 
	}
	


	@GetMapping("/api/truefalse/{truefalseId}/truefalseList")
	public List<TrueFalseList> findAllTrueFalseListItemForTrueFalse
	(@PathVariable("truefalseId") int truefalseId) { 
		Optional<TrueOrFalseExamQuestion> optionalMultiple = TrueFlaseRepo.findById(truefalseId);
		if (optionalMultiple.isPresent()) {
			
			TrueOrFalseExamQuestion truefalse = optionalMultiple.get();
			List<TrueFalseList> OptionItem = truefalse.getTrueFalseList();
			System.err.println("True Flase Finder&&");
			return OptionItem;
		}
		System.err.println("True Flase not Finder&&");
		return null;
	}
	

	
	
	
	@DeleteMapping("/api/truefalseList/{id}")
	public void deleteTrueFalseListItemById(@PathVariable("id") int OptionId) {
		OptionItemRepo.deleteById(OptionId);
	}

	@GetMapping("/api/truefalseList")
	public List<TrueFalseList> findAllTrueFalseListItem() {
		return (List<TrueFalseList>) OptionItemRepo.findAll();
	}

	@GetMapping("/api/truefalseList/{Id}")
	public TrueFalseList findTrueFalseListById(@PathVariable("Id") int OptionId) {
		Optional<TrueFalseList> data = OptionItemRepo.findById(OptionId);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	
	@PutMapping("/api/truefalseList/{Id}")
	public TrueFalseList updateTrueFalseListItem
	(@PathVariable("Id") int OptionId,@RequestBody TrueFalseList newOptionItem) {
		TrueFalseList OptionItemfind = findTrueFalseListById(OptionId);
			 
		
		if (OptionItemfind != null) {
			OptionItemfind.setName(newOptionItem.getName());	 
			OptionItemfind.setTrueFalsequestion(newOptionItem.getTrueFalsequestion());	
			OptionItemRepo.save(OptionItemfind);
			System.out.println("@@@@@@@@@@done");
			return OptionItemfind;

		}
		return null;
	}
}
