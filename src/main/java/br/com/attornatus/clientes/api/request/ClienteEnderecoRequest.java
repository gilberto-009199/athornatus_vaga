package br.com.attornatus.clientes.api.request;

import lombok.Data;

@Data
public class ClienteEnderecoRequest {
	
	private String cep;
	private String cidade;
	private String numero;
	private String logradouro;
	private Boolean principal;
	
}
