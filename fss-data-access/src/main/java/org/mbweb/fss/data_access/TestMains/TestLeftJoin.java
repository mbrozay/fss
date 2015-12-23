package org.mbweb.fss.data_access.TestMains;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Player_horse;
import org.mbweb.fss.data_access.util.HibernateUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestLeftJoin {

	public static void main(String[] args) throws JsonProcessingException {
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("Select new map (ph.id as playerhorseid, hid.name as horsename) from Player_horse as ph right outer join ph.horseId as hid with ph.playerId.id = 2");
		//	Query query = session.createQuery("from Player_horse");
		
		
		List<Object[]> allPlayerHorse = (List<Object[]>) query.list();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		String jacksonJson = mapper.writeValueAsString(allPlayerHorse);
		System.out.println(jacksonJson);
		
/*		for (Object[] aRow : allPlayerHorse) {
		Player_horse ph = (Player_horse) aRow[0];
		Horse horse = (Horse) aRow[1];
		System.out.println(horse.getName());
		
			
			//System.out.println("playerHorseId" + " " + playerHorsePlayer + " " + playerHorseHorse);
			
		} */

	}

}
