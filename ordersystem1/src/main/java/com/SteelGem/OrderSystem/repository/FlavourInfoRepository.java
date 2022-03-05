package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SteelGem.OrderSystem.entity.FlavourInfo;

public interface FlavourInfoRepository extends JpaRepository<FlavourInfo, Integer> {

	FlavourInfo findByFlavourID(Integer flavourID);
	
	FlavourInfo findByFlavourName(Integer flavourName);

	Collection<FlavourInfo> findByDrinkID(Integer drinkID);	
}