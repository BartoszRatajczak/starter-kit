package pl.spring.demo.service.impl;

import pl.spring.demo.converter.BookEntityToBookTo;
import pl.spring.demo.converter.BookToToBookEntity;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
    private BookDao bookDao;

	@Autowired
	private BookEntityToBookTo bookEntityConverter;

	@Autowired
	private BookToToBookEntity bookToConverter;
	
    @Override
    public List<BookTo> findAllBooks() {
    	List<BookTo> bookList = new ArrayList<BookTo>();
    	for (BookEntity book : bookDao.findAll()) {
			bookList.add(bookEntityConverter.convert(book));
		}
        return bookList;
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
    	List<BookTo> bookList = new ArrayList<BookTo>();
    	for (BookEntity book : bookDao.findBookByTitle(title)) {
			bookList.add(bookEntityConverter.convert(book));
		}
        return bookList;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	List<BookTo> bookList = new ArrayList<BookTo>();
    	for (BookEntity book : bookDao.findBooksByAuthor(author)) {
			bookList.add(bookEntityConverter.convert(book));
		}
        return bookList;
    }

    @Override
    public BookTo saveBook(BookTo book) {
    	BookEntity bookEntity = bookDao.save(bookToConverter.convert(book));
		return bookEntityConverter.convert(bookEntity);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

	public void setBookEntityConverter(BookEntityToBookTo bookEntityConverter) {
		this.bookEntityConverter = bookEntityConverter;
	}

	public void setBookToConverter(BookToToBookEntity bookToConverter) {
		this.bookToConverter = bookToConverter;
	}
}
