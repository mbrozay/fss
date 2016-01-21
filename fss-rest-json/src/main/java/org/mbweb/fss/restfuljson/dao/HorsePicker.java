package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;
import java.util.List;

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
		
		List<Long> horsesSelectedLongList = new ArrayList<Long>();
		for (int i=0; i<size; i++){
			String player_horseIdString1 = horsesSelected.get(i);
			long selectedHorseLong1 = Integer.parseInt(player_horseIdString1);
			horsesSelectedLongList.add(selectedHorseLong1);
		}

		String updateRemovedHorsesHql = "UPDATE Player_horse set active = :statusBoolean "  + 
	             "WHERE id NOT IN (2)";
	Query updateRemovedHorsesquery = session.createQuery(updateRemovedHorsesHql);
	updateRemovedHorsesquery.setParameter("statusBoolean", true);
	//updateRemovedHorsesquery.setParameterList("horsesSelectedLongList", horsesSelectedLongList);
	int result = updateRemovedHorsesquery.executeUpdate();
	System.out.println("Rows affected: " + result);
	session.getTransaction().commit();
	

		/*set horses not picked to active = false
		 *update player_horse set active = 'false' where playerId = :playerId and active = 'true'
		 *and horseId not in (horseSelectedId);
		 *remove from array*/
		
		/*Check which horse remain unchanged - update timestamp
		 * Select * from player_horse where id in (horseSelectedId) playerId = :playerId and active = 'true'
		 * Remove from array
		 * */
		
		/*Remaining horses in array
		 * Insert into player_horse playerid, horseid, true
		 * */
		
		
		
		/*int size = horsesSelected.size();
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
		session.getTransaction().commit();*/
		
		return "success";
	}
}
