package br.com.attornatus.clientes.api.response;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ClienteResponse{
	
	private UUID id;
	private String nome;
	private Date dtNascimento;
	private List<ClienteEnderecoResponse> enderecos;
	
}
