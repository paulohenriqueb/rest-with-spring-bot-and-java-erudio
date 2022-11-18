package paulohenrique.rest.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paulohenrique.rest.exceptions.ResourceNotFoundException;
import paulohenrique.rest.model.Person;
import paulohenrique.rest.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	public Person findById(Long id) {
		
		return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));
	}
	
	public Person create(Person person) {
		
		
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		Person entity = personRepository.findById(person.getId())
			.orElseThrow(()-> new ResourceNotFoundException("Error"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(person);
	}
	
	public void delete(Long id) {
		Person entity = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Error to delete"));
		personRepository.delete(entity);
	}
	
}
