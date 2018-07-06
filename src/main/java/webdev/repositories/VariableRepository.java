package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.Variable;

public interface VariableRepository extends CrudRepository<Variable,Integer>{

}
