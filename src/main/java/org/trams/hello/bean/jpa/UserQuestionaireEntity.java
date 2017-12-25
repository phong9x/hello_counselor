/*
 * Created on 24 Apr 2017 ( Time 14:17:26 )
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
 * Persistent class for entity stored in table "user_questionaire"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="user_questionaire", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UserQuestionaireEntity.countAll", query="SELECT COUNT(x) FROM UserQuestionaireEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class UserQuestionaireEntity implements Serializable {

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
    @Column(name="counseling_session_id")
    private Integer    counselingSessionId ;

    @Column(name="point")
    private Float      point        ;

    @Column(name="comment", length=500)
    private String     comment      ;

    @Column(name="recommend", nullable=false)
    private Short      recommend    ;

    @Column(name="type")
    private Short      type         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 
	// "counselorId" (column "counselor_id") is not defined by itself because used as FK in a link 
	// "questionaireId" (column "questionaire_id") is not defined by itself because used as FK in a link 
	// "selfDiagnosisResultsId" (column "self_diagnosis_results_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="counselor_id", referencedColumnName="id")
    private CounselorEntity counselor   ;

    @ManyToOne
    @JoinColumn(name="questionaire_id", referencedColumnName="id")
    private QuestionnaireEntity questionnaire;

	@JsonIgnore
    @OneToMany(mappedBy="userQuestionaire", targetEntity=TestResultEntity.class)
    private List<TestResultEntity> listOfTestResult;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="self_diagnosis_results_id", referencedColumnName="id")
    private SelfDiagnosisResultsEntity selfDiagnosisResults;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UserQuestionaireEntity() {
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
    //--- DATABASE MAPPING : counseling_session_id ( INT ) 
    public void setCounselingSessionId( Integer counselingSessionId ) {
        this.counselingSessionId = counselingSessionId;
    }
    public Integer getCounselingSessionId() {
        return this.counselingSessionId;
    }

    //--- DATABASE MAPPING : point ( FLOAT ) 
    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    //--- DATABASE MAPPING : comment ( VARCHAR ) 
    public void setComment( String comment ) {
        this.comment = comment;
    }
    public String getComment() {
        return this.comment;
    }

    //--- DATABASE MAPPING : recommend ( SMALLINT ) 
    public void setRecommend( Short recommend ) {
        this.recommend = recommend;
    }
    public Short getRecommend() {
        return this.recommend;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setCounselor( CounselorEntity counselor ) {
        this.counselor = counselor;
    }
    public CounselorEntity getCounselor() {
        return this.counselor;
    }

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

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setSelfDiagnosisResults( SelfDiagnosisResultsEntity selfDiagnosisResults ) {
        this.selfDiagnosisResults = selfDiagnosisResults;
    }
    public SelfDiagnosisResultsEntity getSelfDiagnosisResults() {
        return this.selfDiagnosisResults;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(counselingSessionId);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(recommend);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(createDate);
        return sb.toString(); 
    } 

}