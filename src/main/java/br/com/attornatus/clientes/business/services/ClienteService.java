package br.com.attornatus.clientes.business.services;

import java.util.List;
import java.util.UUID;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.domain.exception.DomainException;

public interface ClienteService {
	/**
	 * Metodo para pegar um cliente pelo Id dele
	 * @param id - Identificador do cliente
	 * @return ClienteDto - Cliente correspondente ao Id
	 * @throws DomainException - Cliente nao encontrado
	 */
	public ClienteDto getById(UUID id);

	/**
	 * Metodo para pegar todos os clientes
	 * @return List<ClienteDto> - Lista com todos os clientes na sistema
	 */
	public List<ClienteDto> getAll();

	/**
	 * Metodo para criar um cliente
	 * @param cliente - Cliente que sera criado
	 * @return ClienteDto - Cliente criado
	 */
	public ClienteDto create(ClienteDto cliente);

	/**
	 * Metodo para criar um cliente
	 * @param id - Identificador do cliente que sera atualizado
	 * @param cliente - Cliente com os novos dados
	 * @return ClienteDto - Cliente com os dados atualizados
	 * @throws DomainException - Cliente nao encontrado
	 */
	public ClienteDto update(UUID id, ClienteDto cliente);

	/**
	 * Metodo para remover um cliente
	 * @param id - Identificador do cliente que sera removido
	 */
	public void delete(UUID id);
}
