package com.sapient.microservices.product.exception;

import org.springframework.http.HttpStatus;

public class ResponseMessage {

	public static final String PAGE_VALIDATION_FAILED = "Unexpected PageSize or PageStart";
	public static final String PRODUCT_NOT_FOUND = "Product Not Found.";

	public static final ProductStatus INTERNAL_SERVER_ERROR_RESPONSE = new ProductStatus(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
}
