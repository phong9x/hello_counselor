/*
 * Created on 8 May 2017 ( Time 11:45:43 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@DynamicInsert
@DynamicUpdate
public class BusinessSub implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Integer businessId;

    @NotNull
    @Size( min = 1, max = 256 )
    private String businessSubNo;


    private Integer manageYear;

    @Size( max = 256 )
    private String businessSubName;

    @Size( max = 256 )
    private String managerName;

    @Size( max = 256 )
    private String managerPhone;

    @Size( max = 256 )
    private String managerEmail;

    @Size( max = 256 )
    private String managerPosition;


    private Integer status;


    private Date contractStartDate;


    private Date contractEndDate;

    @NotNull
    private Date createDate;



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
    public void setBusinessId( Integer businessId ) {
        this.businessId = businessId;
    }
    public Integer getBusinessId() {
        return this.businessId;
    }

    public void setBusinessSubNo( String businessSubNo ) {
        this.businessSubNo = businessSubNo;
    }
    public String getBusinessSubNo() {
        return this.businessSubNo;
    }

    public void setManageYear( Integer manageYear ) {
        this.manageYear = manageYear;
    }
    public Integer getManageYear() {
        return this.manageYear;
    }

    public void setBusinessSubName( String businessSubName ) {
        this.businessSubName = businessSubName;
    }
    public String getBusinessSubName() {
        return this.businessSubName;
    }

    public void setManagerName( String managerName ) {
        this.managerName = managerName;
    }
    public String getManagerName() {
        return this.managerName;
    }

    public void setManagerPhone( String managerPhone ) {
        this.managerPhone = managerPhone;
    }
    public String getManagerPhone() {
        return this.managerPhone;
    }

    public void setManagerEmail( String managerEmail ) {
        this.managerEmail = managerEmail;
    }
    public String getManagerEmail() {
        return this.managerEmail;
    }

    public void setManagerPosition( String managerPosition ) {
        this.managerPosition = managerPosition;
    }
    public String getManagerPosition() {
        return this.managerPosition;
    }

    public void setStatus( Integer status ) {
        this.status = status;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setContractStartDate( Date contractStartDate ) {
        this.contractStartDate = contractStartDate;
    }
    public Date getContractStartDate() {
        return this.contractStartDate;
    }

    public void setContractEndDate( Date contractEndDate ) {
        this.contractEndDate = contractEndDate;
    }
    public Date getContractEndDate() {
        return this.contractEndDate;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(businessId);
        sb.append("|");
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
