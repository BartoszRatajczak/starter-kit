package pl.spring.demo.converter;

import org.junit.Assert;
import org.junit.Test;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookEntityToBookToTest {

	@Test
	public void testShouldConvertBookEntityToBookTo() {
		BookEntityToBookTo converter = new BookEntityToBookTo();
		BookEntity bookEntity = new BookEntity(22L, "Tytul ksiazki", "1 Stefan Batory 2 Kazimierz Wielki 3 Boleslaw Chrobry");
		BookTo bookTo = converter.convert(bookEntity);
		Assert.assertEquals(bookEntity.toString(), bookTo.toString());
	}
}
