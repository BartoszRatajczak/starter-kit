package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.converter.BookEntityToBookTo;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookEntityToBookTo bookEntityConverter;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertTrue(6 <= allBooks.size());
    }

    @Test
    public void testShouldFindAllBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByTitlePrefix() {
        // given
        final String title = "Opi";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldNotFindAllBooksByTitle() {
        // given
        final String title = "Krzyzacy";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertTrue(booksByTitle.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthor() {
        // given
        final String author = "Wiliam Szekspir";
        // when
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
        // then
        assertNotNull(booksByAuthor);
        assertEquals(1, booksByAuthor.size());
        assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldFindAllBooksByAuthorPrefix() {
        // given
        final String author = "Wili Sze";
        // when
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
        // then
        assertNotNull(booksByAuthor);
        assertEquals(1, booksByAuthor.size());
        assertFalse(booksByAuthor.isEmpty());
    }
    
    @Test
    public void testShouldNotFindAllBooksByAuthor() {
        // given
        final String author = "Mickiewicz";
        // when
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(author);
        // then
        assertNotNull(booksByAuthor);
        assertTrue(booksByAuthor.isEmpty());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo(22L, "", new ArrayList<>());
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
    
    @Test
    public void testShouldSaveBook() {
    	bookEntityConverter = new BookEntityToBookTo();
        BookEntity book = new BookEntity(null, "title", "10 authorFirstName authorLastName");
        bookService.saveBook(bookEntityConverter.convert(book));
        List<BookTo> bookByAuthor = bookService.findBooksByAuthor("authorFirstName authorLastName");
		assertNotNull(bookByAuthor);
        assertEquals(1, bookByAuthor.size());
        assertNotNull(bookByAuthor.get(0).getId());
    }
}
