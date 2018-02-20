/******************************************************* * Copyright (C) 2016 FlickBay
 *
 * This file is part of FlickBay-2.
 *
 * FlickBay-2 can not be copied and/or distributed without the express
 * permission of FlickBay
 *
 * Original Author: bhargav.mehta
 *******************************************************/
package com.sapient.microservices.product.exception;

import org.springframework.http.HttpStatus;

public class ProductStatus {

	private HttpStatus httpStatus;
	private String msg;

	public ProductStatus(HttpStatus httpStatus, String msg) {
		this.httpStatus = httpStatus;
		this.msg = msg;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "FaceLingoStatus [" + (httpStatus != null ? "httpStatus=" + httpStatus + ", " : "")
				+ (msg != null ? "msg=" + msg : "") + "]";
	}
}
