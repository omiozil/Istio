package com.stock.stockService.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stockService.model.Stock;


@Service("stockService")
public class StockServiceImpl implements StockService{
	
	//private static List<Stock> stockList = new ArrayList<>();
	//private static final AtomicInteger counter = new AtomicInteger();

	
/*	private final String INSERT_SQL_PAYMENT = "INSERT INTO `dbtest`.`stocks`(`stockNo`,`productName`,`stockAvailable`) VALUES(:stockNo,:productName,:stockAvailable)";
	private final String select_SQL_PAYMENT = "select * from stocks";
	private final String getstockByName_SQL_PAYMENT = "select * from stocks where productName = :productName";
	private final String update_SQL_PAYMENT = "UPDATE stocks SET stockAvailable = :stockAvailable WHERE productName = :productName";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public void createStock(Stock stock) {
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("stockNo", counter.incrementAndGet())
				.addValue("productName", stock.getProductName())
				.addValue("stockAvailable", stock.getStockAvailable());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(INSERT_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + stock);
		
	}
	@Override
	public List<Stock> getAllStock() {
		stockList = namedParameterJdbcTemplate.query(select_SQL_PAYMENT, new StockMapper());		
		return stockList;
	}
	
	@Override
	public void updateStock(Stock stock) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("productName", stock.getProductName())
				.addValue("stockAvailable", stock.getStockAvailable());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(update_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + stock);
		
	}
	
	
	@Override
	public Stock getStockByName(String productName) {
		
		
		System.out.println("getStockByName is Called.");
		System.out.println("ProductName::" + productName);
		//stockList =  namedParameterJdbcTemplate.query(getstockByName_SQL_PAYMENT, new StockMapper());
		SqlParameterSource productName1 = new MapSqlParameterSource()
				.addValue("productName", productName);
		
		
		stockList = namedParameterJdbcTemplate.query(getstockByName_SQL_PAYMENT, productName1, new StockMapper());
		
		for(Stock stock : stockList)
		{
			if(stock.getProductName().equals(productName))
				return stock;			
		}
				
		return null;		
		
	}*/
	
	
	  
	private static List<Stock> stockList = new ArrayList<>();
	

	
	
	static{
		stockList= populateDummyStock();
	}
	
	private static int counter = stockList.size();

	
	@Override
	public void createStock(Stock stock) {		
		
		System.out.println(counter);
		counter ++;
		stock.setStockNo(counter);
		stockList.add(stock);
		
	}

	@Override
	public List<Stock> findAllStock() {
		
		return stockList;
	}

	@Override
	public Stock findStockById(int stockNo) {
		
		for(Stock stock : stockList)
			if(stock.getStockNo() == stockNo) {
				return stock;
			}
		return null;
	}

	@Override
	public Stock findStockByName(String productName) {
		for(Stock stock : stockList)
			if(stock.getProductName().equals(productName)) {
				return stock;
			}
		return null;
	}
	
	private static List<Stock> populateDummyStock(){
		stockList.add(new Stock(1,"HardwareDevice",10));
		stockList.add(new Stock(2,"SoftwareDevice",20));
		stockList.add(new Stock(3,"CloudSubcriptions",15));
		return stockList;
	}

}
