package br.com.attornatus.clientes.api.response;

import lombok.Data;

@Data
public class ResponseError {
	
	public ResponseError(String error, String message) {
		this.error = error;
		this.message = message;
	}
	
	private String error;
	private String message;
	
}
