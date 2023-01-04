package br.com.attornatus.clientes.api.request;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ClienteRequest {
	
	private String nome;
	private Date dtNascimento;
	private List<ClienteEnderecoRequest> listEnderecos;
	
}
