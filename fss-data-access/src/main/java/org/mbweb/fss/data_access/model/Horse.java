package org.mbweb.fss.data_access.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName("horses")
@Entity
@Table(name="horse")
public class Horse {

	@Id
	@GeneratedValue
	@JsonProperty
	@Column(name="id")
	private long id;
	@JsonProperty
	@Column(name="name")
	private String name;
	@ManyToOne
	@JoinColumn(name="trainerId")
	private Trainer trainer;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	
}

