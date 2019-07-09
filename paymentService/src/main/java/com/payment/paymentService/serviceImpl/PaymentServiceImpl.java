package com.payment.paymentService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.payment.paymentService.model.Payment;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	
	private final String INSERT_SQL_PAYMENT = "INSERT INTO `dbtest`.`payments`(`paymentId`,`orderNo`,`productName`,`productUnits`,`totalCost`,`paymentStatus`) VALUES(:paymentId,:orderNo,:productName,:productUnits,:totalCost,:paymentStatus);";
	private final String select_SQL_PAYMENT = "select * from payments";
	private final String update_SQL_PAYMENT = "UPDATE payments SET paymentStatus = :paymentStatus where paymentId= :paymentId";	
	private final String max_SQL_PAYMENT = "SELECT * FROM `payments` WHERE paymentId=(SELECT MAX(paymentId) FROM `payments`)";
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static final AtomicInteger counter = new AtomicInteger();
	private static List<Payment> paymentList = new ArrayList<>();

	@Override
	public Payment getPaymentId() {
		paymentList = namedParameterJdbcTemplate.query(max_SQL_PAYMENT, new PaymentMapping());	
		for (Payment payment : paymentList)
			if(payment.getPaymentId() >= 1) {
				System.out.println("IF");
				return payment;
			}
			else {
				System.out.println("Esle");
				payment.setPaymentId(0);
				return payment;
			}
		
		
		System.out.println("Return null");
		
		return null;
		
	}

	@Override
	public void createPay(Payment payment) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("paymentId", payment.getPaymentId())
				.addValue("orderNo", payment.getOrderNo())
				.addValue("productName", payment.getProductName())
				.addValue("productUnits", payment.getProductUnits())
				.addValue("totalCost", payment.getTotalCost())
				.addValue("paymentStatus", payment.getPaymentStatus());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(INSERT_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + payment);
	
		
	}

	@Override
	public List<Payment> getAllPaymentsDetails() {
		paymentList = namedParameterJdbcTemplate.query(select_SQL_PAYMENT, new PaymentMapping());
		return paymentList;
	}

	@Override
	public void updatePaymentStatus(Payment payment) {
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("paymentId", payment.getPaymentId())
				.addValue("paymentStatus", payment.getPaymentStatus());
		namedParameterJdbcTemplate.update(update_SQL_PAYMENT, parameters);
		System.out.println("Payments Details :" + payment);
		
	}
	
	
	
	

/*	private static final AtomicInteger counter = new AtomicInteger();
	private List<Payment> paymentList = new ArrayList<>();
	
	@Override
	public void createPay(Payment payment) {
			
			payment.setPaymentId(counter.incrementAndGet());
			paymentList.add(payment);
		
	}

	@Override
	public List<Payment> getAllPaymentsDetails() {		
		return paymentList;
	}

	@Override
	public Payment findPaymentDetailsByID(int paymentId) {
		for(Payment payment : paymentList)
			if(payment.getPaymentId() == paymentId)
			{
				return payment;
			}
		return null;
	}
	
*/
}
