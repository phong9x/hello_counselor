/*
 * Created on 22 Apr 2017 ( Time 11:48:46 )
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
 * Persistent class for entity stored in table "request_user_psychological"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="request_user_psychological", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="RequestUserPsychologicalEntity.countAll", query="SELECT COUNT(x) FROM RequestUserPsychologicalEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class RequestUserPsychologicalEntity implements Serializable {

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="request_psychological")
    private Date       requestPsychological ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="payment_psychological")
    private Date       paymentPsychological ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="effect_payment_psychological")
    private Date       effectPaymentPsychological ;

    @Column(name="status")
    private Short      status       ;

    @Column(name="fee")
    private Integer    fee          ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="email", length=300)
    private String     email        ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 
	// "counselorId" (column "counselor_id") is not defined by itself because used as FK in a link 
	// "paymentId" (column "payment_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="payment_id", referencedColumnName="id")
    private PaymentHistoryEntity paymentHistory;

    @ManyToOne
    @JoinColumn(name="counselor_id", referencedColumnName="id")
    private CounselorEntity counselor   ;

	@JsonIgnore
    @OneToMany( mappedBy="requestUserPsychological", targetEntity=UserPsychologicalEntity.class)
    private List<UserPsychologicalEntity> listOfUserPsychological;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RequestUserPsychologicalEntity() {
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
    //--- DATABASE MAPPING : request_psychological ( DATETIME ) 
    public void setRequestPsychological( Date requestPsychological ) {
        this.requestPsychological = requestPsychological;
    }
    public Date getRequestPsychological() {
        return this.requestPsychological;
    }

    //--- DATABASE MAPPING : payment_psychological ( DATETIME ) 
    public void setPaymentPsychological( Date paymentPsychological ) {
        this.paymentPsychological = paymentPsychological;
    }
    public Date getPaymentPsychological() {
        return this.paymentPsychological;
    }

    //--- DATABASE MAPPING : effect_payment_psychological ( DATETIME ) 
    public void setEffectPaymentPsychological( Date effectPaymentPsychological ) {
        this.effectPaymentPsychological = effectPaymentPsychological;
    }
    public Date getEffectPaymentPsychological() {
        return this.effectPaymentPsychological;
    }

    //--- DATABASE MAPPING : status ( SMALLINT ) 
    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : fee ( INT ) 
    public void setFee( Integer fee ) {
        this.fee = fee;
    }
    public Integer getFee() {
        return this.fee;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : email ( VARCHAR ) 
    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
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

    public void setPaymentHistory( PaymentHistoryEntity paymentHistory ) {
        this.paymentHistory = paymentHistory;
    }
    public PaymentHistoryEntity getPaymentHistory() {
        return this.paymentHistory;
    }

    public void setCounselor( CounselorEntity counselor ) {
        this.counselor = counselor;
    }
    public CounselorEntity getCounselor() {
        return this.counselor;
    }

    public void setListOfUserPsychological( List<UserPsychologicalEntity> listOfUserPsychological ) {
        this.listOfUserPsychological = listOfUserPsychological;
    }
    public List<UserPsychologicalEntity> getListOfUserPsychological() {
        return this.listOfUserPsychological;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(requestPsychological);
        sb.append("|");
        sb.append(paymentPsychological);
        sb.append("|");
        sb.append(effectPaymentPsychological);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(fee);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
