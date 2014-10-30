/*
 * Created on 29 oct. 2014 ( Time 23:42:22 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
// This Bean has a basic Primary Key (not composite) 

package main.java.org.demo.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "ORGANIZERS"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="ORGANIZERS", schema="APP" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="OrganizersEntity.countAll", query="SELECT COUNT(x) FROM OrganizersEntity x" ),
  @NamedQuery ( name="OrganizersEntity.getUserByLogin", query="SELECT o FROM OrganizersEntity o where o.email = :uname and o.password = :pwd"),
  @NamedQuery ( name="OrganizersEntity.getUserByUsername", query="SELECT o FROM OrganizersEntity o where o.email = :uname")
} )
public class OrganizersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="EMAIL", nullable=false, length=255)
    private String     email        ;

    @Column(name="PASSWORD", nullable=false, length=255)
    private String     password     ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="organizers", targetEntity=EventsEntity.class)
    private List<EventsEntity> listOfEvents;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public OrganizersEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : EMAIL ( VARCHAR ) 
    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    //--- DATABASE MAPPING : PASSWORD ( VARCHAR ) 
    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfEvents( List<EventsEntity> listOfEvents ) {
        this.listOfEvents = listOfEvents;
    }
    public List<EventsEntity> getListOfEvents() {
        return this.listOfEvents;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(email);
        sb.append("|");
        sb.append(password);
        return sb.toString(); 
    } 

}
