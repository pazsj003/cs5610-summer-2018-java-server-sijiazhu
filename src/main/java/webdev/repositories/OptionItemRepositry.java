package webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import webdev.models.OptionItem;

public interface OptionItemRepositry 	extends CrudRepository<OptionItem,Integer> {

}
