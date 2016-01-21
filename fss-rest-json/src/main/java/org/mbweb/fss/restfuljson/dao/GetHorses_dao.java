package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.SessionToken;
import org.mbweb.fss.data_access.util.HibernateUtil;
import org.mbweb.fss.restfuljson.model.GetHorses_pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class GetHorses_dao {

	public String GetHorses(GetHorses_pojo getHorses_pojo) throws JsonProcessingException{
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String sessionTokenHql = "from SessionToken where session = :sessionToken";
		String sessionToken = getHorses_pojo.getSessionToken();
		Query sessionTokenQuery = session.createQuery(sessionTokenHql);
		sessionTokenQuery.setParameter("sessionToken", sessionToken);
		ArrayList<SessionToken> loggedInSession = (ArrayList<SessionToken>) sessionTokenQuery.list();
		long playerId = loggedInSession.get(0).getPlayerId();
		String playerIdString = String.valueOf(playerId);
		
		String horseSeletorHql = "Select new map (hid.name as horsename, hid.id as horseid,\n"
				+ "ph.active as selectedHorse, hid.value as value)\n" 
		+ "from Player_horse as ph right outer join ph.horseId as hid with\n"
		+ "ph.playerId.id = :playerId";
		Query horseSelectorQuery = 
				session.createQuery(horseSeletorHql);
		horseSelectorQuery.setParameter("playerId", playerId);	

		ArrayList<Object[]> allPlayerHorse = (ArrayList<Object[]>) horseSelectorQuery.list();
		System.out.println("Size of list result is " + allPlayerHorse.size());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		String jacksonJson = mapper.writeValueAsString(allPlayerHorse);
		System.out.println(jacksonJson);
		
		session.close();
		return jacksonJson;
		
	}
}
