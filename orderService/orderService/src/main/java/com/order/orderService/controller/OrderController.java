package com.order.orderService.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderService.model.Order;
import com.order.orderService.model.RespStock;
import com.order.orderService.serviceImlp.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {

	public static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private ObjectMapper mapper = new ObjectMapper();
	private static final AtomicInteger counter = new AtomicInteger();

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody Order order) throws JsonProcessingException {

		Map<String, Object> map = new HashMap<>();
		
		try {
		System.out.println("Order : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(order));

		RestTemplate rest = new RestTemplate();

		ResponseEntity<RespStock> resp = rest.postForEntity("http://localhost:8072/getStockByName", order,
				RespStock.class);

		System.out.println("Stock Body : " + resp.getBody().getStockAvailable());
		int a = (int) resp.getBody().getStockAvailable();

		Order o2 = orderService.getOrderNo();
		
		if(o2 != null) {
			System.out.println("o2 not null ");
			int orderNo = 1 + o2.getOrderNo();
			order.setOrderNo(orderNo);
		}
		else {
			System.out.println("o2 is null ");
			order.setOrderNo(counter.incrementAndGet());
		}
		
		
		System.out.println("Order No - : " + order.getOrderNo() );
		
		

		if (a >= order.getProductUnits()) {

			System.out.println("Stock Availabe change :");
			order.setOrderStatus("Process");
			orderService.createOrder(order);
			double stockChange = (int) (resp.getBody().getStockAvailable() - order.getProductUnits());
			order.setStockAvailable(stockChange);
			System.out.println("order No:" + order.getOrderNo());
			
			rest.postForLocation("http://localhost:8072/updateStock", order);
			map.put("status", "Success");
			map.put("orderNo", order.getOrderNo());
		} else {
			order.setOrderStatus("Failed");
			orderService.createOrder(order);
			map.put("status", "Order Failed to process due to unavailable of Stocks");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@RequestMapping(value = "/getOrder", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrder() {

		System.out.println("Get Order Service");
		List<Order> ls = orderService.getAllOrder();

		// Map<String, Object> map = new HashMap<>();

		return new ResponseEntity<List<Order>>(ls, HttpStatus.OK);

	}

	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> updateOrder(@RequestBody Order order) {

		orderService.updateOrder(order);

		
		
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Fail");
	

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	/*
	 * @RequestMapping(value = "/getOrder", method = RequestMethod.GET) public
	 * ResponseEntity<List<Order>> getOrder() {
	 * 
	 * System.out.println("Get Order Service"); List<Order> ls =
	 * orderService.findAllOrder();
	 * 
	 * Map<String, String> map = new HashMap<>(); map.put("status", "Fail");
	 * 
	 * return new ResponseEntity<List<Order>>(ls, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/updateOrder", method = RequestMethod.POST) public
	 * ResponseEntity<Map<String, String>> updateOrder(@RequestBody Order order) {
	 * 
	 * Order ordergetOrderNoResponse =
	 * orderService.findOrderById(order.getOrderNo());
	 * 
	 * System.out.println("Call To Update order status :" + order.getOrderNo());
	 * System.out.println("Call To Update order status :" + order.getOrderStatus());
	 * ordergetOrderNoResponse.setOrderStatus(order.getOrderStatus()); Map<String,
	 * String> map = new HashMap<>(); map.put("status", "Fail");
	 * 
	 * return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	 * 
	 * }
	 */

}
