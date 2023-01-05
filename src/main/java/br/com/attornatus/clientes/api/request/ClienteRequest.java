package br.com.attornatus.clientes.api.request;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequest{

	@NotBlank(message = "Nome is mandatory")
	private String nome;
	private Date dtNascimento;
	private List<ClienteEnderecoRequest> listEnderecos;
	
}
