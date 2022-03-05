package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SteelGem.OrderSystem.entity.AddonFlags;

public interface AddonFlagsRepository extends JpaRepository<AddonFlags, Integer> {

	AddonFlags findByIds(Integer id);

}