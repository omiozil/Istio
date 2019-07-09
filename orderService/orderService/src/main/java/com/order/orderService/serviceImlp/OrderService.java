package com.order.orderService.serviceImlp;

import java.util.List;
import com.order.orderService.model.Order;

public interface OrderService {

	
	Order getOrderNo();
	void createOrder(Order order);
	List<Order> getAllOrder();
	
	void updateOrder(Order order);
	
	
	
/*	void createOrder(Order order);
	
	List<Order> findAllOrder();
	
	Order findOrderById(int orderNo);
*/	
}
