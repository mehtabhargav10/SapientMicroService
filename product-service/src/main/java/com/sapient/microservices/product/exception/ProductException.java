package com.sapient.microservices.product.exception;

public class ProductException extends Exception {

	private static final long serialVersionUID = -3517818172907801550L;

	private ProductStatus code;

	public ProductException() {
	}

	public ProductException(String message){
		super(message);
	}

	public ProductException(Throwable th){
		super(th);
	}

	public ProductException(String message,Throwable th){
		super(message,th);
	}

	public ProductException(ProductStatus code,Throwable e,String message) {
		super(message,e);
		this.code = code;
	}

	public ProductException(ProductStatus code,String message) {
		super(message);
		this.code = code;
	}

	public ProductException(ProductStatus codes) {
		super(codes.getMsg());
		this.code = codes;
	}

	public ProductStatus getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "FaceLingoException [code=" + code + ", Details()="+ super.toString() + "]";
	}

}
