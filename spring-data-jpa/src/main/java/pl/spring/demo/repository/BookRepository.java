package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.spring.demo.entity.BookEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')")
    public List<BookEntity> findBookByTitle(@Param("title") String title);

    /*@Query("select book from BookEntity book where upper(book.authors) like concat('%', upper(:author), '%')")
    public List<BookEntity> findBookByAuthor(@Param("author") String author);*/
    
    /*@Query("select book from BookEntity book join book.authors author where upper(author.author.firstName) like concat(upper(:name), '%') or upper(author.author.lastName) like concat(upper(:name), '%')")
	public List<BookEntity> findBookByAuthor(@Param("name") String name);*/
    
    @Query("SELECT b.books FROM AuthorEntity AS b WHERE CONCAT(b.personalData.name, ' ', b.personalData.surname) LIKE :search")
	List<BookEntity> findBookByAuthor(@Param("search") String author);
	
	/*@Query("SELECT b FROM BookEntity AS b WHERE UPPER(b.title) LIKE :search")
	List<BookEntity> findBookByTitleContainsIgnoreCase(@Param("search") String title);*/
    
    /*@Query("select book from BookEntity book join book.authors author where upper(author.author.name) like concat(upper(:name), '%') or upper(author.author.surname) like concat(upper(:name), '%')")
	public List<BookEntity> findBookByAuthor(@Param("name") String name);*/
}
