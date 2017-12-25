/*
 * Created on 28 Apr 2017 ( Time 13:05:44 )
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
public class Notice implements Serializable {

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
    @Size( min = 1, max = 200 )
    private String title;

    @NotNull
    @Size( min = 1, max = 500 )
    private String content;


    private Short businessType;


    private Integer businessId;

    @NotNull
    @Size( min = 1, max = 50 )
    private String osType;

    @NotNull
    private Integer viewNumber;

    @NotNull
    private Short isShow;


    private Short type;


    private Date createDate;


    private Date updateDate;



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
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setBusinessType( Short businessType ) {
        this.businessType = businessType;
    }
    public Short getBusinessType() {
        return this.businessType;
    }

    public void setBusinessId( Integer businessId ) {
        this.businessId = businessId;
    }
    public Integer getBusinessId() {
        return this.businessId;
    }

    public void setOsType( String osType ) {
        this.osType = osType;
    }
    public String getOsType() {
        return this.osType;
    }

    public void setViewNumber( Integer viewNumber ) {
        this.viewNumber = viewNumber;
    }
    public Integer getViewNumber() {
        return this.viewNumber;
    }

    public void setIsShow( Short isShow ) {
        this.isShow = isShow;
    }
    public Short getIsShow() {
        return this.isShow;
    }

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(title);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(businessType);
        sb.append("|");
        sb.append(businessId);
        sb.append("|");
        sb.append(osType);
        sb.append("|");
        sb.append(viewNumber);
        sb.append("|");
        sb.append(isShow);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
