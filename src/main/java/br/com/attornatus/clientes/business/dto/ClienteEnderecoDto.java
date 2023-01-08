package br.com.attornatus.clientes.business.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ClienteEnderecoDto {
	
	public ClienteEnderecoDto() {};
	public ClienteEnderecoDto(UUID id) {
		this.id = id;
	}
	
	private UUID id;
	private String cep;
	private String cidade;
	private String numero;
	private String logradouro;
	private Boolean principal;
	
	public boolean isPrincipal() {
		return this.principal;
	}

}
