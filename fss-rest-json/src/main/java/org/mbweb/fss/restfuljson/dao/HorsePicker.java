package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.model.Player_horse;
import org.mbweb.fss.data_access.model.SessionToken;
import org.mbweb.fss.data_access.util.HibernateUtil;
import org.mbweb.fss.restfuljson.model.HorsePicker_pojo;

public class HorsePicker {

	
	public String SelectHorses(HorsePicker_pojo horsePicker){
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String sessionTokenHql = "from SessionToken where session = :sessionToken";
		String sessionToken = horsePicker.getSessionToken();
		Query query = session.createQuery(sessionTokenHql);
		query.setParameter("sessionToken", sessionToken);
		ArrayList<SessionToken> loggedInSession = (ArrayList<SessionToken>) query.list();
		long playerId = loggedInSession.get(0).getPlayerId();
		
		
		
		ArrayList<String> horsesSelected = horsePicker.getSelectedHorses();
		int size = horsesSelected.size();
		for (int i=0; i<size; i++){
			Player_horse player_horse = new Player_horse();
			player_horse.setActive(true);
			String selectedHorseString = horsesSelected.get(i);
			long selectedHorseLong = Integer.parseInt(selectedHorseString);
			Horse horse = (Horse) session.get(Horse.class, selectedHorseLong);
			player_horse.setHorseId(horse);;
			Player player = (Player) session.get(Player.class, playerId);
			player_horse.setPlayerId(player);
			session.save(player_horse);
		}
		session.getTransaction().commit();
		
		return "success";
	}
}
