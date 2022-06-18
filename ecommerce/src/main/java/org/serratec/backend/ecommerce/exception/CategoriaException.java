package org.serratec.backend.ecommerce.exception;

public class CategoriaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public CategoriaException() {
		super();
	}
	
	public CategoriaException(String message) {
		super(message);
	}

	public CategoriaException(String message, Exception cause) {
		super(message, cause);
	}
	
	public CategoriaException(Exception e) {
		super(e);
	}

}
