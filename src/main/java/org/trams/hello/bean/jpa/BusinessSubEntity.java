/*
 * Created on 8 May 2017 ( Time 11:45:43 )
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
 * Persistent class for entity stored in table "business_sub"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="business_sub", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="BusinessSubEntity.countAll", query="SELECT COUNT(x) FROM BusinessSubEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class BusinessSubEntity implements Serializable {

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
    @Column(name="business_sub_no", nullable=false, length=256)
    private String     businessSubNo ;

    @Column(name="manage_year")
    private Integer    manageYear   ;

    @Column(name="business_sub_name", length=256)
    private String     businessSubName ;

    @Column(name="manager_name", length=256)
    private String     managerName  ;

    @Column(name="manager_phone", length=256)
    private String     managerPhone ;

    @Column(name="manager_email", length=256)
    private String     managerEmail ;

    @Column(name="manager_position", length=256)
    private String     managerPosition ;

    @Column(name="status")
    private Integer    status       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="contract_start_date")
    private Date       contractStartDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="contract_end_date")
    private Date       contractEndDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable=false)
    private Date       createDate   ;

	// "businessId" (column "business_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="businessSub", targetEntity=UserEntity.class)
    private List<UserEntity> listOfUser  ;

	@JsonIgnore
    @OneToMany(mappedBy="businessSub", targetEntity=VoucherEntity.class)
    private List<VoucherEntity> listOfVoucher;

    @ManyToOne
    @JoinColumn(name="business_id", referencedColumnName="id")
    private BusinessEntity business    ;

	@JsonIgnore
    @OneToMany(mappedBy="businessSub", targetEntity=UserBusinessEntity.class)
    private List<UserBusinessEntity> listOfUserBusiness;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public BusinessSubEntity() {
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
    //--- DATABASE MAPPING : business_sub_no ( VARCHAR ) 
    public void setBusinessSubNo( String businessSubNo ) {
        this.businessSubNo = businessSubNo;
    }
    public String getBusinessSubNo() {
        return this.businessSubNo;
    }

    //--- DATABASE MAPPING : manage_year ( INT ) 
    public void setManageYear( Integer manageYear ) {
        this.manageYear = manageYear;
    }
    public Integer getManageYear() {
        return this.manageYear;
    }

    //--- DATABASE MAPPING : business_sub_name ( VARCHAR ) 
    public void setBusinessSubName( String businessSubName ) {
        this.businessSubName = businessSubName;
    }
    public String getBusinessSubName() {
        return this.businessSubName;
    }

    //--- DATABASE MAPPING : manager_name ( VARCHAR ) 
    public void setManagerName( String managerName ) {
        this.managerName = managerName;
    }
    public String getManagerName() {
        return this.managerName;
    }

    //--- DATABASE MAPPING : manager_phone ( VARCHAR ) 
    public void setManagerPhone( String managerPhone ) {
        this.managerPhone = managerPhone;
    }
    public String getManagerPhone() {
        return this.managerPhone;
    }

    //--- DATABASE MAPPING : manager_email ( VARCHAR ) 
    public void setManagerEmail( String managerEmail ) {
        this.managerEmail = managerEmail;
    }
    public String getManagerEmail() {
        return this.managerEmail;
    }

    //--- DATABASE MAPPING : manager_position ( VARCHAR ) 
    public void setManagerPosition( String managerPosition ) {
        this.managerPosition = managerPosition;
    }
    public String getManagerPosition() {
        return this.managerPosition;
    }

    //--- DATABASE MAPPING : status ( INT ) 
    public void setStatus( Integer status ) {
        this.status = status;
    }
    public Integer getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : contract_start_date ( DATETIME ) 
    public void setContractStartDate( Date contractStartDate ) {
        this.contractStartDate = contractStartDate;
    }
    public Date getContractStartDate() {
        return this.contractStartDate;
    }

    //--- DATABASE MAPPING : contract_end_date ( DATETIME ) 
    public void setContractEndDate( Date contractEndDate ) {
        this.contractEndDate = contractEndDate;
    }
    public Date getContractEndDate() {
        return this.contractEndDate;
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
    public void setListOfUser( List<UserEntity> listOfUser ) {
        this.listOfUser = listOfUser;
    }
    public List<UserEntity> getListOfUser() {
        return this.listOfUser;
    }

    public void setListOfVoucher( List<VoucherEntity> listOfVoucher ) {
        this.listOfVoucher = listOfVoucher;
    }
    public List<VoucherEntity> getListOfVoucher() {
        return this.listOfVoucher;
    }

    public void setBusiness( BusinessEntity business ) {
        this.business = business;
    }
    public BusinessEntity getBusiness() {
        return this.business;
    }

    public void setListOfUserBusiness( List<UserBusinessEntity> listOfUserBusiness ) {
        this.listOfUserBusiness = listOfUserBusiness;
    }
    public List<UserBusinessEntity> getListOfUserBusiness() {
        return this.listOfUserBusiness;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(businessSubNo);
        sb.append("|");
        sb.append(manageYear);
        sb.append("|");
        sb.append(businessSubName);
        sb.append("|");
        sb.append(managerName);
        sb.append("|");
        sb.append(managerPhone);
        sb.append("|");
        sb.append(managerEmail);
        sb.append("|");
        sb.append(managerPosition);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(contractStartDate);
        sb.append("|");
        sb.append(contractEndDate);
        sb.append("|");
        sb.append(createDate);
        return sb.toString(); 
    } 

}
