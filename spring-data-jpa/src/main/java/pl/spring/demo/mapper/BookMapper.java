package pl.spring.demo.mapper;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.PersonalData;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

	public static BookTo map(BookEntity bookEntity) {
		if (bookEntity != null) {
			List<AuthorTo> authors = new ArrayList<AuthorTo>();
			for (AuthorEntity authorEntity : bookEntity.getAuthors()) {
				AuthorTo author = new AuthorTo(authorEntity.getId(), authorEntity.getPersonalData().getName(),
						authorEntity.getPersonalData().getSurname());
				authors.add(author);
			}
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authors);
		}
		return null;
	}

	public static BookEntity map(BookTo bookTo) {
		if (bookTo != null) {
			List<AuthorEntity> authors = new ArrayList<>();
			for (AuthorTo authorTo : bookTo.getAuthors()) {
				PersonalData personalData = new PersonalData(authorTo.getName(), authorTo.getSurname());
				AuthorEntity author = new AuthorEntity(authorTo.getId(), personalData);
				authors.add(author);
			}
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), authors);
		}
		return null;
	}

	public static List<BookTo> map2To(List<BookEntity> bookEntities) {
		return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
	}

	public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
		return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
	}
}
