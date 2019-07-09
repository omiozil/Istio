package com.delivery.deliveryService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.delivery.deliveryService.model.Delivery;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

	private final String INSERT_SQL_PAYMENT = "INSERT INTO `dbtest`.`delivery`(`deliveryId`,`deliveryTimeTakeninDays`,`status`) VALUES(:deliveryId,:deliveryTimeTakeninDays,:status);";
	private final String select_SQL_PAYMENT = "select * from delivery";
	private final String update_SQL_PAYMENT = "UPDATE delivery SET status = :status where deliveryId= :deliveryId";
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static List<Delivery> deliveryList = new ArrayList<>();

	
	
	@Override
	public void createDelivery(Delivery delivery) {
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("deliveryId", counter.incrementAndGet())
				.addValue("deliveryTimeTakeninDays", delivery.getDeliveryTimeTakeninDays())
				.addValue("status", delivery.getStatus());
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(INSERT_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + delivery);
	}

	@Override
	public List<Delivery> findAllDelivery() {
		deliveryList = namedParameterJdbcTemplate.query(select_SQL_PAYMENT, new DeliveryMapper());		
		return deliveryList;
	}

	@Override
	public void updateDeliveryStatus(Delivery delivery) {
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("deliveryId", delivery.getDeliveryId())
				.addValue("status", "Failed");
				//.addValue("status", order.getOrderStatus());
		namedParameterJdbcTemplate.update(update_SQL_PAYMENT, parameters);
		//order.setOrderNo(holder.getKey().intValue());
		System.out.println("Payments Details :" + delivery);
		
	}

	
	
	
	
	/*private List<Delivery> deliveryList = new ArrayList<>();

	@Override
	public void createDelivery(Delivery delivery) {

		deliveryList.add(delivery);

	}

	@Override
	public List<Delivery> findAllDelivery() {
		// TODO Auto-generated method stub
		return deliveryList;
	}

	@Override
	public Delivery findDeliveryByID(int deliveryId) {
		for (Delivery delivery : deliveryList)
			if (delivery.getDeliveryId() == deliveryId) {
				return delivery;
			}
		return null;
	}*/

}
