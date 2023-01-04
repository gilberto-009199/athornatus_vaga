package br.com.attornatus.clientes.api.response;

import lombok.Data;

@Data
public class ClienteEnderecoResponse {
	
	private String cep;
	private String cidade;
	private String numero;
	private String logradouro;
	private Boolean principal;
	
}
