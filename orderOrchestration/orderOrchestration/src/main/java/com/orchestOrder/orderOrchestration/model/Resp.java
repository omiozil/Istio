package com.orchestOrder.orderOrchestration.model;

import java.io.Serializable;

public class Resp implements Serializable {

	private String status;
	private double stockAvailable;
	private int orderNo;
	private int paymentId;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public double getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(double stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	
}
