package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.EssayExamQuestion;
import webdev.models.Exam;

public interface EssayExamQuestionRepository extends CrudRepository<EssayExamQuestion, Integer> {

}
