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

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.bean.jpa.ParticipationsEntity;
import main.java.org.demo.persistence.services.jpa.EventsPersistenceJPA;
import main.java.org.demo.persistence.services.jpa.OrganizersPersistenceJPA;
import main.java.org.demo.persistence.services.jpa.ParticipationsPersistenceJPA;

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
				participation.setEmail(email);
				participation.setEvents(events);
				participation.setNom(name);
				participation.setPrenom(fname);
				if (company != null){
					participation.setSociete(company);
				}
				ParticipationsPersistenceJPA jpa = new ParticipationsPersistenceJPA();
				jpa.insert(participation);
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
		Pattern patern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
		Matcher matcher = patern.matcher(uname);
		if (pwd == null || matcher.matches()== false){
			return null;
		}else{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
			EntityManager em = emf.createEntityManager();
			Query q = em.createQuery ("SELECT * FROM APP.Organizers where email = "+uname+" and password = "+pwd);
			OrganizersEntity res = (OrganizersEntity) q.getSingleResult();

			if (res==null){
				return null;
			}else {
				return res.getId();
			}
		}
	}

	@Override
	public Integer validateSubscribe(String uname, String pwd) {
		Pattern patern = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
		Matcher matcher = patern.matcher(uname);
		if (checkLogin(uname, pwd)== null ||pwd == null || matcher.matches()== false){
			return null;
		}else {
			OrganizersEntity organizers = new OrganizersEntity();
			organizers.setEmail(uname);
			organizers.setPassword(pwd);
			OrganizersPersistenceJPA jpa = new OrganizersPersistenceJPA();
			jpa.insert(organizers);
			
			/*OrganizersEntity usr = new OrganizersEntity();
			usr.setEmail(un);
			usr.setPassword(pwd);
			OrganizersPersistence provider = PersistenceServiceProvider.getService(OrganizersPersistence.class);
			provider.save(usr);*/
			return organizers.getId();
		}
	}

	@Override
	public boolean checkValidSession(String uid) {
		if(uid != null) 
			if(!uid.equalsIgnoreCase(""))
				return true;
		return false;
	}

	@Override
	public List<EventsEntity> getUserEvents(int usernameId) {
		OrganizersPersistenceJPA jpa = new OrganizersPersistenceJPA();
		OrganizersEntity organizer= jpa.load(usernameId);
		List<EventsEntity> events = organizer.getListOfEvents();
		return events;
	}

	@Override
	public boolean validateNewEvent(int userId, String name, String addr,
			String beginDate, String endDate, String beginHour, String endHour, int published) {
		if (name == null || addr == null || beginDate == null || endDate == null|| beginHour == null || endHour == null)
			return false;
		
		EventsEntity event = new EventsEntity();
		event.setName(name);
		event.setAdresse(addr);
		
		OrganizersEntity user = new OrganizersPersistenceJPA().load(userId);
		if (user == null)
			return false;
		event.setOrganizers(user);

		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfHour = new SimpleDateFormat("hh:mm:s");
		Date begDateSDF;
		Date endDateSDF;
		Date begHourSDF;
		Date endHourSDF;
		try {
			begDateSDF = sdfDate.parse(beginDate);
			endDateSDF = sdfDate.parse(endDate);
			begHourSDF = sdfHour.parse(beginDate);
			endHourSDF = sdfHour.parse(endDate);
		}
		catch (ParseException e) { return false; }
		if (begDateSDF.after(endDateSDF) || (begDateSDF.compareTo(endDateSDF)==0 && begHourSDF.after(endHourSDF))) 
			return false;
		event.setDateDebut(begDateSDF);;
		event.setDateFin(begDateSDF);
		event.setPublication(published);
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

	
}
