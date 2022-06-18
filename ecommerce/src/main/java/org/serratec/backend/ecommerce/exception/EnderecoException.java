package org.serratec.backend.ecommerce.exception;

public class EnderecoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public EnderecoException() {
		super();
	}
	
	public EnderecoException(String message) {
		super(message);
	}

	public EnderecoException(String message, Exception cause) {
		super(message, cause);
	}
	
	public EnderecoException(Exception e) {
		super(e);
	}
}
