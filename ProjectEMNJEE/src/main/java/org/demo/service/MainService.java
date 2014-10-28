/**
 * 
 */
package main.java.org.demo.service;

import java.util.Date;
import java.util.List;

import main.java.org.demo.bean.jpa.EventsEntity;

/**
 * @author morin
 *
 */
public class MainService implements ServicesInterface{

	public MainService() { }
	
	@Override
	public  List<EventsEntity> getPublicEventList() {
		//TODO
		return null;
	}
	
	@Override
	public  boolean checkEventIdValidity(String id) {
		//TODO
		return true;
	}
	
	@Override
	public EventsEntity getEvent(int id) {
		//TODO
		return null;
	}
	@Override
	public boolean checkEventSubscribe(int eventId, String email,String fname, String name, String company) {
		//TODO
		return false;
	}

	@Override
	public String getCryptedPassword(String usernameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkLogin(String uname, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateSubscribe(String uname, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkValidSession(int usernameId, String cryptedPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EventsEntity> getUserEvents(int usernameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateNewEvent(int userId, String name, String addr,
			Date begin, Date end, boolean published) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEvent(int eventId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOwner(int eventId, int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void publishEvent(int eventId) {
		// TODO Auto-generated method stub
		
	}
}
