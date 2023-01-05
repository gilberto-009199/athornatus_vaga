package br.com.attornatus.clientes.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteEnderecoRequest {
	
	@NotBlank(message = "CEP é Obrigatorio")
	private String cep;
	@NotBlank(message = "Cidade é Obrigatorio")
	private String cidade;
	@NotBlank(message = "numero é Obrigatorio")
	private String numero;
	@NotBlank(message = "Logradouro é Obrigatorio")
	private String logradouro;
	
	private Boolean principal;

}
