/*
 * Created on 27 Apr 2017 ( Time 16:45:38 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for entity stored in table "notification"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="notification", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="NotificationEntity.countAll", query="SELECT COUNT(x) FROM NotificationEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class NotificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="title", length=200)
    private String     title        ;

    @Column(name="content", length=500)
    private String     content      ;

    @Column(name="landing_page", length=100)
    private String     landingPage  ;

    @Column(name="total_push", nullable=false)
    private Integer    totalPush    ;

    @Column(name="open_app", nullable=false)
    private Integer    openApp      ;

    @Column(name="total_push_success", nullable=false)
    private Integer    totalPushSuccess ;

    @Column(name="type")
    private Short      type         ;

    @Column(name="type_push")
    private Short      typePush     ;

    @Column(name="information", length=200)
    private String     information  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_push_date")
    private Date       startPushDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="notification", targetEntity=UserNotificationEntity.class)
    private List<UserNotificationEntity> listOfUserNotification;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;


	@Transient
    private boolean    isRead;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public NotificationEntity() {
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
    //--- DATABASE MAPPING : title ( VARCHAR ) 
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    //--- DATABASE MAPPING : content ( VARCHAR ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : landing_page ( VARCHAR ) 
    public void setLandingPage( String landingPage ) {
        this.landingPage = landingPage;
    }
    public String getLandingPage() {
        return this.landingPage;
    }

    //--- DATABASE MAPPING : total_push ( INT ) 
    public void setTotalPush( Integer totalPush ) {
        this.totalPush = totalPush;
    }
    public Integer getTotalPush() {
        return this.totalPush;
    }

    //--- DATABASE MAPPING : open_app ( INT ) 
    public void setOpenApp( Integer openApp ) {
        this.openApp = openApp;
    }
    public Integer getOpenApp() {
        return this.openApp;
    }

    //--- DATABASE MAPPING : total_push_success ( INT ) 
    public void setTotalPushSuccess( Integer totalPushSuccess ) {
        this.totalPushSuccess = totalPushSuccess;
    }
    public Integer getTotalPushSuccess() {
        return this.totalPushSuccess;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    //--- DATABASE MAPPING : type_push ( SMALLINT ) 
    public void setTypePush( Short typePush ) {
        this.typePush = typePush;
    }
    public Short getTypePush() {
        return this.typePush;
    }

    //--- DATABASE MAPPING : information ( VARCHAR ) 
    public void setInformation( String information ) {
        this.information = information;
    }
    public String getInformation() {
        return this.information;
    }

    //--- DATABASE MAPPING : start_push_date ( DATETIME ) 
    public void setStartPushDate( Date startPushDate ) {
        this.startPushDate = startPushDate;
    }
    public Date getStartPushDate() {
        return this.startPushDate;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfUserNotification( List<UserNotificationEntity> listOfUserNotification ) {
        this.listOfUserNotification = listOfUserNotification;
    }
    public List<UserNotificationEntity> getListOfUserNotification() {
        return this.listOfUserNotification;
    }

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(title);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(landingPage);
        sb.append("|");
        sb.append(totalPush);
        sb.append("|");
        sb.append(openApp);
        sb.append("|");
        sb.append(totalPushSuccess);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(typePush);
        sb.append("|");
        sb.append(information);
        sb.append("|");
        sb.append(startPushDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
