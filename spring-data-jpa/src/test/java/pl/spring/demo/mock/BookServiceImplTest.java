package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import pl.spring.demo.converter.BookEntityToBookTo;
import pl.spring.demo.converter.BookToToBookEntity;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookDao bookDao;
    
    private BookEntityToBookTo bookEntityConverter = new BookEntityToBookTo();
    private BookToToBookEntity bookToConverter = new BookToToBookEntity();

    @Before
    public void setUpt() {
        MockitoAnnotations.initMocks(this);
        bookService.setBookEntityConverter(bookEntityConverter);
        bookService.setBookToConverter(bookToConverter);
    }

    @Test
    public void testShouldSaveBook() {
        // given
    	bookEntityConverter = new BookEntityToBookTo();
        BookEntity book = new BookEntity(null, "title", "10 authorFirstName authorLastName");
        Mockito.when(bookDao.save(book)).thenReturn(new BookEntity(1L, "title", "10 authorFirstName authorLastName"));
        // when
        BookTo result = bookService.saveBook(bookEntityConverter.convert(book));
        // then
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, result.getId().longValue());
    }
}
