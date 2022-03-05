package com.SteelGem.OrderSystem.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SteelGem.OrderSystem.entity.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Integer> {

	ProductInfo findByIds(Integer id);

	Collection<ProductInfo> findBySubCategory(Integer subCategory);
	
	Collection<ProductInfo> findByCategory(Integer category);
	
	ProductInfo findByName (String name);
	
}