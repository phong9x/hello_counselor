/*
 * Created on 3 May 2017 ( Time 10:24:45 )
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
import javax.persistence.Id;
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
 * Persistent class for entity stored in table "self_diagnosis_results"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="self_diagnosis_results", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="SelfDiagnosisResultsEntity.countAll", query="SELECT COUNT(x) FROM SelfDiagnosisResultsEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class SelfDiagnosisResultsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="questionaire_id", nullable=false)
    private Integer    questionaireId ;

    @Column(name="from_point")
    private Float      fromPoint    ;

    @Column(name="to_point")
    private Float      toPoint      ;

    @Column(name="content_result")
    private String     contentResult ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="type_icon")
    private Short      typeIcon     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="selfDiagnosisResults", targetEntity=UserQuestionaireEntity.class)
    private List<UserQuestionaireEntity> listOfUserQuestionaire;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public SelfDiagnosisResultsEntity() {
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
    //--- DATABASE MAPPING : questionaire_id ( INT ) 
    public void setQuestionaireId( Integer questionaireId ) {
        this.questionaireId = questionaireId;
    }
    public Integer getQuestionaireId() {
        return this.questionaireId;
    }

    //--- DATABASE MAPPING : from_point ( FLOAT ) 
    public void setFromPoint( Float fromPoint ) {
        this.fromPoint = fromPoint;
    }
    public Float getFromPoint() {
        return this.fromPoint;
    }

    //--- DATABASE MAPPING : to_point ( FLOAT ) 
    public void setToPoint( Float toPoint ) {
        this.toPoint = toPoint;
    }
    public Float getToPoint() {
        return this.toPoint;
    }

    //--- DATABASE MAPPING : content_result ( TEXT ) 
    public void setContentResult( String contentResult ) {
        this.contentResult = contentResult;
    }
    public String getContentResult() {
        return this.contentResult;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : type_icon ( SMALLINT ) 
    public void setTypeIcon( Short typeIcon ) {
        this.typeIcon = typeIcon;
    }
    public Short getTypeIcon() {
        return this.typeIcon;
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
    public void setListOfUserQuestionaire( List<UserQuestionaireEntity> listOfUserQuestionaire ) {
        this.listOfUserQuestionaire = listOfUserQuestionaire;
    }
    public List<UserQuestionaireEntity> getListOfUserQuestionaire() {
        return this.listOfUserQuestionaire;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(questionaireId);
        sb.append("|");
        sb.append(fromPoint);
        sb.append("|");
        sb.append(toPoint);
        // attribute 'contentResult' not usable (type = String Long Text)
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(typeIcon);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}