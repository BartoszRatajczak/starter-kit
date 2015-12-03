package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK")
public class BookEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	private String title;
	@ManyToMany
	@JoinTable(name = "BOOK_AUTHOR", joinColumns = {
			@JoinColumn(name = "id_book", updatable = false, nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_author", updatable = false, nullable = false) })
	private List<AuthorEntity> authors = new ArrayList<>();

	protected BookEntity() {

	}

	public BookEntity(Long id, String title, List<AuthorEntity> authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorEntity> authors) {
		this.authors = authors;
	}

}
