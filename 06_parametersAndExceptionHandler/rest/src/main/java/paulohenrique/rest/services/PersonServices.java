package paulohenrique.rest.services;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import paulohenrique.rest.controllers.PersonController;
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
		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		persons.stream()
			.forEach(p -> p.add(
					linkTo(
							methodOn(PersonController.class).findById(
								p.getKey())).withSelfRel()
					));
		return persons;
	}
	
	public PersonVO findById(Long id) {
		logger.info("Finding one person!");
		
		var entity = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("No records found for this id!"));
		PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		var entity = DozerMapper.parseObject(person, Person.class);
		PersonVO personVo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Uppdating one person!");
		var entity = personRepository.findById(person.getKey())
			.orElseThrow(()-> new ResourceNotFoundException("Error"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		PersonVO personVo= DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
		personVo.add(linkTo(methodOn(PersonController.class).findById(personVo.getKey())).withSelfRel());
		return personVo;
		
	}
	
	public void delete(Long id) {
		logger.info("Deletting one person!");
		
		var entity = personRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Error to delete"));
		personRepository.delete(entity);
	}
	
}
