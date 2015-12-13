package org.mbweb.fss.data_access.TestMains;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.serialize.HorseSerialize;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCallObjectList {

	public static void main(String[] args) throws JsonProcessingException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select id,name from Horse");
		HorseSerialize horseSerialize = new HorseSerialize(query);
		
		

		

	}

}
