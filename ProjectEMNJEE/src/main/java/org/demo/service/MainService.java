/**
 * 
 */
package main.java.org.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.derby.tools.sysinfo;
import org.hibernate.Hibernate;

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.bean.jpa.ParticipationsEntity;
import main.java.org.demo.persistence.PersistenceServiceProvider;
import main.java.org.demo.persistence.services.EventsPersistence;
import main.java.org.demo.persistence.services.OrganizersPersistence;
import main.java.org.demo.persistence.services.ParticipationsPersistence;
import main.java.org.demo.persistence.services.jpa.EventsPersistenceJPA;
import main.java.org.demo.persistence.services.jpa.OrganizersPersistenceJPA;

/**
 * @author morin
 *
 */
public class MainService implements ServicesInterface{

	public MainService() { }

	@Override
	public  List<EventsEntity> getPublicEventList() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("EventsEntity.getAllPublished");
		@SuppressWarnings("unchecked")
		List<EventsEntity> results = q.getResultList ();
		return results;
	}

	@Override
	public  boolean checkEventIdValidity(String id) {
		@SuppressWarnings("unused")
		int intID;
		try {
			intID = Integer.parseInt(id);		
		}catch (NumberFormatException e){
			return false;
		}
		return true;		
	}

	@Override
	public EventsEntity getEvent(int id) {
		EventsEntity event = new EventsPersistenceJPA().load(id);
		return event;
	}	
	
	@Override
	public boolean checkEventSubscribe(int eventId, String email,String fname, String name, String company) {

		EventsEntity events = this.getEvent(eventId);
		if (events == null ){
			return false;
		}else {
			//Vérification du mail
			Pattern patern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
			Matcher matcher = patern.matcher(email);
			if (fname == null || name == null || matcher.matches()== false){
				return false;
			}else{
				ParticipationsEntity participation =new ParticipationsEntity();
				participation.setEvents(events);
				participation.setEmail(email);
				participation.setNom(name);
				participation.setPrenom(fname);
				if (company != null){
					participation.setSociete(company);
				}
				ParticipationsPersistence provider = PersistenceServiceProvider.getService(ParticipationsPersistence.class);
				provider.insert(participation);
				return true;
			}			
		}
	}

	@Override
	public String getCryptedPassword(String usernameId) {
		OrganizersPersistenceJPA jpaEvent= new OrganizersPersistenceJPA();		
		String res = jpaEvent.load(Integer.parseInt(usernameId)).getPassword();
		return res;
	}

	@Override
	public Integer checkLogin(String uname, String pwd) {
		Pattern patern = Pattern.compile("(^[_a-z0-9-]+(\\.[_a-z0-9-]+)*)@([a-z0-9-]+)(\\.[a-z0-9-]+)+$");
		Matcher matcher = patern.matcher(uname);
		if (pwd == null || matcher.matches()== false){
			return null;
		}else{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
			EntityManager em = emf.createEntityManager();
			Query q = em.createNamedQuery("OrganizersEntity.getUserByLogin");
			q.setParameter("uname", uname);
			q.setParameter("pwd", pwd);
			List<OrganizersEntity> res = q.getResultList();
			if(res.isEmpty()) return null;
			else return res.get(0).getId();
		}
	}

	public boolean checkUserExist(String uname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("OrganizersEntity.getUserByUsername");
		q.setParameter("uname", uname);
		List<OrganizersEntity> res = q.getResultList();
		if(res.isEmpty()) return false;
		else return true;
	}
	
	@Override
	public Integer validateSubscribe(String uname, String pwd) {
		Pattern patern = Pattern.compile("(^[_a-z0-9-]+(\\.[_a-z0-9-]+)*)@([a-z0-9-]+)(\\.[a-z0-9-]+)+$");
		Matcher matcher = patern.matcher(uname);
		if (checkUserExist(uname) || pwd == null || matcher.matches()== false){
			return null;
		}else {
			OrganizersPersistence provider = PersistenceServiceProvider.getService(OrganizersPersistence.class);
			OrganizersEntity organizer = new OrganizersEntity();
			organizer.setEmail(uname);
			organizer.setPassword(pwd);
			provider.insert(organizer);
			
			/*OrganizersEntity usr = new OrganizersEntity();
			usr.setEmail(un);
			usr.setPassword(pwd);
			OrganizersPersistence provider = PersistenceServiceProvider.getService(OrganizersPersistence.class);
			provider.save(usr);*/
			return organizer.getId();
		}
	}

	@Override
	public boolean checkValidSession(String uid) {
		System.out.println("uid="+uid);
		if(uid != null) 
			if(!uid.equalsIgnoreCase(""))
				return true;
		return false;
	}

	@Override
	public List<EventsEntity> getUserEvents(int usernameId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("EventsEntity.getAllByUserId");
		q.setParameter("uid", usernameId);
		List<EventsEntity> res = q.getResultList();
		if(res.isEmpty()) return null;
		else return res;
		/*
		OrganizersPersistence provider = PersistenceServiceProvider.getService(OrganizersPersistence.class);		
		OrganizersEntity organizer=  provider.load(usernameId);
		Hibernate.initialize(organizer);
		List<EventsEntity> events = organizer.getListOfEvents();
		return events;*/
	}

	@Override
	public boolean validateNewEvent(int userId, String name, String addr,
			String beginDate, String endDate, String beginHour, String endHour, int published) {
		if (name == null || addr == null || beginDate == null || endDate == null|| beginHour == null || endHour == null)
			return false;
		
		EventsPersistence eventProvider = PersistenceServiceProvider.getService(EventsPersistence.class);
		OrganizersPersistence userProvider = PersistenceServiceProvider.getService(OrganizersPersistence.class);
		
		EventsEntity event = new EventsEntity();
		event.setName(name);
		event.setAdresse(addr);
		
		OrganizersEntity user = userProvider.load(userId);
		if (user == null)
			return false;
		event.setOrganizers(user);
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
		Date begDateSDF;
		Date endDateSDF;
		Date begHourSDF;
		Date endHourSDF;
		try {
			begDateSDF = sdfDate.parse(beginDate);
			endDateSDF = sdfDate.parse(endDate);
			begHourSDF = sdfHeure.parse(beginHour);
			endHourSDF = sdfHeure.parse(endHour);
		}
		catch (ParseException e) { return false; }
		if (begDateSDF.after(endDateSDF) || (begDateSDF.compareTo(endDateSDF)==0 && begHourSDF.after(endHourSDF))) 
			return false;
		event.setDateDebut(begDateSDF);
		event.setDateFin(endDateSDF);
		event.setHeureDebut(begHourSDF);
		event.setHeureFin(endHourSDF);
		event.setPublication(published);
		
		eventProvider.insert(event);
		return true;
	}

	@Override
	public boolean removeEvent(int eventId, int userId) {
		EventsEntity event=this.getEvent(eventId);
		EventsPersistenceJPA jpa = new EventsPersistenceJPA();
		return jpa.delete(event);
	}

	@Override
	public boolean isOwner(int eventId, int userId) {
		EventsEntity event=this.getEvent(eventId);		
		return (event.getOrganizers().getId()==userId);
	}

	@Override
	public void publishEvent(int eventId) {
		EventsEntity event=this.getEvent(eventId);	
		event.setPublication(1);
	}

	@Override
	public  List<ParticipationsEntity> getParticipationList(int eventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query q = em.createNamedQuery("ParticipationsEntity.getAllByEventId");
		q.setParameter("uid", eventId);
		@SuppressWarnings("unchecked")
		List<ParticipationsEntity> results = q.getResultList ();
		return results;
	}
	
	@Override
	public boolean validateUpdateEvent(int eventId, String name, String addr,
			String beginDate, String endDate, String beginHour, String endHour, int published) {
		if (name == null || addr == null || beginDate == null || endDate == null|| beginHour == null || endHour == null)
			return false;
		
		EventsPersistence eventProvider = PersistenceServiceProvider.getService(EventsPersistence.class);
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfHeure = new SimpleDateFormat("HH:mm");
		EventsEntity event = new EventsPersistenceJPA().load(eventId);
		
		if(event == null) return false;
		try {
			event.setName(name);
			event.setAdresse(addr);
			
			Date begDateSDF;
			Date endDateSDF;
			Date begHourSDF;
			Date endHourSDF;
			begDateSDF = (sdfDate.parse(beginDate));
			endDateSDF = (sdfDate.parse(endDate));
			begHourSDF = (sdfHeure.parse(beginHour));
			endHourSDF = (sdfHeure.parse(endHour));
			event.setDateDebut(begDateSDF);
			event.setDateFin(endDateSDF);
			event.setHeureDebut(begHourSDF);
			event.setHeureFin(endHourSDF);
			event.setPublication(published);
			if (begDateSDF.after(endDateSDF) || (begDateSDF.compareTo(endDateSDF)==0 && begHourSDF.after(endHourSDF))) 
				return false;
			eventProvider.save(event);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
