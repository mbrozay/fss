package org.mbweb.fss.data_access.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Player_horse {

	@Id
	@GeneratedValue
	@JsonProperty
	@Column(name="id")
	private Long id;
	@ManyToOne
	@JoinColumn(name="playerId")
	private Player playerId;
	@ManyToOne
	@JoinColumn(name="horseId")
	private Horse horseId;
	private boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Player getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Player playerId) {
		this.playerId = playerId;
	}
	public Horse getHorseId() {
		return horseId;
	}
	public void setHorseId(Horse horseId) {
		this.horseId = horseId;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
	
}

