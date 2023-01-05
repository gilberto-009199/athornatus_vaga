package br.com.attornatus.clientes.business.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ClienteDto {
	
	private UUID id;
	private String nome;
	private Date dtNascimento;
	private List<ClienteEnderecoDto> listEnderecos;
	
}
