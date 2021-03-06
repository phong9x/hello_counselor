/*
 * Created on 17 May 2017 ( Time 10:31:38 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "notice"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="notice", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="NoticeEntity.countAll", query="SELECT COUNT(x) FROM NoticeEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class NoticeEntity implements Serializable {

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
    @Column(name="title", nullable=false, length=200)
    private String     title        ;

    @Column(name="content", nullable=false, length=500)
    private String     content      ;

    @Column(name="business_type")
    private Short      businessType ;

    @Column(name="os_type", nullable=false, length=50)
    private String     osType       ;

    @Column(name="view_number", nullable=false)
    private Integer    viewNumber   ;

    @Column(name="is_show", nullable=false)
    private Short      isShow       ;

    @Column(name="type")
    private Short      type         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;
    
    @Transient
	private String adminName;
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	// "adminId" (column "admin_id") is not defined by itself because used as FK in a link 
	// "businessId" (column "business_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="notice", targetEntity=UserNoticeEntity.class)
    private List<UserNoticeEntity> listOfUserNotice;

    @ManyToOne
    @JoinColumn(name="admin_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="business_id", referencedColumnName="id")
    private BusinessEntity business    ;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public NoticeEntity() {
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

    //--- DATABASE MAPPING : content ( VARCHAR ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : business_type ( SMALLINT UNSIGNED ) 
    public void setBusinessType( Short businessType ) {
        this.businessType = businessType;
    }
    public Short getBusinessType() {
        return this.businessType;
    }

    //--- DATABASE MAPPING : os_type ( VARCHAR ) 
    public void setOsType( String osType ) {
        this.osType = osType;
    }
    public String getOsType() {
        return this.osType;
    }

    //--- DATABASE MAPPING : view_number ( INT ) 
    public void setViewNumber( Integer viewNumber ) {
        this.viewNumber = viewNumber;
    }
    public Integer getViewNumber() {
        return this.viewNumber;
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
    public void setListOfUserNotice( List<UserNoticeEntity> listOfUserNotice ) {
        this.listOfUserNotice = listOfUserNotice;
    }
    public List<UserNoticeEntity> getListOfUserNotice() {
        return this.listOfUserNotice;
    }

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setBusiness( BusinessEntity business ) {
        this.business = business;
    }
    public BusinessEntity getBusiness() {
        return this.business;
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
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(businessType);
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
