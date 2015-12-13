package org.mbweb.fss.data_access.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="trainer")
public class Trainer {

	@Id
    @GeneratedValue
	private Long id;
	private String salutation;
	private String firstInitial;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String location;
	private String country;
	//Including the below causes a circular dependency and breaks gson
/*	@OneToMany(mappedBy="trainer")
	private Collection<Horse> horse = new ArrayList<Horse>();*/
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstInitial() {
		return firstInitial;
	}

	public void setFirstInitial(String firstInitial) {
		this.firstInitial = firstInitial;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}

