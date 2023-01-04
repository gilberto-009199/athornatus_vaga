package br.com.attornatus.clientes.domain.entities;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ClienteEntity extends BaseEntity{
	
	@Column
	private String nome;
	
	@Column
	private Date dtNascimento;
	
	@OneToMany(mappedBy = "cliente")
	private List<ClienteEnderecoEntity> listEnderecos;
	
}
