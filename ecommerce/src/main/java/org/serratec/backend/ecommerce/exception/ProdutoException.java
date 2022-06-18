package org.serratec.backend.ecommerce.exception;

public class ProdutoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ProdutoException() {
		super();
	}
	
	public ProdutoException(String message) {
		super(message);
	}

	public ProdutoException(String message, Exception cause) {
		super(message, cause);
	}
	
	public ProdutoException(Exception e) {
		super(e);
	}

}
