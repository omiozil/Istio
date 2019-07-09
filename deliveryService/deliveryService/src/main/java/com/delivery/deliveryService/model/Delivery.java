package com.delivery.deliveryService.model;

public class Delivery {
	
	private int deliveryId;
	private int deliveryTimeTakeninDays;
	private String status;
	
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
