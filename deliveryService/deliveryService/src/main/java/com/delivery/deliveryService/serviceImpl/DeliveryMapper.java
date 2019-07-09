package com.delivery.deliveryService.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.delivery.deliveryService.model.Delivery;






public class DeliveryMapper implements RowMapper<Delivery> {
	
	@Override
	public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {
		Delivery delivery = new Delivery();
		delivery.setDeliveryId(rs.getInt("deliveryId"));
		delivery.setDeliveryTimeTakeninDays(rs.getInt("deliveryTimeTakeninDays"));
		delivery.setStatus(rs.getString("status"));
	
		
		return delivery;
	}

}
