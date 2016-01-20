package org.mbweb.fss.restfuljson.model;

import java.util.ArrayList;

public class HorsePicker_pojo {
	
	public String sessionToken;
	public ArrayList<String> selectedHorses;
	public String response;
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public ArrayList<String> getSelectedHorses() {
		return selectedHorses;
	}
	public void setSelectedHorses(ArrayList<String> selectedHorses) {
		this.selectedHorses = selectedHorses;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	
}
