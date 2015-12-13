package org.mbweb.fss.rest.TestDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class TestHibernateList {

	public String HibernateList() throws JsonProcessingException{
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Horse");
		List<Horse> horses = (ArrayList<Horse>) query.list();	
	//	String horse1 = (String) horses.get(0).getName();
	//	String trainer1 = (String) horses.get(0).getTrainer().getLastName();
		System.out.println("Size of list result is " + horses.size());
	//	System.out.println(horse1);
	//	System.out.println(trainer1);
		
/*		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String json = gson.toJson(horses);*/
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	//	String rootName = Horse.class.getAnnotation(JsonRootName.class).value();
	//	String jacksonJson = mapper.writer().withRootName(rootName).withDefaultPrettyPrinter().writeValueAsString(horses);
		String jacksonJson = mapper.writeValueAsString(horses);
		System.out.println(jacksonJson);
		
		return jacksonJson;
		
	}
}
