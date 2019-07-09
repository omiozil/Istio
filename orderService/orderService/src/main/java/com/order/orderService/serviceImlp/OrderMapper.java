package com.order.orderService.serviceImlp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.orderService.model.Order;

class OrderMapper implements RowMapper {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderNo(rs.getInt("orderNo"));
		order.setProductName(rs.getString("productName"));
		order.setProductUnits(rs.getInt("productUnits"));
		order.setTotalCost(rs.getDouble("totalCost"));
		order.setOrderStatus(rs.getString("orderStatus"));
		
		return order;
	}



}
