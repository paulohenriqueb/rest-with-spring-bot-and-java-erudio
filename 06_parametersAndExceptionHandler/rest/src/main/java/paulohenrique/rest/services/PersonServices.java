package paulohenrique.rest.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paulohenrique.rest.data.vo.v1.PersonVO;
import paulohenrique.rest.exceptions.ResourceNotFoundException;
import paulohenrique.rest.repositories.PersonRepository;

@Service
public class PersonServices {
	
	@Autowired
	PersonRepository personRepository;
	
	public List<PersonVO> findAll() {
		return personRepository.findAll();
	}
	
	public PersonVO findById(Long id) {
		
		return personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));
	}
	
	public PersonVO create(PersonVO person) {
		
		
		return personRepository.save(person);
	}
	
	public PersonVO update(PersonVO person) {
		PersonVO entity = personRepository.findById(person.getId())
			.orElseThrow(()-> new ResourceNotFoundException("Error"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return personRepository.save(person);
	}
	
	public void delete(Long id) {
		PersonVO entity = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Error to delete"));
		personRepository.delete(entity);
	}
	
}
