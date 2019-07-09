package com.delivery.deliveryService.serviceImpl;

import java.util.List;

import com.delivery.deliveryService.model.Delivery;

public interface DeliveryService {

    void createDelivery(Delivery delivery);
	
	List<Delivery> findAllDelivery();
	
	void updateDeliveryStatus(Delivery delivery);
	
	/*void createDelivery(Delivery delivery);
	
	List<Delivery> findAllDelivery();
	
	Delivery findDeliveryByID(int deliveryId);*/
	
}
