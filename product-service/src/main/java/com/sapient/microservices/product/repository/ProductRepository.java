package com.sapient.microservices.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sapient.microservices.product.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Page<Product> findByProductCategoryIgnoreCase(String productCategory, Pageable pageable);
	Product findById(Long id);
}
