package br.com.attornatus.clientes.api.exception;

public class APIException extends RuntimeException {

	private String message;
	private String code;
	
    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
        this.message = message;
    }
    
    public APIException(String message, String code) {
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
