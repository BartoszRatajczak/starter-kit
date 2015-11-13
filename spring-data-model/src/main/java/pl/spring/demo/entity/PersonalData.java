package pl.spring.demo.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PersonalData implements Serializable {

	private String name;
	private String surname;

	protected PersonalData() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
