package paulohenrique.rest.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import paulohenrique.rest.data.vo.v1.BookVO;
import paulohenrique.rest.model.Book;

public class MockBook {
	public Book mockEntity() {
		return mockEntity(0);
	}
	
	public BookVO mockVO() {
		return mockVO(0);
	}
	
	public List<Book> mockEntityList(){
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}
	public List<BookVO> mockVoList(){
		List<BookVO> books = new ArrayList<>();
		for(int i=0; i<14; i++) {
			books.add(mockVO(i));
		}
		return books;
	}
	
	public Book mockEntity(Integer number) {
		Date currentDate = new Date();
		Book book = new Book();
		book.setAuthor("Author Test" + number);
		book.setLaunchDate(currentDate);
		book.setPrice(25D);
		book.setId(number.longValue());
		book.setTitle("Title Test" + number);
		return book;
	}
	
	public BookVO mockVO(Integer number) {
		Date currentDate = new Date();
		BookVO book = new BookVO();
		book.setAuthor("Author Test" + number);
		book.setLaunchDate(currentDate);
		book.setPrice(25D);
		book.setKey(number.longValue());
		book.setTitle("Title Test" + number);
		return book;
	}
}
