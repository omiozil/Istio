package com.order.orderService.serviceImlp;


import java.util.ArrayList;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;


import com.order.orderService.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	

	private final String INSERT_SQL_PAYMENT = "INSERT INTO `dbtest`.`orders`(`orderNo`,`productName`,`productUnits`,`totalCost`,`orderStatus`) VALUES(:orderNo,:productName,:productUnits,:totalCost,:orderStatus);";
	private final String select_SQL_PAYMENT = "select * from orders";
	private final String update_SQL_PAYMENT = "UPDATE orders SET orderStatus = :orderStatus where orderNo= :orderNo";
	private final String max_SQL_PAYMENT = "SELECT * FROM `orders` WHERE orderNo=(SELECT MAX(orderNo) FROM `orders`)";
	
	
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	
	
	private static List<Order> orders = new ArrayList<>();

	
	
	
	@Override
	public void createOrder(Order order) {		
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("orderNo", order.getOrderNo())
				.addValue("productName", order.getProductName())
				.addValue("productUnits", order.getProductUnits())
				.addValue("totalCost", order.getTotalCost())
				.addValue("orderStatus", order.getOrderStatus());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(INSERT_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + order);
		
	}
	
	@Override
	public List<Order> getAllOrder() {		
				
		orders = namedParameterJdbcTemplate.query(select_SQL_PAYMENT, new OrderMapper());		
		return orders;
		
	}
	
	
	@Override
	public void updateOrder(Order order) {		
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("orderNo", order.getOrderNo())
				.addValue("orderStatus", order.getOrderStatus());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(update_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + order);
		
	}

	@Override
	public Order getOrderNo() {
		
		orders = namedParameterJdbcTemplate.query(max_SQL_PAYMENT, new OrderMapper());	
		for (Order order : orders)
			if(order.getOrderNo() >= 1) {
				System.out.println("IF");
				return order;
			}
			else {
				System.out.println("Esle");
				order.setOrderNo(0);
				return order;
			}
		
		
		System.out.println("Return null");
		
		return null;
	}
	
	
	
	
	
	
	
/*	Order order;
	private static final AtomicInteger counter = new AtomicInteger();
	private static List<Order> orders = new ArrayList<>();
	
	
	@Override
	public void createOrder(Order order) {
		order.setOrderNo(counter.incrementAndGet());
		orders.add(order);
	}
	
	@Override
	public List<Order> findAllOrder() {
		return orders;
	}
	
	public Order findOrderById(int orderNo) {
		for(Order order : orders){
			if(order.getOrderNo() == orderNo){
				return order;
			}
		}
		return null;
	}

*/
}


