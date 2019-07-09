package com.stock.stockService.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.stock.stockService.model.Stock;

public class StockMapper  implements RowMapper  {
	
	@Override
	public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stock stock = new Stock();
		stock.setStockNo(rs.getInt("stockNo"));
		stock.setProductName(rs.getString("productName"));
//		stock.setUnitCost(rs.getDouble("unitCost"));
		stock.setStockAvailable(rs.getDouble("stockAvailable"));
		
		return stock;
	}


}
