package paulohenrique.rest.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import paulohenrique.rest.data.vo.v2.PersonVOV2;
import paulohenrique.rest.model.Person;

@Service
public class PersonMapper {
	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 personVoV2 = new PersonVOV2();
		personVoV2.setId(person.getId());
		personVoV2.setFirstName(person.getFirstName());
		personVoV2.setLastName(person.getLastName());
		personVoV2.setAddress(person.getAddress());
		personVoV2.setGender(person.getGender());
		personVoV2.setBithDay(new Date());
		return personVoV2;
	}

	public Person convertVoToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		//entity.setBithDay(new Date());
		return entity;
	}
}
