package pl.spring.demo.to;

public class AuthorTo {

	private Long id;
	private String firstName;
	private String lastName;
	
	public AuthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return id+" "+firstName+" "+lastName;
	}
}
