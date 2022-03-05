package com.SteelGem.OrderSystem.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.CheckInIdentity;

public interface CheckInIdentityRepository extends JpaRepository<CheckInIdentity, Integer> {

	CheckInIdentity findByCustomerID(Integer id);
	CheckInIdentity findByUpdatedOn(Date updatedOn);
	
	
}