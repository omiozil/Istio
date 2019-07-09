package com.orchestOrder.orderOrchestration.model;

public class OrchestrationModel {
	
	private int orderNo;
	private String productName;
	private int productUnits;
	private double totalCost;
	private String orderStatus;
	private int stockNo;
	private double stockAvailable;
	private double stockChange;
	private int paymentId;
	private String paymentStatus;
	private int deliveryId;
	private int deliveryTimeTakeninDays;
	private String status;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductUnits() {
		return productUnits;
	}
	public void setProductUnits(int productUnits) {
		this.productUnits = productUnits;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}
	public double getStockAvailable() {
		return stockAvailable;
	}
	public void setStockAvailable(double stockAvailable) {
		this.stockAvailable = stockAvailable;
	}
	public double getStockChange() {
		return stockChange;
	}
	public void setStockChange(double stockChange) {
		this.stockChange = stockChange;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}
	public int getDeliveryTimeTakeninDays() {
		return deliveryTimeTakeninDays;
	}
	public void setDeliveryTimeTakeninDays(int deliveryTimeTakeninDays) {
		this.deliveryTimeTakeninDays = deliveryTimeTakeninDays;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
