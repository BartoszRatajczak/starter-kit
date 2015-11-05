package pl.spring.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Component
public class BookToToBookEntity implements Converter<BookTo, BookEntity> {

	public BookToToBookEntity() {
	}
	
	@Override
	public BookEntity convert(BookTo bookTo) {
		BookEntity bookEntity;
		String authors = "";
		for (AuthorTo author : bookTo.getAuthors()) {
			authors += author.getId() + " " + author.getFirstName() + " " + author.getLastName() + " ";
		}
		bookEntity = new BookEntity(bookTo.getId(), bookTo.getTitle(), authors.trim());
		return bookEntity;
	}
}
