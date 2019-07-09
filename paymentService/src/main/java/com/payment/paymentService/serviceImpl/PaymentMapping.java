package com.payment.paymentService.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.payment.paymentService.model.Payment;



public class PaymentMapping implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Payment payment = new Payment();
		payment.setPaymentId(rs.getInt("paymentId"));
		payment.setOrderNo(rs.getInt("orderNo"));
		payment.setProductName(rs.getString("productName"));
		payment.setProductUnits(rs.getDouble("productUnits"));
		payment.setTotalCost(rs.getDouble("totalCost"));
		payment.setPaymentStatus(rs.getString("paymentStatus"));
		
		return payment;
	}

	
}
