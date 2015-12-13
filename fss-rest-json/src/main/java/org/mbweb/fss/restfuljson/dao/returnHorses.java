package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class returnHorses {

	
public String HorseList() throws JsonProcessingException{
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Horse");
		List<Horse> horses = (ArrayList<Horse>) query.list();	
		
		System.out.println("Size of list result is " + horses.size());
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		String jacksonJson = mapper.writeValueAsString(horses);
		System.out.println(jacksonJson);
		
		
		return jacksonJson;
}
}
