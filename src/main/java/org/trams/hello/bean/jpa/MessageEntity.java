/*
 * Created on 7 Apr 2017 ( Time 17:33:11 )
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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for entity stored in table "message"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="message", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="MessageEntity.countAll", query="SELECT COUNT(x) FROM MessageEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class MessageEntity implements Serializable {

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
    @Column(name="content", nullable=false)
    private String     content      ;

    @Column(name="type_user")
    private Integer    typeUser     ;
    
    
    @Column(name="is_delete")
    private Short isDelete;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="send_date", nullable=false)
    private Date       sendDate     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="recieve_date")
    private Date       recieveDate  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    
	// "sendUserId" (column "send_user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="send_user_id", referencedColumnName="id")
    private UserEntity user        ;

	@JsonIgnore
    @OneToMany(mappedBy="message", targetEntity=UserReceiveMessageEntity.class)
    private List<UserReceiveMessageEntity> listOfUserReceiveMessage;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public MessageEntity() {
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
    //--- DATABASE MAPPING : content ( TEXT ) 
    
    
    
    public void setContent( String content ) {
        this.content = content;
    }
    public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : type_user ( INT ) 
    public void setTypeUser( Integer typeUser ) {
        this.typeUser = typeUser;
    }
    public Integer getTypeUser() {
        return this.typeUser;
    }

    //--- DATABASE MAPPING : send_date ( DATETIME ) 
    public void setSendDate( Date sendDate ) {
        this.sendDate = sendDate;
    }
    public Date getSendDate() {
        return this.sendDate;
    }

    //--- DATABASE MAPPING : recieve_date ( DATETIME ) 
    public void setRecieveDate( Date recieveDate ) {
        this.recieveDate = recieveDate;
    }
    public Date getRecieveDate() {
        return this.recieveDate;
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
    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setListOfUserReceiveMessage( List<UserReceiveMessageEntity> listOfUserReceiveMessage ) {
        this.listOfUserReceiveMessage = listOfUserReceiveMessage;
    }
    public List<UserReceiveMessageEntity> getListOfUserReceiveMessage() {
        return this.listOfUserReceiveMessage;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        // attribute 'content' not usable (type = String Long Text)
        sb.append(typeUser);
        sb.append("|");
        sb.append(sendDate);
        sb.append("|");
        sb.append(recieveDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}