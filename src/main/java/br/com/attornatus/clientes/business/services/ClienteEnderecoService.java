package br.com.attornatus.clientes.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.clientes.domain.repositories.ClienteEnderecoRepository;
import br.com.attornatus.clientes.domain.repositories.ClienteRepository;

@Service
public class ClienteEnderecoService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;
	
	
	

}
