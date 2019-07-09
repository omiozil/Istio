package com.stock.stockService.serviceImpl;

import java.util.List;

import com.stock.stockService.model.Stock;

public interface StockService {
	

	
/*	void createStock(Stock stock);
	List<Stock> getAllStock();
	void updateStock(Stock stock);
	Stock getStockByName(String productName);
	*/	
	void createStock(Stock stock);
	
	List<Stock> findAllStock();
	
	Stock findStockById(int stockNo);
	
	Stock findStockByName(String productName);

}
