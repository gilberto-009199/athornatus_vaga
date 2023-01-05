package br.com.attornatus.clientes.domain.exception;

public class DomainException extends RuntimeException {

	private String message;
	private String code;
	
    public DomainException() {
        super();
    }

    public DomainException(String message) {
        super(message);
        this.message = message;
    }
    
    public DomainException(String code, String message) {
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
