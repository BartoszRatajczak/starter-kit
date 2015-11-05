package pl.spring.demo.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

public class BookToToBookEntityTest {

	@Test
	public void testShouldConvertBookToToBookEntity() {
		BookToToBookEntity converter = new BookToToBookEntity();
		List<AuthorTo> authorsList = new ArrayList<AuthorTo>();
		authorsList.add(new AuthorTo(1L, "Stefan", "Batory"));
		authorsList.add(new AuthorTo(2L, "Kazimierz", "Wielki"));
		authorsList.add(new AuthorTo(3L, "Boleslaw", "Chrobry"));
		BookTo bookTo = new BookTo(22L, "Tytul ksiazki", authorsList);
		BookEntity bookEntity = converter.convert(bookTo);
		Assert.assertEquals(bookTo.toString(), bookEntity.toString());
	}
}
