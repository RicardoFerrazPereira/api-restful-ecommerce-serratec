package org.serratec.backend.ecommerce.exception;

public class MovimentacaoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public MovimentacaoException() {
		super();
	}
	
	public MovimentacaoException(String message) {
		super(message);
	}

	public MovimentacaoException(String message, Exception cause) {
		super(message, cause);
	}
	
	public MovimentacaoException(Exception e) {
		super(e);
	}

}
