/*
 * Created on 23 Mar 2017 ( Time 11:13:03 )
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
 * Persistent class for entity stored in table "question"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="question", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="QuestionEntity.countAll", query="SELECT COUNT(x) FROM QuestionEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class QuestionEntity implements Serializable {

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
    @Column(name="order_number", nullable=false)
    private Integer    orderNumber  ;

    @Column(name="content", nullable=false, length=500)
    private String     content      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "questionaireId" (column "questionaire_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="questionaire_id", referencedColumnName="id")
    private QuestionnaireEntity questionnaire;

	@JsonIgnore
    @OneToMany(mappedBy="question", targetEntity=TestResultEntity.class)
    private List<TestResultEntity> listOfTestResult;

	@JsonIgnore
    @OneToMany(mappedBy="question", targetEntity=AnswerEntity.class)
    private List<AnswerEntity> listOfAnswer;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public QuestionEntity() {
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
    //--- DATABASE MAPPING : order_number ( INT ) 
    public void setOrderNumber( Integer orderNumber ) {
        this.orderNumber = orderNumber;
    }
    public Integer getOrderNumber() {
        return this.orderNumber;
    }

    //--- DATABASE MAPPING : content ( VARCHAR ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
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
    public void setQuestionnaire( QuestionnaireEntity questionnaire ) {
        this.questionnaire = questionnaire;
    }
    public QuestionnaireEntity getQuestionnaire() {
        return this.questionnaire;
    }

    public void setListOfTestResult( List<TestResultEntity> listOfTestResult ) {
        this.listOfTestResult = listOfTestResult;
    }
    public List<TestResultEntity> getListOfTestResult() {
        return this.listOfTestResult;
    }

    public void setListOfAnswer( List<AnswerEntity> listOfAnswer ) {
        this.listOfAnswer = listOfAnswer;
    }
    public List<AnswerEntity> getListOfAnswer() {
        return this.listOfAnswer;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(orderNumber);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
