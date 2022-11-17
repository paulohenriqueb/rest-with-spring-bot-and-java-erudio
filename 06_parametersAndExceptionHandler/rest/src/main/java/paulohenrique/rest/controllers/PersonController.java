package paulohenrique.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import java.util.concurrent.atomic.AtomicLong;


import org.springframework.web.bind.annotation.RestController;

import paulohenrique.rest.model.Person;
import paulohenrique.rest.services.PersonServices;




@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonServices personService; 
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return personService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById( @PathVariable(value = "id") String id){
		return personService.findById(id);
	}
	
}
