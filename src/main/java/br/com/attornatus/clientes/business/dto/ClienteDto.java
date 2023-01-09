package br.com.attornatus.clientes.business.dto;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ClienteDto {
	
	public ClienteDto() {}
	public ClienteDto(UUID id) {
		this.id = id;
	}
	private UUID id;
	private String nome;
	private LocalDate dtNascimento;
	private List<ClienteEnderecoDto> enderecos = new ArrayList<ClienteEnderecoDto>();
	
}
