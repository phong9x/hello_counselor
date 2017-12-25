/*
 * Created on 29 Mar 2017 ( Time 09:42:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Persistent class for entity stored in table "inquiry"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="inquiry", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="InquiryEntity.countAll", query="SELECT COUNT(x) FROM InquiryEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class InquiryEntity implements Serializable {

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
    @Column(name="answer", length=255)
    private String     answer       ;

    @Column(name="answer_user_id")
    private Integer    answerUserId ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="question", nullable=false, length=255)
    private String     question     ;

    @Column(name="status", nullable=false)
    private Integer    status       ;

    @Column(name="type_user", nullable=false)
    private Integer    typeUser     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Column(name="category_id")
    private Integer    categoryId   ;

    @Column(name="ask_email", length=255)
    private String     askEmail     ;

	// "askUserId" (column "ask_user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="ask_user_id", referencedColumnName="id")
    private UserEntity user        ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public InquiryEntity() {
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
    //--- DATABASE MAPPING : answer ( VARCHAR ) 
    public void setAnswer( String answer ) {
        this.answer = answer;
    }
    public String getAnswer() {
        return this.answer;
    }

    //--- DATABASE MAPPING : answer_user_id ( INT ) 
    public void setAnswerUserId( Integer answerUserId ) {
        this.answerUserId = answerUserId;
    }
    public Integer getAnswerUserId() {
        return this.answerUserId;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : question ( VARCHAR ) 
    public void setQuestion( String question ) {
        this.question = question;
    }
    public String getQuestion() {
        return this.question;
    }

    //--- DATABASE MAPPING : status ( INT ) 
    public void setStatus( Integer status ) {
        this.status = status;
    }
    public Integer getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : type_user ( INT ) 
    public void setTypeUser( Integer typeUser ) {
        this.typeUser = typeUser;
    }
    public Integer getTypeUser() {
        return this.typeUser;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    //--- DATABASE MAPPING : category_id ( INT ) 
    public void setCategoryId( Integer categoryId ) {
        this.categoryId = categoryId;
    }
    public Integer getCategoryId() {
        return this.categoryId;
    }

    //--- DATABASE MAPPING : ask_email ( VARCHAR ) 
    public void setAskEmail( String askEmail ) {
        this.askEmail = askEmail;
    }
    public String getAskEmail() {
        return this.askEmail;
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


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(answer);
        sb.append("|");
        sb.append(answerUserId);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(question);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(typeUser);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(categoryId);
        sb.append("|");
        sb.append(askEmail);
        return sb.toString(); 
    } 

}