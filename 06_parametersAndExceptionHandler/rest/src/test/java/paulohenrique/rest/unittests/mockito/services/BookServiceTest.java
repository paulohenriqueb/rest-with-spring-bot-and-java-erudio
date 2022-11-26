package paulohenrique.rest.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import paulohenrique.rest.data.vo.v1.BookVO;
//import paulohenrique.rest.exceptions.RequiredObjectIsNullException;
import paulohenrique.rest.model.Book;
import paulohenrique.rest.repositories.BookRepository;
import paulohenrique.rest.services.BookServices;
import paulohenrique.rest.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	MockBook input;
	
	@InjectMocks
	private BookServices bookService;
	
	@Mock
	BookRepository bookRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception{
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		
		when(bookRepository.findAll()).thenReturn(list);
		
		var book = bookService.findAll();
		assertNotNull(book);
		
		assertEquals(14, book.size());
		
		var bookOne = book.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		System.out.println(bookOne.getLinks());
		assertEquals("Author Test1", bookOne.getAuthor());
		assertNotNull(bookOne.getLaunchDate());
		assertEquals(25D, bookOne.getPrice());
		assertEquals("Title Test1", bookOne.getTitle());
		
		var bookFour = book.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		System.out.println(bookFour.getLinks());
		assertEquals("Author Test4", bookFour.getAuthor());
		assertNotNull(bookFour.getLaunchDate());
		assertEquals(25D, bookFour.getPrice());
		assertEquals("Title Test4", bookFour.getTitle());
		
		var bookSeven = book.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		
		System.out.println(bookSeven.getLinks());
		assertEquals("Author Test7", bookSeven.getAuthor());
		assertNotNull(bookSeven.getLaunchDate());
		assertEquals(25D, bookSeven.getPrice());
		assertEquals("Title Test7", bookSeven.getTitle());
	}
	
	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = bookService.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.getLinks());
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testCreate() {
		
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO bookVo = input.mockVO(1);
		bookVo.setKey(1L);
		
		entity.setId(1L);
		
		when(bookRepository.save(entity)).thenReturn(persisted);
				
		var result = bookService.create(bookVo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.getLinks());
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	/*
	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, 
				()-> bookService.create(null));
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
		
	}*/
	
	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO bookVo = input.mockVO(1);
		bookVo.setKey(1L);
		
		entity.setId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		when(bookRepository.save(entity)).thenReturn(persisted);
		
		var result = bookService.update(bookVo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.getLinks());
		assertEquals("Author Test1", result.getAuthor());
		assertNotNull(result.getLaunchDate());
		assertEquals(25D, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		bookService.delete(1L);
		
	}
}
