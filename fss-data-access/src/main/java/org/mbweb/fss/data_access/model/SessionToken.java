package org.mbweb.fss.data_access.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("sessionTokens")
@Entity
@Table(name="sessionToken")
public class SessionToken {
	@Id
	@GeneratedValue
	@JsonProperty
	@Column(name="id")
	private long id;
	@JsonProperty
	@Column(name="playerId")
	private long playerId;
	@JsonProperty
	@Column(name="session")
	private String session;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	

}
