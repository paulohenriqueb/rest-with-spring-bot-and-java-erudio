package paulohenrique.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import paulohenrique.rest.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
