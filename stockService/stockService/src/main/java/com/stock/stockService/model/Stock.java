package com.stock.stockService.model;

public class Stock {

	int stockNo;
	String productName;
	//double unitCost;
	double stockAvailable;
	
	
	
public Stock() {
		
	}

	public Stock(int stockNo, String productName, double stockAvailable) {
	
		this.stockNo = stockNo;
		this.productName = productName;
//		this.unitCost = unitCost;
		this.stockAvailable = stockAvailable;
		
	}

	
	/*public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
*/		
	
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
