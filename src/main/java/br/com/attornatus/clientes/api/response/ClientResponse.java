package br.com.attornatus.clientes.api.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ClientResponse {
	
	private String nome;
	private Date dtNascimento;
	private List<ClienteEnderecoResponse> listEnderecos;
	
}
