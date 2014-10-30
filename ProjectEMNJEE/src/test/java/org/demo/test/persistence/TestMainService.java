package test.java.org.demo.test.persistence;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.bean.jpa.ParticipationsEntity;
import main.java.org.demo.persistence.services.EventsPersistence;
import main.java.org.demo.persistence.services.jpa.EventsPersistenceJPA;
import main.java.org.demo.service.MainService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMainService {

	
	private static final String vMotDePasse = "toto";
	private static final String vEmail = "toto@toto.fr";
	private MainService mainService;
	private EventsEntity eventsEntity;
	private OrganizersEntity organizersEntity;
	private ParticipationsEntity participationsEntity;
	List<EventsEntity> listeEventsEntities;
	List<OrganizersEntity> listeOrganizersEntites;
	List<ParticipationsEntity> listeParticipationsEntities;
	
	
	
	@Before
	public void setUp(){
		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("organizer@organizer.fr");
		organizersEntity.setPassword("organizer");
		
		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("participation@participation.fr");
		participationsEntity.setNom("nomPart");
		participationsEntity.setPrenom("prenomPart");
		participationsEntity.setSociete("socPart");
		
		
		eventsEntity = new EventsEntity();
		eventsEntity.setAdresse("toto");
		eventsEntity.setDateDebut(new Date());
		eventsEntity.setDateFin(new Date());
		eventsEntity.setHeureDebut(new Date());
		eventsEntity.setHeureFin(new Date());
		eventsEntity.setName("event");
		eventsEntity.setPublication(1);
		
	}
	
	@Test
	public void testGetPublicEventList(){
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
//		EntityManager em = emf.createEntityManager();
//		Query q = em.createNamedQuery("EventsEntity.getAllPublished");
//		@SuppressWarnings("unchecked")
//		List<EventsEntity> results = q.getResultList();
//		if(results !=null && !results.isEmpty()){
//			for(EventsEntity vEventsEntity : results){
//				
//			}
//		}
	}
	
	@Test
	public void testCheckEventIdValidity(){
		
	}
	
	@Test
	public void testGetEvent(){
		
	}
	
	@Test
	public void testCheckEventSubscribe(){
		
	}
	
	@Test
	public void testGetCryptedPassword(){
		
	}
	
	@Test
	public void testCheckLogin(){
		
	}
	
	@Test
	public void testCheckUserExist(){
		
	}
	
	@Test
	public void testValidateSubscribe(){
		
	}
	
	@Test
	public void testCheckValidSession(){
		
	}
	
	@Test
	public void testGetUserEvents(){
		
	}
	
	@Test
	public void testValidateNewEvent(){
		
	}
	
	@Test
	public void testRemoveEvent(){
		
	}
	
	@Test
	public void testIsOwner(){
		
	}
	
	@Test
	public void testPublishEvent(){
		
	}
	
	@After
	public void tearDown(){
		
	}
}
