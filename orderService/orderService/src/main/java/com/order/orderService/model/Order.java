package com.order.orderService.model;

public class Order {
	
	int orderNo;
	String productName;
	int productUnits;
	double totalCost;
	String orderStatus;
	double stockAvailable;
	
	public Order() {
		
	}
	
	public Order(int orderNo, String productName, int productUnits, double totalCost, String orderStatus, double stockAvailable) {
		super();
		this.orderNo = orderNo;
		this.productName = productName;
		this.productUnits = productUnits;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
		this.stockAvailable = stockAvailable;
	}
	
	public double getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(double stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

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


	

}
