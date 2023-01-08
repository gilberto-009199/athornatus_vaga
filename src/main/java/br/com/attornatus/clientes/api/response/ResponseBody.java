package br.com.attornatus.clientes.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseBody<T> {

	public ResponseBody() {}
	
	public ResponseBody(T message) {
		this.message = message;
	}
	
	public ResponseBody(ResponseError error) {
		this.error.add(error);
	}
	
	public ResponseBody(List<ResponseError> error) {
		this.error.addAll(error);
	}
	
	private T message;
	
	private List<ResponseError> error = new ArrayList<>();
	
}