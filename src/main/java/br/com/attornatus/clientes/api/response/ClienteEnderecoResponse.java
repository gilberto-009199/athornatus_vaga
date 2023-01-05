package br.com.attornatus.clientes.api.response;

import java.util.UUID;

import lombok.Data;

@Data
public class ClienteEnderecoResponse{

	private UUID id;
	private String cep;
	private String cidade;
	private String numero;
	private String logradouro;
	private Boolean principal;

}
