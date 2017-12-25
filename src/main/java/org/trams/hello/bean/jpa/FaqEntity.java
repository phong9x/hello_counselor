/*
 * Created on 25 May 2017 ( Time 14:00:38 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Persistent class for entity stored in table "faq"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="faq", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="FaqEntity.countAll", query="SELECT COUNT(x) FROM FaqEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class FaqEntity implements Serializable {

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
    @Column(name="title", nullable=false, length=300)
    private String     title        ;

    @Column(name="content", nullable=false)
    private String     content      ;

    @Column(name="view_count", nullable=false)
    private Integer    viewCount    ;

    @Column(name="os_display", nullable=false)
    private Integer    osDisplay    ;

    @Column(name="order_number")
    private Integer    orderNumber  ;

    @Column(name="is_show", nullable=false)
    private Short      isShow       ;

    @Column(name="type", nullable=false)
    private Short      type         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public FaqEntity() {
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
    //--- DATABASE MAPPING : title ( VARCHAR ) 
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    //--- DATABASE MAPPING : content ( TEXT ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : view_count ( INT ) 
    public void setViewCount( Integer viewCount ) {
        this.viewCount = viewCount;
    }
    public Integer getViewCount() {
        return this.viewCount;
    }

    //--- DATABASE MAPPING : os_display ( INT ) 
    public void setOsDisplay( Integer osDisplay ) {
        this.osDisplay = osDisplay;
    }
    public Integer getOsDisplay() {
        return this.osDisplay;
    }

    //--- DATABASE MAPPING : order_number ( INT ) 
    public void setOrderNumber( Integer orderNumber ) {
        this.orderNumber = orderNumber;
    }
    public Integer getOrderNumber() {
        return this.orderNumber;
    }

    //--- DATABASE MAPPING : is_show ( SMALLINT ) 
    public void setIsShow( Short isShow ) {
        this.isShow = isShow;
    }
    public Short getIsShow() {
        return this.isShow;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

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
    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(title);
        // attribute 'content' not usable (type = String Long Text)
        sb.append("|");
        sb.append(viewCount);
        sb.append("|");
        sb.append(osDisplay);
        sb.append("|");
        sb.append(orderNumber);
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
