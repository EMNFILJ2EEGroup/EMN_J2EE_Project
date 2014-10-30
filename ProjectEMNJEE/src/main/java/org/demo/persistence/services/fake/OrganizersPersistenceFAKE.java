/*
 * Created on 30 oct. 2014 ( Time 13:59:50 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package main.java.org.demo.persistence.services.fake;

import java.util.List;
import java.util.Map;

import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.persistence.commons.fake.GenericFakeService;
import main.java.org.demo.persistence.services.OrganizersPersistence;

/**
 * Fake persistence service implementation ( entity "Organizers" )
 *
 * @author Telosys Tools Generator
 */
public class OrganizersPersistenceFAKE extends GenericFakeService<OrganizersEntity> implements OrganizersPersistence {

	public OrganizersPersistenceFAKE () {
		super(OrganizersEntity.class);
	}
	
	protected OrganizersEntity buildEntity(int index) {
		OrganizersEntity entity = new OrganizersEntity();
		// Init fields with mock values
		entity.setId( nextInteger() ) ;
		entity.setEmail( nextString() ) ;
		entity.setPassword( nextString() ) ;
		return entity ;
	}
	
	
	public boolean delete(OrganizersEntity entity) {
		log("delete ( OrganizersEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(OrganizersEntity entity) {
		log("insert ( OrganizersEntity : " + entity + ")" ) ;
	}

	public OrganizersEntity load( Integer id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<OrganizersEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<OrganizersEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<OrganizersEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public OrganizersEntity save(OrganizersEntity entity) {
		log("insert ( OrganizersEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<OrganizersEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
