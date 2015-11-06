package pl.spring.demo.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.entity.BookEntity;

@Component
public class BookDaoImpl implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

	@Override
	public List<BookEntity> findAll() {
		return new ArrayList<>(ALL_BOOKS);
	}

	@Override
	public List<BookEntity> findBookByTitle(String titlePrefix) {
		List<BookEntity> bookList = new ArrayList<BookEntity>();
		for (BookEntity book : ALL_BOOKS) {
			if (book.getTitle().toUpperCase().startsWith(titlePrefix.toUpperCase())) {
				bookList.add(book);
			}
		}
		return bookList;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String authorPrefix) {
		String[] searchedAuthor = authorPrefix.toUpperCase().trim().split(" ");
		List<BookEntity> bookList = new ArrayList<BookEntity>();
		for (BookEntity book : ALL_BOOKS) {
			String[] authors = book.getAuthors().toUpperCase().trim().split(" ");
			for (String authorData : authors) {
				for (String searchedAuthorData : searchedAuthor) {
					if (authorData.startsWith(searchedAuthorData)) {
						bookList.add(book);
					}
				}
			}
		}
		return bookList;
	}

	@Override
	@NullableId
	public BookEntity save(BookEntity book) {
		ALL_BOOKS.add(book);
		return book;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "1 Wiliam Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "2 Hanna Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "3 Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "4 Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "5 Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "6 Aleksander Fredro"));
	}
}
