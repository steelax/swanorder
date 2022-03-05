package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.ConfirmedOrders;
import com.SteelGem.OrderSystem.entity.ProductInfo;

public interface ConfirmedOrdersRepository extends JpaRepository<ConfirmedOrders, Integer> {

	ConfirmedOrders findByOrderID(Integer id);
	
}
