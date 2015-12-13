package org.mbweb.fss.restfuljson.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player_Horse_noId;
import org.mbweb.fss.data_access.model.Player_horse;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;



public class ListHorseById {

	public String HorseList(String horseId){
	Session session = null;
	session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	long horseIdL = Long.parseLong(horseId);
	
	Query query = session.createQuery("from Horse where id = " + horseIdL);
	List<Horse> horses = (ArrayList<Horse>) query.list();	
	String horseName = horses.get(0).getName();
	
	return horseName;
	
	}
	
	public String HorseListJson(String horseJson) throws JsonParseException, JsonMappingException, IOException{
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		ObjectMapper mapper = new ObjectMapper();
/*		JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Player_horse.class);
		ArrayList<Player_horse> ph = mapper.readValue(horseJson,type);*/
		List<Player_Horse_noId> ph = mapper.readValue(horseJson,
				TypeFactory.defaultInstance().constructCollectionType(List.class,  
						Player_Horse_noId.class));
		String horseId = ph.get(0).getHorseId();
		
		long horseIdL = Long.parseLong(horseId);
		
		Query query = session.createQuery("from Horse where id = " + horseIdL);
		List<Horse> horses = (List<Horse>) query.list();	
		String horseName = horses.get(0).getName();
		
		return horseName;
		
		}
	
	
	
}
