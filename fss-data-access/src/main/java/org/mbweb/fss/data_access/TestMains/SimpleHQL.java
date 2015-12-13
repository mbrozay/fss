package org.mbweb.fss.data_access.TestMains;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Trainer;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

public class SimpleHQL {
static ArrayList<Horse> allHorses;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Horse");
		allHorses = (ArrayList<Horse>) query.list();	
//		String horse1 = (String) allHorses.get(0).getName();
//		String trainer1 = (String) allHorses.get(0).getTrainer().getLastName();
		System.out.println("Size of list result is " + allHorses.size());
//		System.out.println(horse1);
//		System.out.println(trainer1);
		
	//	SimpleHQL sH = new SimpleHQL();
	//	sH.saveToJsonFile("jsonFile", allHorses);	
	//	sH.jsonPrint(allHorses);
		
/*		Gson gson = new Gson();
		System.out.println(gson.toJson(allHorses));*/
	//	Object[] objArray = allHorses.toArray();
		ObjectMapper mapper = new ObjectMapper();
	//	mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			String rootName = Horse.class.getAnnotation(JsonRootName.class).value();
	//		mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String jacksonJson = mapper.writer().withRootName(rootName).withDefaultPrettyPrinter().writeValueAsString(allHorses);
			System.out.println(jacksonJson);
		
	}

	public void jsonPrint(ArrayList<Horse> jsonInput){
		try {
			String json = new Gson().toJson(jsonInput);
			System.out.println(json); 
	       } catch (Exception e) {
	    	   System.err.println("Caught IOException: " + e.getMessage());
	       }

	}
	
	public void saveToJsonFile(String filename, ArrayList<Horse> jsonInput) throws IOException{
		File f = new File(filename);
		FileWriter fw = new FileWriter(f);
		
		Gson gson = new Gson();
		for (Horse allHorses : jsonInput){
			String jsonLine = gson.toJson(allHorses) + "\n";
			fw.append(jsonLine);
		}
		fw.close();
	}
}
