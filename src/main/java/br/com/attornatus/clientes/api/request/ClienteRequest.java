package br.com.attornatus.clientes.api.request;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteRequest{

	@NotBlank(message = "{NotBlank.cliente.nome}")
	private String nome;

	@NotNull(message = "{NotNull.cliente.dtNascimento}")
	private Date dtNascimento;
	
	private List<ClienteEnderecoRequest> listEnderecos;
	
}
