package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.model.SessionToken;
import org.mbweb.fss.data_access.util.HibernateUtil;
import org.mbweb.fss.restfuljson.model.Login_pojo;
import java.util.UUID;


public class LoginValidate {
	String message;
	String sessionTokenValue;
	
	static ArrayList<Player> player;
	public String LoginCheckCreds (Login_pojo login){
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String username = login.getUsername();
		String password = login.getPassword();
		
		String hql = "from Player where username = :username and password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		player = (ArrayList<Player>) query.list();	
		
		if (player.size()>0){
			sessionTokenValue = UUID.randomUUID().toString();
			SessionToken sessionToken = new SessionToken();
			Long playerId = player.get(0).getId();
			sessionToken.setSession(sessionTokenValue);
			sessionToken.setPlayerId(playerId);
			session.save(sessionToken);
			session.getTransaction().commit();
			
		}
		else {
			sessionTokenValue = null;
		}
		
		
		return sessionTokenValue;
		
	}
}
