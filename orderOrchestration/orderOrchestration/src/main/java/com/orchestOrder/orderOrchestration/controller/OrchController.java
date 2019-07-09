package com.orchestOrder.orderOrchestration.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestOrder.orderOrchestration.common.DataUtils;
import com.orchestOrder.orderOrchestration.model.OrchestrationModel;
import com.orchestOrder.orderOrchestration.model.Resp;

@RestController
@RequestMapping("/api")
public class OrchController {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@RequestMapping(value = "/orchestration", method = RequestMethod.POST)
	public ResponseEntity<?> createOrch(@RequestBody OrchestrationModel orcModel) throws IOException, ClassNotFoundException {

		RestTemplate rest = new RestTemplate();

		/* ************ Call to Create Order ****************** */
		
		System.out.println("Call to Create Order Service");
		System.out.println("Order : " + mapper.writeValueAsString(orcModel));
		
		
		ResponseEntity<Resp> response1 = rest.postForEntity("http://localhost:8072/getStockByName", orcModel,
				Resp.class);
		int stockAvailable = (int) response1.getBody().getStockAvailable();
		
		System.out.println("Order : " + mapper.writeValueAsString(orcModel));
		ResponseEntity<Resp> response = rest.postForEntity("http://localhost:8071/api/createOrder", orcModel,
				Resp.class);
		
		int orderNo = response.getBody().getOrderNo();
		System.out.println("Order No:" + orderNo);
		
		JSONObject myObject = new JSONObject(response);
		System.out.println("Response Body_______:" + myObject.toString());
		
		//System.out.println("Response Body From Generic Method :" + DataUtils.mapToJson(response));
		
		String str1 = response.getBody().getStatus();
		System.out.println("STR1:" + str1);

		if (str1.equals("Success")) {
				
				System.out.println("Call to Create Payment Service");
	

				/* ************ Call to Payment Service to generate Payment ************ */
				ResponseEntity<Resp> paymentResponse = rest.postForEntity("http://localhost:8073/api/createPayment",
						orcModel, Resp.class);

				
				int paymentId = paymentResponse.getBody().getPaymentId();
				System.out.println("Payment ID:" + paymentId);
				System.out.println("Response Body_______:" + response.getBody());
				String str2 = paymentResponse.getBody().getStatus();
				System.out.println("STR2:" + str2);

				if(str2.equals("Success")) {
					
					System.out.println("SUCCESSSSSSSSSSSSSSSSSSSSSSSSSSS");
					
					System.out.println("Call to Create Delivery Service");
					ResponseEntity<Resp> deliveryResponse = rest.postForEntity("http://localhost:8074/api/createDelivery",
							orcModel, Resp.class);

					
					String str3 = deliveryResponse.getBody().getStatus();
					System.out.println("Delivery Call :" + str3);
					if(str3.equals("Success")) {
						
						System.out.println("Delivery Success Response");
						orcModel.setOrderNo(orderNo);
						orcModel.setOrderStatus("Success");
						orcModel.setPaymentId(paymentId);
						orcModel.setPaymentStatus("Success");
						
						System.out.println("Order : " + mapper.writeValueAsString(orcModel));
						
						rest.postForLocation("http://localhost:8071/api/updateOrder", orcModel);
						rest.postForLocation("http://localhost:8073/api/updatePayment", orcModel);
						
						Map<String, String> map = new HashMap<>();
						map.put("Reasponse Message", "Delivery is accepted with in One Day");
						map.put("Response Code", "0");
						map.put("Payment Response", orcModel.getPaymentStatus());
						map.put("Order Response", orcModel.getOrderStatus());
						System.out.println("Purchase:" + map);
						return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
						
					}else {
						
						System.out.println(" Delivery Not Possible");
						orcModel.setOrderNo(orderNo);
						orcModel.setOrderStatus("Failed");
						orcModel.setPaymentId(paymentId);
						orcModel.setPaymentStatus("Failed");
						rest.postForLocation("http://localhost:8071/api/updateOrder", orcModel);
						orcModel.setStockAvailable(stockAvailable);
						rest.postForLocation("http://localhost:8072/updateStock", orcModel);
						rest.postForLocation("http://localhost:8073/api/updatePayment", orcModel);
						
						Map<String, String> map = new HashMap<>();
						map.put("Reasponse Message", "Delivery not possible with in One day");
						map.put("Response Code", "1");
						map.put("Payment Response", orcModel.getPaymentStatus());
						map.put("Order Response", orcModel.getOrderStatus());
						System.out.println("Purchase:" + map);
						return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
						
					}
					
				}
				else {
					
					System.out.println("Str2 so Else");
					orcModel.setOrderNo(orderNo);
					orcModel.setOrderStatus("Failed");
					orcModel.setPaymentId(paymentId);
					orcModel.setPaymentStatus("Failed");
					rest.postForLocation("http://localhost:8071/api/updateOrder", orcModel);					 
					
					System.out.println("Stock Avialble : " + stockAvailable);
					orcModel.setStockAvailable(stockAvailable);
					rest.postForLocation("http://localhost:8072/updateStock", orcModel);
					rest.postForLocation("http://localhost:8073/api/updatePayment", orcModel);
					
					Map<String, String> map = new HashMap<>();
					map.put("Reasponse Message", "Payment Generation Failed");
					map.put("Response Code", "1");
					map.put("Payment Response", orcModel.getPaymentStatus());
					map.put("Order Response", orcModel.getOrderStatus());
					System.out.println("Purchase:" + map);
					return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
					
				}

			
			}

		orcModel.setOrderStatus("Failed");
		rest.postForLocation("http://localhost:8071/api/updateOrder", orcModel);

		Map<String, String> map = new HashMap<>();
		map.put("Reasponse Message", "Order Creation Failed");
		map.put("Response Code", "1");
		orcModel.setStockAvailable(stockAvailable);
		rest.postForLocation("http://localhost:8072/updateStock", orcModel);
		map.put("Payment Response", orcModel.getPaymentStatus());
		map.put("Order Response", orcModel.getOrderStatus());
		System.out.println("Purchase:" + map);
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);


	}

}
