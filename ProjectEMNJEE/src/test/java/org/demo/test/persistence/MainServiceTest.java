package test.java.org.demo.test.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.bean.jpa.ParticipationsEntity;
import main.java.org.demo.controller.MainEventController;
import main.java.org.demo.persistence.PersistenceServiceProvider;
import main.java.org.demo.persistence.services.EventsPersistence;
import main.java.org.demo.persistence.services.OrganizersPersistence;
import main.java.org.demo.persistence.services.ParticipationsPersistence;
import main.java.org.demo.service.MainService;
import main.java.org.demo.service.ServicesInterface;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainServiceTest {

	private static final Integer publication = 1;
	private static final Integer idNonExistant = 9999999;
	private MainService mainService;
	private EventsEntity eventsEntity;
	private OrganizersEntity organizersEntity;
	private ParticipationsEntity participationsEntity;
	List<EventsEntity> listeEventsEntities;
	List<OrganizersEntity> listeOrganizersEntites;
	List<ParticipationsEntity> listeParticipationsEntities;
	EventsPersistence eventsPersistence = PersistenceServiceProvider.getService(EventsPersistence.class);
	ParticipationsPersistence participationsPersistence = PersistenceServiceProvider.getService(ParticipationsPersistence.class);
	OrganizersPersistence organizersPersistence = PersistenceServiceProvider.getService(OrganizersPersistence.class);


	@Before
	public void setUp(){
		mainService = new MainService();

	}


	@Test
	public void testGetPublicEventList(){
		if(mainService.getPublicEventList()!=null && !mainService.getPublicEventList().isEmpty()){
			for(EventsEntity ee : mainService.getPublicEventList()){
				assertTrue("Test si on récupère bien seulement les évènements publiés", ee.getPublication().equals(publication));
			}
		}
	}

	@Test
	public void testCheckEventIdValidity(){
		String valeurFausse = "projet";
		String valeurVraie = "12";
		assertFalse(mainService.checkEventIdValidity(valeurFausse));
		assertTrue(mainService.checkEventIdValidity(valeurVraie));
	}

	@Test
	public void testGetEvent() throws ParseException{

		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("orgTest@org.fr");
		organizersEntity.setPassword("orgTest");
		organizersPersistence.insert(organizersEntity);

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");

		eventsEntity = new EventsEntity();
		eventsEntity.setName("eventTest");
		eventsEntity.setAdresse("hihihi");
		eventsEntity.setDateDebut(sdfDate.parse("2014-10-15"));
		eventsEntity.setDateFin(sdfDate.parse("2014-10-16"));
		eventsEntity.setHeureDebut(sdfHeure.parse("12:00"));
		eventsEntity.setHeureFin(sdfHeure.parse("12:00"));
		eventsEntity.setPublication(1);
		eventsEntity.setOrganizers(organizersEntity);
		eventsEntity.setListOfParticipations(null);
		eventsPersistence.insert(eventsEntity);

		assertEquals(eventsEntity.getName(), mainService.getEvent(eventsEntity.getId()).getName());
		assertEquals(eventsEntity.getAdresse(), mainService.getEvent(eventsEntity.getId()).getAdresse());
		assertEquals(eventsEntity.getPublication(), mainService.getEvent(eventsEntity.getId()).getPublication());
		assertNull(mainService.getEvent(idNonExistant));

	}

	@Test
	public void testCheckEventSubscribe(){
		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("orgtest@orgtest.fr");
		organizersEntity.setPassword("orgTest");
		organizersPersistence.insert(organizersEntity);
		
		eventsEntity = new EventsEntity();
		eventsEntity.setName("eventTest");
		eventsEntity.setAdresse("test");
		eventsEntity.setDateDebut(new Date());
		eventsEntity.setDateFin(new Date());
		eventsEntity.setHeureDebut(new Date());
		eventsEntity.setHeureFin(new Date());
		eventsEntity.setPublication(1);
		eventsEntity.setOrganizers(organizersEntity);
		eventsPersistence.insert(eventsEntity);
		
		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("parttest@parttest.fr");
		participationsEntity.setNom("nomPartTest");
		participationsEntity.setPrenom("prenomPartTest");
		participationsEntity.setEvents(eventsEntity);
		assertTrue(mainService.checkEventSubscribe(eventsEntity.getId(), participationsEntity.getEmail(), participationsEntity.getPrenom(), participationsEntity.getNom(), participationsEntity.getSociete()));

		participationsEntity.setEmail("test");
		assertFalse(mainService.checkEventSubscribe(eventsEntity.getId(), participationsEntity.getEmail(), participationsEntity.getPrenom(), participationsEntity.getNom(), participationsEntity.getSociete()));
	}

		@Test
		public void testGetCryptedPassword(){
			organizersEntity = new OrganizersEntity();
			organizersEntity.setEmail("orgTest@orgTest.fr");
			organizersEntity.setPassword("CRYPTED");
			organizersPersistence.insert(organizersEntity);
			assertEquals("CRYPTED",mainService.getCryptedPassword(organizersEntity.getId().toString()));
		}
		
		@Test
		public void testCheckLogin(){	
			organizersEntity = new OrganizersEntity();
			organizersEntity.setEmail("existe@existe.fr");
			organizersEntity.setPassword("existe");
			organizersPersistence.insert(organizersEntity);
			
			assertEquals(organizersEntity.getId(), mainService.checkLogin("existe@existe.fr", "existe"));
			assertNotEquals(idNonExistant, mainService.checkLogin("existepas@existe.fr", "existe"));
			assertNotEquals(organizersEntity.getId(), mainService.checkLogin("existepasdutout@check.fr", "existepas"));
			
		}
		
		@Test
		public void testCheckUserExist(){
			organizersEntity = new OrganizersEntity();
			organizersEntity.setEmail("existemail@existe.fr");
			organizersEntity.setPassword("neSertPasIci");
			organizersPersistence.insert(organizersEntity);
			
			assertTrue(mainService.checkUserExist("existemail@existe.fr"));
			assertFalse(mainService.checkUserExist("nexistepaslemail@enbase.fr"));
		}
		
	//	@Test
	//	public void testValidateSubscribe(){
	//		
	//	}
	//	
	//	@Test
	//	public void testCheckValidSession(){
	//		
	//	}
	//	
	//	@Test
	//	public void testGetUserEvents(){
	//		
	//	}
	//	
	//	@Test
	//	public void testValidateNewEvent(){
	//		
	//	}
	//	
	//	@Test
	//	public void testRemoveEvent(){
	//		
	//	}
	//	
	//	@Test
	//	public void testIsOwner(){
	//		
	//	}
	//	
	//	@Test
	//	public void testPublishEvent(){
	//		
	//	}

	@Test
	public void testGererDonneesBase(){
		// On supprime toutes les données de la base


		List<ParticipationsEntity> listePE = participationsPersistence.loadAll();
		if(listePE!=null && !listePE.isEmpty()){
			for(ParticipationsEntity pe : listePE){
				participationsPersistence.delete(pe.getId());
			}
		}

		List<EventsEntity> listeEE = eventsPersistence.loadAll();
		if(listeEE!=null && !listeEE.isEmpty()){
			for(EventsEntity ee : listeEE){
				eventsPersistence.delete(ee.getId());
			}
		}

		List<OrganizersEntity> listeOE = organizersPersistence.loadAll();
		if(listeOE!=null && !listeOE.isEmpty()){
			for(OrganizersEntity oe : listeOE){
				organizersPersistence.delete(oe.getId());
			}
		}


		// Insertion des utilisateurs/organisateurs
		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("organizer1@organizer1.fr");
		organizersEntity.setPassword("organizer1");
		organizersPersistence.insert(organizersEntity);

		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("organizer2@organizer2.fr");
		organizersEntity.setPassword("organizer2");
		organizersPersistence.insert(organizersEntity);

		// Insertion des évènements		
		eventsEntity = new EventsEntity();
		eventsEntity.setName("event1");
		eventsEntity.setAdresse("toto");
		eventsEntity.setDateDebut(new Date());
		eventsEntity.setDateFin(new Date());
		eventsEntity.setHeureDebut(new Date());
		eventsEntity.setHeureFin(new Date());
		eventsEntity.setPublication(1);
		eventsEntity.setOrganizers(organizersEntity);
		eventsPersistence.insert(eventsEntity);

		eventsEntity = new EventsEntity();
		eventsEntity.setName("event2");
		eventsEntity.setAdresse("tata");
		eventsEntity.setDateDebut(new Date());
		eventsEntity.setDateFin(new Date());
		eventsEntity.setHeureDebut(new Date());
		eventsEntity.setHeureFin(new Date());
		eventsEntity.setPublication(0);
		eventsEntity.setOrganizers(organizersEntity);
		eventsPersistence.insert(eventsEntity);

		// Insertion des participants
		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("participant1@participation1.fr");
		participationsEntity.setNom("nomPart1");
		participationsEntity.setPrenom("prenomPart1");
		participationsEntity.setSociete("socPart1");
		participationsEntity.setEvents(eventsEntity);
		participationsPersistence.insert(participationsEntity);

		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("participant2@participation2.fr");
		participationsEntity.setNom("nomPart2");
		participationsEntity.setPrenom("prenomPart2");
		participationsEntity.setEvents(eventsEntity);
		participationsPersistence.insert(participationsEntity);
	}

	@After
	public void tearDown(){

	}
}
