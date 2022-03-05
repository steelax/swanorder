package com.SteelGem.OrderSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.OrderDetails;
import com.SteelGem.OrderSystem.entity.StaffDetails;

public interface StaffDetailsRepository extends JpaRepository<StaffDetails, String>  {

	StaffDetails findByUserId(String Id);
	
}
