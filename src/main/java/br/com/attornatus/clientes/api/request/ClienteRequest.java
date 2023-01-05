package br.com.attornatus.clientes.api.request;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteRequest{

	@NotBlank(message = "Nome é Obrigatorio")
	private String nome;

	@NotNull(message = "dtNasciemnto é Obrigatorio")
	private Date dtNascimento;
	
	private List<ClienteEnderecoRequest> listEnderecos;
	
}
