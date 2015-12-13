package org.mbweb.fss.data_access.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table
public class Player_horse {

	@Id
	@GeneratedValue
	private Long id;
	@JsonProperty
	private Long playerId;
	@JsonProperty
	private Long horseId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Long getHorseId() {
		return horseId;
	}
	public void setHorseId(Long horseId) {
		this.horseId = horseId;
	}
	
	
}

