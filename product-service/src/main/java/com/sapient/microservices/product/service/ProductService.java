package com.sapient.microservices.product.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sapient.microservices.product.exception.ProductException;
import com.sapient.microservices.product.exception.ProductStatus;
import com.sapient.microservices.product.exception.ResponseMessage;
import com.sapient.microservices.product.model.Product;
import com.sapient.microservices.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getProductList(Integer pageNumber, Integer pageSize, String query) {
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Product> productsPage = productRepository.findByProductCategoryIgnoreCase(query, pageRequest);
		return productsPage;
	}

	@Transactional(rollbackOn={Exception.class})
	public Product deleteProduct(Long id) throws ProductException {
		Product product = productRepository.findById(id);
		if (product == null) {
			throw new ProductException(new ProductStatus(HttpStatus.BAD_REQUEST, ResponseMessage.PRODUCT_NOT_FOUND));
		}
		productRepository.delete(product);
		return product;
	}

	@Transactional(rollbackOn={Exception.class})
	public Product addProduct(Product product) {
		product = productRepository.save(product);
		return product;
	}
}
