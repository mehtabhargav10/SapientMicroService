package com.sapient.microservices.product.rest;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.microservices.product.exception.ProductException;
import com.sapient.microservices.product.exception.ProductStatus;
import com.sapient.microservices.product.exception.ResponseMessage;
import com.sapient.microservices.product.model.Product;
import com.sapient.microservices.product.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	private ProductService productService;

	protected Logger logger = Logger.getLogger(ProductRestController.class.getName());

	@RequestMapping(value="/product/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> findByNumber(@PathVariable("id") Long id) {
		try {
			Product product = productService.deleteProduct(id);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity<>(ResponseMessage.INTERNAL_SERVER_ERROR_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/products", method=RequestMethod.GET)
	public ResponseEntity<Object> listTopics(HttpSession httpSession,
			@RequestParam(value="pageStart", required = false, defaultValue="1") Integer pageStart,
			@RequestParam(value="pageSize", required = false, defaultValue="10") Integer pageSize,
			@RequestParam(value="productType", required = false, defaultValue="") String query) {
		try {
			if (pageStart < 0 || pageSize < 1 || pageSize > 100) {
				throw new ProductException(new ProductStatus(HttpStatus.BAD_REQUEST, ResponseMessage.PAGE_VALIDATION_FAILED));
			}
			Page<Product> topicList = productService.getProductList(pageStart-1, pageSize, query);
			return new ResponseEntity<>(topicList, HttpStatus.OK);
		} catch (ProductException e) {
			return new ResponseEntity<>(e.getCode(), e.getCode().getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity<>(ResponseMessage.INTERNAL_SERVER_ERROR_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/product", method=RequestMethod.POST)
	public ResponseEntity<Object> addTopic(@RequestBody Product product) {
		try {
			product = productService.addProduct(product);
			return new ResponseEntity<>(product, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(ResponseMessage.INTERNAL_SERVER_ERROR_RESPONSE, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
