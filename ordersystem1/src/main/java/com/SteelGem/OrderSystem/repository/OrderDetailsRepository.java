package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.OrderDetails;


public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	OrderDetails findByOrderItemID(Integer id);
	
	Collection<OrderDetails> findByOrderId(Integer orderId);
	
}
