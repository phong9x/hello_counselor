/*
 * Created on 22 Apr 2017 ( Time 11:48:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@DynamicInsert
@DynamicUpdate
public class UserPsychological implements Serializable {

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


    private Integer counselorId;


    private Integer psychologicalTestId;


    private Integer requestPsychologicalId;


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

    public void setCounselorId( Integer counselorId ) {
        this.counselorId = counselorId;
    }
    public Integer getCounselorId() {
        return this.counselorId;
    }

    public void setPsychologicalTestId( Integer psychologicalTestId ) {
        this.psychologicalTestId = psychologicalTestId;
    }
    public Integer getPsychologicalTestId() {
        return this.psychologicalTestId;
    }

    public void setRequestPsychologicalId( Integer requestPsychologicalId ) {
        this.requestPsychologicalId = requestPsychologicalId;
    }
    public Integer getRequestPsychologicalId() {
        return this.requestPsychologicalId;
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
        sb.append(counselorId);
        sb.append("|");
        sb.append(psychologicalTestId);
        sb.append("|");
        sb.append(requestPsychologicalId);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
