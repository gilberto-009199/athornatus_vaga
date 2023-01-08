package br.com.attornatus.clientes.domain.entities;


import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClienteEnderecoEntity extends BaseEntity{
	
	
	@Column
	private String cep;
	
	@Column
	private String cidade;
	
	@Column
	private String numero;
	
	@Column
	private String logradouro;
	
	@Column
	private Boolean principal;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private ClienteEntity cliente;
	
	public boolean isPrincipal() {
		return this.principal;
	}
}
