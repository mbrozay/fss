package org.mbweb.fss.data_access.TestMains;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Test2col;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.google.gson.Gson;

public class SimpleHQLGson {

	public static void main(String[] args) {
		

			Session session = null;
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			Query query = session.createQuery("from Test2col");
			List<Test2col> t2c = (ArrayList<Test2col>) query.list();	
			int t2c1 = (int) t2c.get(0).getId();
			String t2ctext1 = (String) t2c.get(0).getText();
			System.out.println("Size of list result is " + t2c.size());
			System.out.println(t2c1);
			System.out.println(t2ctext1);
			
			String json = new Gson().toJson(t2c);
			System.out.println(json);
		

	}

}
