package br.com.attornatus.clientes.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteEnderecoRequest {

	@NotBlank(message = "{cliente.endereco.cep.notblank}")
	@Size(min = 9, max = 9, message = "{cliente.endereco.cep.size}")
	private String cep;

	@NotBlank(message = "{cliente.endereco.cidade.notblank}")
	@Size(min = 4, max = 255, message = "{cliente.endereco.cidade.size}")
	private String cidade;

	@NotBlank(message = "{cliente.endereco.numero.notblank}")
	@Size(min = 1, max = 255, message = "{cliente.endereco.numero.size}")
	private String numero;

	@NotBlank(message = "{cliente.endereco.logradouro.notblank}")
	@Size(min = 4, max = 255, message = "{cliente.endereco.logradouro.size}")
	private String logradouro;

	private Boolean principal;

	public ClienteEnderecoRequest() {}
	public ClienteEnderecoRequest(String cep, String cidade, String numero, String logradouro, Boolean principal) {
		this.cep = cep;
		this.cidade = cidade;
		this.numero = numero;
		this.logradouro = logradouro;
		this.principal = principal;
	}
	
}
