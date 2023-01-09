package br.com.attornatus.clientes.business.services;

import java.util.List;
import java.util.UUID;

import br.com.attornatus.clientes.business.dto.ClienteDto;


public interface ClienteService {
	
	public ClienteDto getById(UUID id);

	public List<ClienteDto> getAll();

	public ClienteDto create(ClienteDto cliente);

	public ClienteDto update(UUID id, ClienteDto cliente);

	public void delete(UUID id);
}
