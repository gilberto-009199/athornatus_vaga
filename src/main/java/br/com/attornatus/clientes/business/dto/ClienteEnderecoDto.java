package br.com.attornatus.clientes.business.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ClienteEnderecoDto {
	
	private UUID id;
	private String cep;
	private String cidade;
	private String numero;
	private String logradouro;
	private Boolean principal;

}
