package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.MultipleChoiceExamQuestion;
 

public interface MultipleChoicesQuestionRepository
	extends CrudRepository<MultipleChoiceExamQuestion, Integer> {
	
}