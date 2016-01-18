package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.util.HibernateUtil;
import org.mbweb.fss.restfuljson.model.Login_pojo;

public class LoginValidate {
	String result;
	static ArrayList<Player> player;
	public String LoginCheckCreds (Login_pojo login){
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String username = login.getUsername();
		String password = login.getPassword();
		
		String hql = "from Player where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		player = (ArrayList<Player>) query.list();	
		
		if (player.size()>0){
			result = "You are logged in successfully";
		}
		else {
			result = "Your credentials are wrong";
		}
		
		return result;
		
	}
}
