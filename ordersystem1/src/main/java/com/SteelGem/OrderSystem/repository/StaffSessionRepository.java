package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.StaffSession;

public interface StaffSessionRepository extends JpaRepository<StaffSession, String> {

	StaffSession findByUserId(String userId);
	
	StaffSession findBySessionID(String sessionID);

}