/*
 * Created on 22 May 2017 ( Time 09:42:33 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;

import javax.validation.constraints.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;
@DynamicInsert
@DynamicUpdate
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer userId;

    @Size( max = 200 )
    private String title;

    @Size( max = 500 )
    private String content;


    private Short type;


    private Short typePush;

    @Size( max = 200 )
    private String information;


    private Short osType;


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
    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

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

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setTypePush( Short typePush ) {
        this.typePush = typePush;
    }
    public Short getTypePush() {
        return this.typePush;
    }

    public void setInformation( String information ) {
        this.information = information;
    }
    public String getInformation() {
        return this.information;
    }

    public void setOsType( Short osType ) {
        this.osType = osType;
    }
    public Short getOsType() {
        return this.osType;
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
        sb.append(userId);
        sb.append("|");
        sb.append(title);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(typePush);
        sb.append("|");
        sb.append(information);
        sb.append("|");
        sb.append(osType);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}