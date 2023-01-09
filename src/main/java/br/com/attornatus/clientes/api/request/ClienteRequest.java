package br.com.attornatus.clientes.api.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequest{
	
	@NotBlank(message = "{cliente.nome.notblank}")
	@Size(min = 4, max = 255, message = "{cliente.nome.size}")
	private String nome;

	@NotNull(message = "{cliente.dtNascimento.notnull}")
	private LocalDate dtNascimento;

	@Valid
	@Size(max = 10, message = "{cliente.enderecos.size}")
	private List<ClienteEnderecoRequest> enderecos = new ArrayList();
	
	
	public ClienteRequest() {}
	public ClienteRequest(String nome, LocalDate dtNascimento) {
		this.nome = nome;
		this.dtNascimento = dtNascimento;
	}
	
}
