package org.mbweb.fss.data_access.TestMains;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.mbweb.fss.data_access.model.Horse;
import org.mbweb.fss.data_access.model.Trainer;
import org.mbweb.fss.data_access.util.HibernateUtil;

public class TestCrud {

	static ArrayList<Horse> testHorsesArray;
	
	public static void main(String[] args) {
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//need to get trainerId to update Horse table
		Long trainerid = (long) 1;
		Trainer trainer = (Trainer) session.get(Trainer.class, trainerid);
		System.out.println(trainer.getFirstName());
		//adding ten horses with trainerId = 1
		for (int i=0; i<10; i++) {
			Horse horse = new Horse();
			horse.setName("TestInsertHorse" + i);		
			horse.setTrainer(trainer);
			session.save(horse);
		}
		session.getTransaction().commit();
		
		//after committing a transaction the session closes
		session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//get the horses just inserted
		String hql = "from Horse where name like :testHorses";
		String testHorses = "TestInsertHorse";
		Query query = session.createQuery(hql);
		query.setParameter("testHorses", "%" + testHorses + "%");
		testHorsesArray = (ArrayList<Horse>) query.list();	
		System.out.println("Size of list result is " + testHorsesArray.size());

		int size = testHorsesArray.size();
		//updating horses recently inserted
		for (int u=0; u<size; u++){
			String horseName = (String) testHorsesArray.get(u).getName();
			System.out.println(horseName);
			long horseid = (long) testHorsesArray.get(u).getId();
			Horse horseUpdate = (Horse) session.get(Horse.class, horseid);
			horseUpdate.setName(horseName + "updated");
			session.update(horseUpdate);
			String horseNameUpdated = (String) testHorsesArray.get(u).getName();
			System.out.println(horseNameUpdated);
		}
		session.getTransaction().commit();
		session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		//deleting horses recently inserted
		for (int j=0; j<size; j++) {
			long horseid = (long) testHorsesArray.get(j).getId();
			System.out.println(horseid);
			Horse horseDelete = (Horse) session.get(Horse.class, horseid);
			session.delete(horseDelete);
		}
		session.getTransaction().commit();
		
		//list out all remaining horses
		session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query2 = session.createQuery("from Horse");
		ArrayList<Horse> allHorses = (ArrayList<Horse>) query2.list();
		
		for (int a=0; a<allHorses.size(); a++){
			long horseid = (long) allHorses.get(a).getId();
			Horse horse = (Horse) session.get(Horse.class, horseid);
			System.out.println(horse.getName());
		}
		System.exit(0);
	}
	
	

}
