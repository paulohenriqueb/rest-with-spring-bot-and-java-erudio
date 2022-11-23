package paulohenrique.rest.repositories;

import org.springframework.stereotype.Repository;

import paulohenrique.rest.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{}
