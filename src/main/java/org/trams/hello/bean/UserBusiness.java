package org.trams.hello.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity bean for table "user_business"
 * 
 * @author Telosys Tools Generator
 *
 */
public class UserBusiness implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer    id           ; // Primary Key

    private Integer    userId       ;
    private Integer    businessSubId ;
    private Boolean    isDeleted    ;
    private Date       createDate   ;
    private Date       updateDate   ;

    /**
     * Default constructor
     */
    public UserBusiness()
    {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR THE PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "INT", NotNull : true ) 
     * @param id
     */
	public void setId( Integer id )
    {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "INT", NotNull : true ) 
     * @return the field value
     */
	public Integer getId()
    {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR DATA FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : user_id ( INT ) 
    /**
     * Set the "userId" field value
     * This field is mapped on the database column "user_id" ( type "INT", NotNull : false ) 
     * @param userId
     */
    public void setUserId( Integer userId )
    {
        this.userId = userId;
    }
    /**
     * Get the "userId" field value
     * This field is mapped on the database column "user_id" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getUserId()
    {
        return this.userId;
    }

    //--- DATABASE MAPPING : business_sub_id ( INT ) 
    /**
     * Set the "businessSubId" field value
     * This field is mapped on the database column "business_sub_id" ( type "INT", NotNull : false ) 
     * @param businessSubId
     */
    public void setBusinessSubId( Integer businessSubId )
    {
        this.businessSubId = businessSubId;
    }
    /**
     * Get the "businessSubId" field value
     * This field is mapped on the database column "business_sub_id" ( type "INT", NotNull : false ) 
     * @return the field value
     */
    public Integer getBusinessSubId()
    {
        return this.businessSubId;
    }

    //--- DATABASE MAPPING : is_deleted ( BIT ) 
    /**
     * Set the "isDeleted" field value
     * This field is mapped on the database column "is_deleted" ( type "BIT", NotNull : false ) 
     * @param isDeleted
     */
    public void setIsDeleted( Boolean isDeleted )
    {
        this.isDeleted = isDeleted;
    }
    /**
     * Get the "isDeleted" field value
     * This field is mapped on the database column "is_deleted" ( type "BIT", NotNull : false ) 
     * @return the field value
     */
    public Boolean getIsDeleted()
    {
        return this.isDeleted;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    /**
     * Set the "createDate" field value
     * This field is mapped on the database column "create_date" ( type "DATETIME", NotNull : false ) 
     * @param createDate
     */
    public void setCreateDate( Date createDate )
    {
        this.createDate = createDate;
    }
    /**
     * Get the "createDate" field value
     * This field is mapped on the database column "create_date" ( type "DATETIME", NotNull : false ) 
     * @return the field value
     */
    public Date getCreateDate()
    {
        return this.createDate;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    /**
     * Set the "updateDate" field value
     * This field is mapped on the database column "update_date" ( type "DATETIME", NotNull : false ) 
     * @param updateDate
     */
    public void setUpdateDate( Date updateDate )
    {
        this.updateDate = updateDate;
    }
    /**
     * Get the "updateDate" field value
     * This field is mapped on the database column "update_date" ( type "DATETIME", NotNull : false ) 
     * @return the field value
     */
    public Date getUpdateDate()
    {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(businessSubId);
        sb.append("|");
        sb.append(isDeleted);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
