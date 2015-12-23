package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class returnHorsesPlayer {
	
public String HorePlayerList() throws JsonProcessingException{
	Session session = null;
	session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	Long playerId = (long) 2;
//	Query query = session.createQuery("select ph.id, ph.horseId from Player_horse as ph right outer join ph.horseId as hid with ph.playerId = 2  ");
	Query query = 
			session.createQuery
			("Select new map (hid.name as horsename, hid.id as horseid,\n"
					+ "ph.active as selectedHorse, hid.value as value)\n" 
			+ "from Player_horse as ph right outer join ph.horseId as hid with\n"
			+ "ph.playerId.id = 2");

	ArrayList<Object[]> allPlayerHorse = (ArrayList<Object[]>) query.list();
	System.out.println("Size of list result is " + allPlayerHorse.size());
	
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	
	String jacksonJson = mapper.writeValueAsString(allPlayerHorse);
	System.out.println(jacksonJson);
	
	session.close();
	return jacksonJson;
	
}

}
