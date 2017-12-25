package org.trams.hello.bean.api;

import java.util.Date;

public class HelloCoinSummary {
	private Integer coin;
	private Integer currentCoin;
	private String reason;
	private Date createDate;
	private Integer isIncrease;

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getCurrentCoin() {
		return currentCoin;
	}

	public void setCurrentCoin(Integer currentCoin) {
		this.currentCoin = currentCoin;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsIncrease() {
		return isIncrease;
	}

	public void setIsIncrease(Integer isIncrease) {
		this.isIncrease = isIncrease;
	}

}
