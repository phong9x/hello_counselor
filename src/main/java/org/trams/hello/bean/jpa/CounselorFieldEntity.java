/*
 * Created on 1 thg 12 2016 ( Time 15:28:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Persistent class for entity stored in table "counselor_field"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="counselor_field", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CounselorFieldEntity.countAll", query="SELECT COUNT(x) FROM CounselorFieldEntity x" )
} )
public class CounselorFieldEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private CounselorFieldEntityKey compositePrimaryKey ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable=false)
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date", nullable=false)
    private Date       updateDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	
    @ManyToOne
    @JoinColumn(name="counselor_id", referencedColumnName="id", insertable=false, updatable=false)
    private CounselorEntity counselor   ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CounselorFieldEntity() {
		super();
		this.compositePrimaryKey = new CounselorFieldEntityKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setCounselorId( Integer counselorId ) {
        this.compositePrimaryKey.setCounselorId( counselorId ) ;
    }
    public Integer getCounselorId() {
        return this.compositePrimaryKey.getCounselorId() ;
    }
    public void setCounselorFieldId( Integer counselorFieldId ) {
        this.compositePrimaryKey.setCounselorFieldId( counselorFieldId ) ;
    }
    public Integer getCounselorFieldId() {
        return this.compositePrimaryKey.getCounselorFieldId() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
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
    public void setCounselor( CounselorEntity counselor ) {
        this.counselor = counselor;
    }
    public CounselorEntity getCounselor() {
        return this.counselor;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
