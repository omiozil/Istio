package com.stock.stockService.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stockService.model.Stock;
import com.stock.stockService.serviceImpl.StockService;

@RestController
@RequestMapping
public class StockController {
	
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	StockService stockService;
	
	/* ************ DB INSERTION CHANGE ****************** */
	
/*	@RequestMapping(value = "/createStock", method = RequestMethod.POST)
	public ResponseEntity<?> createStock(@RequestBody Stock stock){
		
		System.out.println("Create Stock Service");	
		
		stockService.createStock(stock);
		
		Map<String, String> map = new HashMap<>();
		map.put("status","Success");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value = "/getStock", method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> getStock(){	
		
		System.out.println("Get Stock Call");
		List<Stock> ls = stockService.getAllStock();
		
		

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<List<Stock>>(ls,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updateStock", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> updateStock(@RequestBody Stock stock){	
		
		System.out.println("Update Stock Service");
		
		stockService.updateStock(stock);

		
		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/getStockByName", method = RequestMethod.POST)
	public ResponseEntity<Stock> stockByName(@RequestBody Stock stock) throws JsonProcessingException{	
		
		System.out.println("Get Stock by name is called");
		
		System.out.println("Product Name :" + stock.getProductName());
		
		 Stock ls = stockService.getStockByName(stock.getProductName());
		
	
		//List<Stock> ls = stockService.getStockByName(stock.getProductName());
		System.out.println("LIST LS : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ls));
		return new ResponseEntity<Stock>(ls,HttpStatus.OK);
		
			
	}
	*/
	
	
	/* ************ DB INSERTION CHANGE ****************** */
	
	
	
	
	@RequestMapping(value = "/createStock", method = RequestMethod.POST)
	public ResponseEntity<?> createStock(@RequestBody Stock stock){
		
		System.out.println("Create Stock Service");	
		
		stockService.createStock(stock);
		
		Map<String, String> map = new HashMap<>();
		map.put("status","Success");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
			
	}
	
	@RequestMapping(value = "/getStock", method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> getStock() throws JsonProcessingException{	
		
		System.out.println("Get Stock Call");
		List<Stock> ls = stockService.findAllStock();
		
		

		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");
		
		System.out.println("LIST LS : " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ls));

		return new ResponseEntity<List<Stock>>(ls,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/updateStock", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> updateStock(@RequestBody Stock stock){	
		
		System.out.println("Update Stock Service");
		Stock stockgetStockNoResponse = stockService.findStockByName(stock.getProductName());
		
		
		System.out.println("getStockAvailable : " + stockgetStockNoResponse.getStockAvailable());
		

		stockgetStockNoResponse.setStockAvailable(stock.getStockAvailable());
		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/revertStock", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> revertStock(@RequestBody Stock stock){	
		
		Stock stockgetStockNoResponse = stockService.findStockByName(stock.getProductName());
		System.out.println("Revert Stock Service");
		
		
		System.out.println("getStockAvailable : " + stockgetStockNoResponse.getStockAvailable());

		stockgetStockNoResponse.setStockAvailable(stock.getStockAvailable());
		Map<String, String> map = new HashMap<>();
		map.put("status","Fail");

		return new ResponseEntity<Map<String, String>>(map,HttpStatus.OK);
		
			
	}
	
	@RequestMapping(value = "/getStockByName", method = RequestMethod.POST)
	public ResponseEntity<Stock> stockByName(@RequestBody Stock stock){	
		System.out.println("Get Stock by name is called");
		Stock stockgetStockNoResponse = stockService.findStockByName(stock.getProductName());
		
		System.out.println("stockgetStockNoResponse :" + stockgetStockNoResponse);
		return new ResponseEntity<Stock>(stockgetStockNoResponse,HttpStatus.OK);
		
			
	}
	
	

}
