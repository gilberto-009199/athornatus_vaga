package br.com.attornatus.clientes.business.exception;

public class BusinessException extends RuntimeException {

	private String message;
	private String code;
	
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
    
    public BusinessException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void setCode(String code) {
    	this.code = code;
    }
    
    public String getCode() {
    	return this.code;
    }
}
