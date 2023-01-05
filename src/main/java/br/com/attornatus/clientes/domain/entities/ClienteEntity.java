package br.com.attornatus.clientes.domain.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	
	// Carregamento Sujo para facilitar
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ClienteEnderecoEntity> listEnderecos = new ArrayList();
	
}
