package br.com.attornatus.clientes.business.services;

import java.util.List;
import java.util.UUID;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;

public interface ClienteEnderecoService {
	
	public List<ClienteEnderecoDto> getAllByCliente(ClienteDto cliente);
	
	public ClienteEnderecoDto getById(UUID id);
	
	public ClienteEnderecoDto create(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco);
	
	public ClienteEnderecoDto update(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco);

	public List<ClienteEnderecoDto> createAllClienteEndereco(ClienteDto clienteDto, List<ClienteEnderecoDto> listEnderecos);

	public void delete(UUID id);
	
}
