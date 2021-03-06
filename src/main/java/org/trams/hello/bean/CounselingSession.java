/*
 * Created on 31 May 2017 ( Time 11:29:40 )
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
public class CounselingSession implements Serializable {

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
    private Integer userId;

    @NotNull
    private Integer counselorId;


    private Integer paymentId;

    @Size( max = 100 )
    private String roomId;

    @Size( max = 500 )
    private String counselingField;


    private Short memberType;

    @NotNull
    private Date startTime;


    private Date endTime;


    private Date cancelMettingTime;


    private Date entryCounselorTime;


    private Date entryUserTime;


    private Date exitCounselorTime;


    private Date exitUserTime;


    private Float point;


    private Date refundDate;


    private Short status;


    private Short counselType;

    @NotNull
    private Short counselingTimeType;


    private Short satisfactionRating;


    private Integer isDelete;


    private Date createDate;


    private Date updateDate;

    
    private Integer disconnectCount;

    
    private String preRoomId;
    
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

    public void setPaymentId( Integer paymentId ) {
        this.paymentId = paymentId;
    }
    public Integer getPaymentId() {
        return this.paymentId;
    }

    public void setRoomId( String roomId ) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return this.roomId;
    }

    public void setCounselingField( String counselingField ) {
        this.counselingField = counselingField;
    }
    public String getCounselingField() {
        return this.counselingField;
    }

    public void setMemberType( Short memberType ) {
        this.memberType = memberType;
    }
    public Short getMemberType() {
        return this.memberType;
    }

    public void setStartTime( Date startTime ) {
        this.startTime = startTime;
    }
    public Date getStartTime() {
        return this.startTime;
    }

    public void setEndTime( Date endTime ) {
        this.endTime = endTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }

    public void setCancelMettingTime( Date cancelMettingTime ) {
        this.cancelMettingTime = cancelMettingTime;
    }
    public Date getCancelMettingTime() {
        return this.cancelMettingTime;
    }

    public void setEntryCounselorTime( Date entryCounselorTime ) {
        this.entryCounselorTime = entryCounselorTime;
    }
    public Date getEntryCounselorTime() {
        return this.entryCounselorTime;
    }

    public void setEntryUserTime( Date entryUserTime ) {
        this.entryUserTime = entryUserTime;
    }
    public Date getEntryUserTime() {
        return this.entryUserTime;
    }

    public void setExitCounselorTime( Date exitCounselorTime ) {
        this.exitCounselorTime = exitCounselorTime;
    }
    public Date getExitCounselorTime() {
        return this.exitCounselorTime;
    }

    public void setExitUserTime( Date exitUserTime ) {
        this.exitUserTime = exitUserTime;
    }
    public Date getExitUserTime() {
        return this.exitUserTime;
    }

    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    public void setRefundDate( Date refundDate ) {
        this.refundDate = refundDate;
    }
    public Date getRefundDate() {
        return this.refundDate;
    }

    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    public void setCounselType( Short counselType ) {
        this.counselType = counselType;
    }
    public Short getCounselType() {
        return this.counselType;
    }

    public void setCounselingTimeType( Short counselingTimeType ) {
        this.counselingTimeType = counselingTimeType;
    }
    public Short getCounselingTimeType() {
        return this.counselingTimeType;
    }

    public void setSatisfactionRating( Short satisfactionRating ) {
        this.satisfactionRating = satisfactionRating;
    }
    public Short getSatisfactionRating() {
        return this.satisfactionRating;
    }

    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return this.isDelete;
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

	public Integer getDisconnectCount() {
		return disconnectCount;
	}

	public void setDisconnectCount(Integer disconnectCount) {
		this.disconnectCount = disconnectCount;
	}
	
	
	public String getPreRoomId() {
		return preRoomId;
	}

	public void setPreRoomId(String preRoomId) {
		this.preRoomId = preRoomId;
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
        sb.append(paymentId);
        sb.append("|");
        sb.append(roomId);
        sb.append("|");
        sb.append(counselingField);
        sb.append("|");
        sb.append(memberType);
        sb.append("|");
        sb.append(startTime);
        sb.append("|");
        sb.append(endTime);
        sb.append("|");
        sb.append(cancelMettingTime);
        sb.append("|");
        sb.append(entryCounselorTime);
        sb.append("|");
        sb.append(entryUserTime);
        sb.append("|");
        sb.append(exitCounselorTime);
        sb.append("|");
        sb.append(exitUserTime);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(refundDate);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(counselType);
        sb.append("|");
        sb.append(counselingTimeType);
        sb.append("|");
        sb.append(satisfactionRating);
        sb.append("|");
        sb.append(isDelete);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(disconnectCount);
        sb.append("|");
        sb.append(preRoomId);
        return sb.toString(); 
    } 


}
