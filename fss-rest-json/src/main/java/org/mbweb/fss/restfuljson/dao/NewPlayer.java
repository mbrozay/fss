package org.mbweb.fss.restfuljson.dao;

import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.util.HibernateUtil;
import org.mbweb.fss.restfuljson.model.Player_pojo;

public class NewPlayer {
	String result;

	public String PlayerSignup(Player player) {
		String firstName = player.getFirstName();
	Session session = null;
	session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	
	session.save(player);
	session.getTransaction().commit();
	result = "Success";
	return result;
	
	}
	
}
