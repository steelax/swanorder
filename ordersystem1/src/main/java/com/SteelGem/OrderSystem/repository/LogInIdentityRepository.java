package com.SteelGem.OrderSystem.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.LogInIdentity;

public interface LogInIdentityRepository extends JpaRepository<LogInIdentity, Integer> {

	LogInIdentity findByCustomerID(Integer id);
	LogInIdentity findByUpdatedOn(Date updatedOn);
	
}
