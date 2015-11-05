package pl.spring.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Component
public class BookEntityToBookTo implements Converter<BookEntity, BookTo> {
	
	public BookEntityToBookTo() {
	}

	@Override
	public BookTo convert(BookEntity bookEntity) {
		BookTo bookTo;
		Long id;
		List<AuthorTo> authorsList = new ArrayList<AuthorTo>();
		String authors[] = bookEntity.getAuthors().split(" ");
		if (authors.length % 3 == 0) {
			for (int i = 0; i < authors.length; i += 3) {
				if (isNumeric(authors[i])) {
					id = Long.parseLong(authors[i]);
				} else {
					id = null;
				}
				authorsList.add(new AuthorTo(id, authors[i + 1], authors[i + 2]));
			}
		}
//		bookTo = new BookTo(2L, bookEntity.getTitle(), authorsList);
		bookTo = new BookTo(bookEntity.getId(), bookEntity.getTitle(), authorsList);
		return bookTo;
	}

	private boolean isNumeric(String s) {
		return s.matches("\\d+");
	}
}
