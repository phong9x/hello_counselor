/*
 * Created on 14 Apr 2017 ( Time 15:35:15 )
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
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer parentId;

    @NotNull
    @Size( min = 1, max = 100 )
    private String categoryName;

    @Size( max = 100 )
    private String decription;

    @Size( max = 100 )
    private String comment;

    @Size( max = 300 )
    private String imageCheckUrl;

    @Size( max = 300 )
    private String imageUncheckUrl;

    @NotNull
    private Short type;

    @NotNull
    private Short isShow;

    @NotNull
    private Integer relateCategoryId;


    private Date createDate;


    private Date updateDate;

    @NotNull
    private Integer status;



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
    public void setParentId( Integer parentId ) {
        this.parentId = parentId;
    }
    public Integer getParentId() {
        return this.parentId;
    }

    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setDecription( String decription ) {
        this.decription = decription;
    }
    public String getDecription() {
        return this.decription;
    }

    public void setComment( String comment ) {
        this.comment = comment;
    }
    public String getComment() {
        return this.comment;
    }

    public void setImageCheckUrl( String imageCheckUrl ) {
        this.imageCheckUrl = imageCheckUrl;
    }
    public String getImageCheckUrl() {
        return this.imageCheckUrl;
    }

    public void setImageUncheckUrl( String imageUncheckUrl ) {
        this.imageUncheckUrl = imageUncheckUrl;
    }
    public String getImageUncheckUrl() {
        return this.imageUncheckUrl;
    }

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setIsShow( Short isShow ) {
        this.isShow = isShow;
    }
    public Short getIsShow() {
        return this.isShow;
    }

    public void setRelateCategoryId( Integer relateCategoryId ) {
        this.relateCategoryId = relateCategoryId;
    }
    public Integer getRelateCategoryId() {
        return this.relateCategoryId;
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

    public void setStatus( Integer status ) {
        this.status = status;
    }
    public Integer getStatus() {
        return this.status;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(parentId);
        sb.append("|");
        sb.append(categoryName);
        sb.append("|");
        sb.append(decription);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(imageCheckUrl);
        sb.append("|");
        sb.append(imageUncheckUrl);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(isShow);
        sb.append("|");
        sb.append(relateCategoryId);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 


}