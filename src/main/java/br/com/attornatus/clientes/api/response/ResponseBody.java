package br.com.attornatus.clientes.api.response;

import lombok.Data;

@Data
public class ResponseBody<T> {

	public ResponseBody() {}
	
	public ResponseBody(T message) {
		this.message = message;
	}
	
	public ResponseBody(ResponseError error) {
		this.error = new ResponseError[] { error };
	}
	
	public ResponseBody(ResponseError[] error) {
		this.error = error;
	}
	
	private T message;
	
	private ResponseError[] error;
	
}