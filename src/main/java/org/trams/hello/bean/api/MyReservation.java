package org.trams.hello.bean.api;

import java.util.Date;

public class MyReservation {
	private Integer id;
	private String thumbnailUrlCounselor;
	private String fullNameCounselor;
	private Date startTimeCounseling;
	private Date endTimeCounseling;
	private Integer isCancelCounseling;
	private Float pointsCounselor;
	private Float pointsConvertCounselor;
	private int likesCounselor;
	private Integer paymentId;
	private Integer counselorId;
	private Integer statusEvaluationOfSatisfaction;

	public Integer getStatusEvaluationOfSatisfaction() {
		return statusEvaluationOfSatisfaction;
	}

	public void setStatusEvaluationOfSatisfaction(Integer statusEvaluationOfSatisfaction) {
		this.statusEvaluationOfSatisfaction = statusEvaluationOfSatisfaction;
	}

	public Integer getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(Integer counselorId) {
		this.counselorId = counselorId;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThumbnailUrlCounselor() {
		return thumbnailUrlCounselor;
	}

	public void setThumbnailUrlCounselor(String thumbnailUrlCounselor) {
		this.thumbnailUrlCounselor = thumbnailUrlCounselor;
	}

	public String getFullNameCounselor() {
		return fullNameCounselor;
	}

	public void setFullNameCounselor(String fullNameCounselor) {
		this.fullNameCounselor = fullNameCounselor;
	}

	public Date getStartTimeCounseling() {
		return startTimeCounseling;
	}

	public void setStartTimeCounseling(Date startTimeCounseling) {
		this.startTimeCounseling = startTimeCounseling;
	}

	public Date getEndTimeCounseling() {
		return endTimeCounseling;
	}

	public void setEndTimeCounseling(Date endTimeCounseling) {
		this.endTimeCounseling = endTimeCounseling;
	}

	public Integer getIsCancelCounseling() {
		return isCancelCounseling;
	}

	public void setIsCancelCounseling(Integer isCancelCounseling) {
		this.isCancelCounseling = isCancelCounseling;
	}

	public Float getPointsCounselor() {
		return pointsCounselor;
	}

	public void setPointsCounselor(Float pointsCounselor) {
		this.pointsCounselor = pointsCounselor;
	}

	public Float getPointsConvertCounselor() {
		return pointsConvertCounselor;
	}

	public void setPointsConvertCounselor(Float pointsConvertCounselor) {
		this.pointsConvertCounselor = pointsConvertCounselor;
	}

	public int getLikesCounselor() {
		return likesCounselor;
	}

	public void setLikesCounselor(int likesCounselor) {
		this.likesCounselor = likesCounselor;
	}

}
