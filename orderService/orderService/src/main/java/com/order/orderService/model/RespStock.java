package com.order.orderService.model;

public class RespStock {
	
	int stockNo;
	String productName;
	double stockAvailable;




	public int getStockNo() {
		return stockNo;
	}



	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	

	public double getStockAvailable() {
		return stockAvailable;
	}
	
	public void setStockAvailable(double stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

}
