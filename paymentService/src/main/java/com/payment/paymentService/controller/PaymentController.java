package com.payment.paymentService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.paymentService.model.Payment;
import com.payment.paymentService.serviceImpl.PaymentService;



@RestController
@RequestMapping("/api")
public class PaymentController {
	
	private static final AtomicInteger counter = new AtomicInteger();
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/createPayment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody Payment payment) throws JsonProcessingException{			
		
		Payment p1 = paymentService.getPaymentId();
		
		if(p1 != null) {
			System.out.println("p1 not null ");
			int paymentId = 1 + p1.getPaymentId();
			payment.setPaymentId(paymentId);
		}
		else {
			System.out.println("o2 is null ");
			payment.setPaymentId(counter.incrementAndGet());
		}
		
		System.out.println("Payment Service Call");
		payment.setPaymentStatus("Process");
		paymentService.createPay(payment);
		

		Map<String, Object> map = new HashMap<>();
		map.put("status","Success");
		map.put("paymentId",payment.getPaymentId());
		
		System.out.println("Map : " + map.toString());
		System.out.println("Map 2 : " + mapper.writeValueAsString(map));

		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/getPaymentDetails", method = RequestMethod.GET)
	public ResponseEntity<List<Payment>> getOrder(){	
		
		System.out.println("Get Payment Service Call");
		List<Payment> ls = paymentService.getAllPaymentsDetails();
		

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<List<Payment>>(ls,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> updateOrder(@RequestBody Payment payment){	
		
		System.out.println("Update Payment Service Call");
		paymentService.updatePaymentStatus(payment);
		
		
		Map<String, String> map = new HashMap<>();
		map.put("status","Payment Status Updated");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}

}
