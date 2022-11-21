package paulohenrique.rest.services;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paulohenrique.rest.data.vo.v1.PersonVO;
import paulohenrique.rest.exceptions.ResourceNotFoundException;
import paulohenrique.rest.mapper.DozerMapper;
import paulohenrique.rest.model.Person;
import paulohenrique.rest.repositories.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository personRepository;
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		var entity = personRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Uppdating one person!");
		var entity = personRepository.findById(person.getId())
			.orElseThrow(()-> new ResourceNotFoundException("Error"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
		
	}
	
	public void delete(Long id) {
		logger.info("Deletting one person!");
		
		var entity = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Error to delete"));
		personRepository.delete(entity);
	}
	
}
