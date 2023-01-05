package br.com.attornatus.clientes.api.response;

import lombok.Data;

@Data
public class ResponseError {
	
	public ResponseError(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String code;
	private String message;
	
}
