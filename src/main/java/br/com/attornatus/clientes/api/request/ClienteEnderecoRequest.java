package br.com.attornatus.clientes.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteEnderecoRequest {

	@NotBlank(message = "{NotBlank.cliente.endereco.cep}")
	private String cep;

	@NotBlank(message = "{NotBlank.cliente.endereco.cidade}")
	private String cidade;

	@NotBlank(message = "{NotBlank.cliente.endereco.numero}")
	private String numero;

	@NotBlank(message = "{NotBlank.cliente.endereco.logradouro}")
	private String logradouro;

	private Boolean principal;

}
