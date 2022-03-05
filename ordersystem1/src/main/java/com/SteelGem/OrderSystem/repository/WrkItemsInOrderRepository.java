package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.WrkItemsInOrder;


public interface WrkItemsInOrderRepository extends JpaRepository<WrkItemsInOrder, Integer> {

	WrkItemsInOrder findByOrderItemID(Integer orderItemID);
	Collection<WrkItemsInOrder> findByCustomerID(Integer customerID);
	WrkItemsInOrder findByItemGenericID(Integer itemGenericID);
	
	Collection<WrkItemsInOrder> findByCustomerIDAndCancelledFlag(Integer customerID,String cancelledFlag);
	
	Collection<WrkItemsInOrder> findByParentItemNoAndCustomerID(Integer parentItemNo, Integer customerID);
	
	Collection<WrkItemsInOrder> findByCancelledFlag(String cancelledFlag);
}
