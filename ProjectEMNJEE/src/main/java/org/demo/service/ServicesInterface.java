package main.java.org.demo.service;

import java.util.List;

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.bean.jpa.ParticipationsEntity;

public interface ServicesInterface {

	/**
	 * Check if the informations contained in the session are correct.
	 * @param username - The username id
	 * @param cryptedPwd - The crypted password
	 * @return True if the crypted password (the token) correspond to the user.
	 */
	public boolean checkValidSession(String uid);
	
	/**
	 * Retrieve the crypted password of the user
	 * @param usernameId - The user id
	 * @return The crypted password
	 */
	public String getCryptedPassword(String usernameId);
	
	/**
	 * Called when user is trying to connect. 
	 * @param uname - The username
	 * @param pwd - The Password (in clear)
	 * @return True if uname is in database and pwd is valid.
	 */
	public Integer checkLogin(String uname, String pwd);
	
	/**
	 * Check if there is no existing user with the same username in DB and if the uname is correct, the
	 * user is inserted in DB
	 * @param uname - The username
	 * @param pwd - the user password (in clear)
	 * @return true if the new user was correctly inserted in DB.
	 */
	public Integer validateSubscribe(String uname, String pwd);
	
	/**
	 * Retrieve the list of published events from Db .
	 * @return An ArrayList of published EventsEntity.
	 */
	public  List<EventsEntity> getPublicEventList();
	
	/**
	 * Check if the id correspond to a valid Integer value. Don't check if the event exists.
	 * @param id - The Raw event ID from the request
	 * @return True if the id corresponds to a valid Event.
	 */
	public  boolean checkEventIdValidity(String id);
	
	/**
	 * Check if params are valid.
	 * If so, the subscriber is added in DB and True is returned;
	 * @param eventId - The event Id
	 * @param email - The email
	 * @param fname - The first name
	 * @param name - The name
	 * @param company - The name of the company
	 * @return boolean
	 */
	public boolean checkEventSubscribe(int eventId, String email,String fname, String name, String company);
	
	/**
	 * Retrieve Event from DB with an ID.
	 * @param id - The supposed ID of the event.
	 * @return null if the event is not in DB, True otherwise.
	 */
	public EventsEntity getEvent(int id);
	
	/**
	 * Return the events list of the user. (published or not).
	 * @param usernameId - The ID of the user.
	 * @return An ArayList of EventsEntity, null if the user have no events.
	 */
	public List<EventsEntity> getUserEvents(int usernameId);
	
	/**
	 * Check if all the params are correct and if so, the new event is created and stored in DB
	 * @param userId - The Id of the user who created the event
	 * @param name - The name of the event
	 * @param addr - The address of the event
	 * @param begin - The begining date and hour of the event
	 * @param end - The ending date and hour of the event
	 * @param published - True if the event is public
	 * @return True if the event was correctly added to the database
	 */
	public boolean validateNewEvent(int userId, String name, String addr,
			String beginDate, String endDate, String beginHour, String endHour, int published);		;
	
	/**
	 * Check if the user is the owner of the event.
	 * @param eventId - The event Id
	 * @param userId - The user Id
	 * @return True if the user is the owner of the event.
	 */
	public boolean isOwner(int eventId, int userId);	
	
	public  List<ParticipationsEntity> getParticipationList(int eventId);
	public boolean validateUpdateEvent(int eventId,  String name, String addr,
			String beginDate, String endDate, String beginHour, String endHour, int published);
	
}
