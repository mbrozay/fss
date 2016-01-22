package org.mbweb.fss.restfuljson.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player;
import org.mbweb.fss.data_access.model.Player_horse;
import org.mbweb.fss.data_access.model.SessionToken;
import org.mbweb.fss.data_access.model.Trainer;
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
		Player player = (Player) session.get(Player.class, playerId);
		
	
		ArrayList<String> horsesSelected = horsePicker.getSelectedHorses();
		int size = horsesSelected.size();
		
		List<Long> horsesSelectedLongList = new ArrayList<Long>();
		for (int i=0; i<size; i++){
			String player_horseIdString1 = horsesSelected.get(i);
			long selectedHorseLong1 = Integer.parseInt(player_horseIdString1);
			horsesSelectedLongList.add(selectedHorseLong1);
		}
		if (horsesSelectedLongList.size() == 0) {
			horsesSelectedLongList.add((long) 0);
		}
		/*set horses not picked to active = false
		 *update player_horse set active = 'false' where playerId = :playerId and active = 'true'
		 *and horseId not in (horseSelectedId);
		 */
		String updateRemovedHorsesHql = "UPDATE Player_horse ph set ph.active = :statusBoolean "  + 
	             "WHERE ph.horseId.id not in (:horsesSelectedLongList) and ph.playerId.id = :playerId";
	Query updateRemovedHorsesquery = session.createQuery(updateRemovedHorsesHql);
	updateRemovedHorsesquery.setParameter("statusBoolean", false);
	updateRemovedHorsesquery.setParameter("playerId", player.getId());
	updateRemovedHorsesquery.setParameterList("horsesSelectedLongList", horsesSelectedLongList);
	int result = updateRemovedHorsesquery.executeUpdate();
	session.getTransaction().commit();
	System.out.println("Rows affected: " + result);

	/*Check which horse remain unchanged - update timestamp
		 * Select * from player_horse where id in (horseSelectedId) playerId = :playerId and active = 'true'
		 * Remove from array
		 * */
		
	session = null;
	session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	String unchangedHorsesHql = "from Player_horse ph where ph.horseId.id in (:horsesSelectedLongList)"
			+ "and ph.playerId.id = :playerId and ph.active = :statusBoolean";
	Query unchangedHorsesQuery = session.createQuery(unchangedHorsesHql);
	unchangedHorsesQuery.setParameter("statusBoolean", true);
	unchangedHorsesQuery.setParameterList("horsesSelectedLongList", horsesSelectedLongList);
	unchangedHorsesQuery.setParameter("playerId", player.getId());
	
	ArrayList<Player_horse> unchangedHorsesArray = (ArrayList<Player_horse>) unchangedHorsesQuery.list();	
	
	int unchangedHorsesArraySize = unchangedHorsesArray.size();
	for (int i=0; i<unchangedHorsesArraySize; i++) {

		Horse horse = (Horse) session.get(Horse.class, unchangedHorsesArray.get(i).getHorseId().getId());
		long horsesSelectedLongListtoRemove = horse.getId();
		horsesSelectedLongList.remove(horsesSelectedLongListtoRemove);		
	}
	
		/*Remaining horses in array
		 * Insert into player_horse playerid, horseid, true
		 * */
		int horsestooInsertArraySize = horsesSelectedLongList.size();
		if (horsestooInsertArraySize > 0) {
		if (horsesSelectedLongList.get(0) != ((long) 0)) {
			
		for (int j=0; j<horsestooInsertArraySize; j++){
			Player_horse player_horse = new Player_horse();
			player_horse.setActive(true);
			Horse horse = (Horse) session.get(Horse.class, horsesSelectedLongList.get(j));
			player_horse.setHorseId(horse);
			player_horse.setPlayerId(player);
			session.save(player_horse);
		}
		session.getTransaction().commit();
		}
		}
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
