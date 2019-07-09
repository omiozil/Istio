package com.delivery.deliveryService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.deliveryService.model.Delivery;
import com.delivery.deliveryService.serviceImpl.DeliveryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/api")
public class DeliveryController {
	
	public static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private DeliveryService deliveryService;
	
	@RequestMapping(value = "/createDelivery", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> createCustomer(@RequestBody Delivery delivery) throws JsonProcessingException{	
		
		System.out.println("Create Delivery");
		System.out.println("Delivery : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(delivery));
		
		//delivery.setDeliveryId(1);
		//delivery.setDeliveryTimeTakeninDays(1);
		
		
		System.out.println("Delivery : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(delivery));
		
		int deliveryTime = delivery.getDeliveryTimeTakeninDays();
		Map<String, String> map = new HashMap<>();
		
		if(deliveryTime == 1) {
			delivery.setStatus("Completed");
			deliveryService.createDelivery(delivery);
			map.put("status","Success");
		
		}
		else {
			delivery.setStatus("Not Attempted");
			deliveryService.createDelivery(delivery);	
			map.put("status","Fail");
		}

		
		

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/getDelivery", method = RequestMethod.GET)
	public ResponseEntity<List<Delivery>> getOrder(){	
		
		
		System.out.println("Get Delivery Service Call");
		
		List<Delivery> ls = deliveryService.findAllDelivery();
		

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<List<Delivery>>(ls,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updateDelivery", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> updateOrder(@RequestBody Delivery delivery){	
			
		System.out.println("Update Delivery Service Call");
		deliveryService.updateDeliveryStatus(delivery);

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}

	
/*	@RequestMapping(value = "/createDelivery", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> createCustomer(@RequestBody Delivery delivery) throws JsonProcessingException{	
		
		System.out.println("Create Delivery");
		System.out.println("Order : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(delivery));
		
		delivery.setDeliveryId(1);
		delivery.setDeliveryTimeTakeninDays(1);
		
		
		System.out.println("Delivery : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(delivery));
		
		int deliveryTime = delivery.getDeliveryTimeTakeninDays();
		
		if(deliveryTime == 1) {
			delivery.setStatus("Completed");
			deliveryService.createDelivery(delivery);
		
		}
		else {
			delivery.setStatus("Not Attempted");
			deliveryService.createDelivery(delivery);			
		}

		Map<String, String> map = new HashMap<>();
		map.put("status","Success");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/getDelivery", method = RequestMethod.GET)
	public ResponseEntity<List<Delivery>> getOrder(){	
		
		
		System.out.println("Get Delivery Service Call");
		
		List<Delivery> ls = deliveryService.findAllDelivery();
		

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<List<Delivery>>(ls,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updateDelivery", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> updateOrder(@RequestBody Delivery delivery){	
		
		Delivery getDeliveryIdResponse = deliveryService.findDeliveryByID(delivery.getDeliveryId());
		System.out.println("Update Delivery Service Call");
		

		getDeliveryIdResponse.setDeliveryId(delivery.getDeliveryId());
		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
*/
}
