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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainServiceTest {

	private static final Integer publication = 1;
	private static final Integer idNonExistant = 9999999;
	private static MainService mainService;
	private static EventsEntity eventsEntity;
	private static OrganizersEntity organizersEntity;
	private static ParticipationsEntity participationsEntity;
	private static List<EventsEntity> listeEventsEntities = new ArrayList<EventsEntity>();
	private static List<OrganizersEntity> listeOrganizersEntites = new ArrayList<OrganizersEntity>();
	private static List<ParticipationsEntity> listeParticipationsEntities = new ArrayList<ParticipationsEntity>();
	private static EventsPersistence eventsPersistence = PersistenceServiceProvider.getService(EventsPersistence.class);
	private static ParticipationsPersistence participationsPersistence = PersistenceServiceProvider.getService(ParticipationsPersistence.class);
	private static OrganizersPersistence organizersPersistence = PersistenceServiceProvider.getService(OrganizersPersistence.class);


	@BeforeClass
	public static void setUp(){
		mainService = new MainService();

		// Insertion des utilisateurs/organisateurs pour les tests
		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("organizer1@organizer1.fr");
		organizersEntity.setPassword("organizer1");
		organizersPersistence.insert(organizersEntity);
		listeOrganizersEntites.add(organizersEntity);

		organizersEntity = new OrganizersEntity();
		organizersEntity.setEmail("organizer2@organizer2.fr");
		organizersEntity.setPassword("organizer2");
		organizersPersistence.insert(organizersEntity);
		listeOrganizersEntites.add(organizersEntity);

		// Insertion des évènements	pour les tests
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
		listeEventsEntities.add(eventsEntity);

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
		listeEventsEntities.add(eventsEntity);

		// Insertion des participants pour les tests
		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("participant1@participation1.fr");
		participationsEntity.setNom("nomPart1");
		participationsEntity.setPrenom("prenomPart1");
		participationsEntity.setSociete("socPart1");
		participationsEntity.setEvents(eventsEntity);
		participationsPersistence.insert(participationsEntity);
		listeParticipationsEntities.add(participationsEntity);

		participationsEntity = new ParticipationsEntity();
		participationsEntity.setEmail("participant2@participation2.fr");
		participationsEntity.setNom("nomPart2");
		participationsEntity.setPrenom("prenomPart2");
		participationsEntity.setEvents(eventsEntity);
		participationsPersistence.insert(participationsEntity);
		listeParticipationsEntities.add(participationsEntity);
	}

	@Test
	public void validateUpdateEvent(){
		assertFalse(mainService.validateUpdateEvent(eventsEntity.getId(), "maj", "test", "pasunedate", "2013-05-02", "10:00", "11:00", 0));
	}
	
	@Test
	public void testGetParticipationList(){
		assertNotEquals(new ArrayList<ParticipationsEntity>(), mainService.getParticipationList(eventsEntity.getId()));
	}
	
	@Test
	public void testIsOwner(){
		assertTrue(mainService.isOwner(eventsEntity.getId(), organizersEntity.getId()));
		assertFalse(mainService.isOwner(eventsEntity.getId(), idNonExistant));
	}
	
	@Test
	public void testValidateNewEvent(){
		assertFalse(mainService.validateNewEvent(organizersEntity.getId(), "test", "adresse", "pasunedate", "2014-05-06", "09:00", "12:00", 1));
	}
	
	@Test
	public void testGetUserEvents(){
		assertNotEquals(new ArrayList<EventsEntity>(), mainService.getUserEvents(organizersEntity.getId()));
	}
	
	@Test
	public void testCheckValidSession(){
		assertFalse(mainService.checkValidSession(null));
		assertTrue(mainService.checkValidSession("test"));
	}
	
	@Test
	public void testValidateSubscribe(){
		assertNotNull(mainService.validateSubscribe("organizer3@organizer3.fr", "organizer3"));
		assertNull(mainService.validateSubscribe("organizer2@organizer2.fr", "organizer2"));
	}
	
	@Test
	public void testCheckUserExist(){
		assertTrue(mainService.checkUserExist("organizer1@organizer1.fr"));
		assertFalse(mainService.checkUserExist("nexistepaslemail@enbase.fr"));
	}
	
	@Test
	public void testCheckLogin(){	
		assertEquals(organizersEntity.getId(), mainService.checkLogin("organizer2@organizer2.fr", "organizer2"));
		assertNotEquals(idNonExistant, mainService.checkLogin("existepas@existe.fr", "existe"));
		assertNotEquals(organizersEntity.getId(), mainService.checkLogin("existepasdutout@check.fr", "existepas"));
	}
	
	@Test
	public void testGetCryptedPassword(){
		assertEquals("organizer2",mainService.getCryptedPassword(organizersEntity.getId().toString()));
		assertNotEquals("paslebonmotdepasse",mainService.getCryptedPassword(organizersEntity.getId().toString()));
	}
	
	@Test
	public void testCheckEventSubscribe(){
		assertTrue(mainService.checkEventSubscribe(eventsEntity.getId(), "testpart@testpart.fr", "prenomPart", "nomPart", "societePart"));
		participationsEntity.setEmail("testsansarobase");
		assertFalse(mainService.checkEventSubscribe(eventsEntity.getId(), participationsEntity.getEmail(), participationsEntity.getPrenom(), participationsEntity.getNom(), participationsEntity.getSociete()));
	}
	
	@Test
	public void testGetEvent() throws ParseException{
		assertEquals(eventsEntity.getName(), mainService.getEvent(eventsEntity.getId()).getName());
		assertEquals(eventsEntity.getAdresse(), mainService.getEvent(eventsEntity.getId()).getAdresse());
		assertEquals(eventsEntity.getPublication(), mainService.getEvent(eventsEntity.getId()).getPublication());
		assertNull(mainService.getEvent(idNonExistant));
	}
	
	@Test
	public void testCheckEventIdValidity(){
		String valeurFausse = "projet";
		String valeurVraie = "12";
		assertFalse(mainService.checkEventIdValidity(valeurFausse));
		assertTrue(mainService.checkEventIdValidity(valeurVraie));
	}
	
	@Test
	public void testGetPublicEventList(){
		if(mainService.getPublicEventList()!=null && !mainService.getPublicEventList().isEmpty()){
			for(EventsEntity ee : mainService.getPublicEventList()){
				assertTrue("Test si on récupère bien seulement les évènements publiés", ee.getPublication().equals(publication));
			}
		}
	}

	@AfterClass
	public static void tearDown(){
		// On supprime toutes les données de la base comme si on n'avait pas fait de tests
		Integer idPE = participationsEntity.getId();
		Integer idEE = eventsEntity.getId();
		Integer idOE = organizersEntity.getId();
		participationsPersistence.delete(idPE);
		participationsPersistence.delete(idPE-1);
		participationsPersistence.delete(idPE+1);
		eventsPersistence.delete(idEE);
		eventsPersistence.delete(idEE-1);
		organizersPersistence.delete(idOE);
		organizersPersistence.delete(idOE-1);
		organizersPersistence.delete(idOE+1);
	}
}
