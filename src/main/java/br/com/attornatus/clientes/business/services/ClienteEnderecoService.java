package br.com.attornatus.clientes.business.services;

import java.util.List;
import java.util.UUID;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.exception.DomainException;

public interface ClienteEnderecoService {
	/**
	 * Metodo para pegar todos os enderecos de um Cliente
	 * @param cliente - Cliente
	 * @return List<ClienteEnderecoDto> - Lista de enderecos correspondente ao Cliente
	 * @throws DomainException - Cliente nao encontrado
	 */
	public List<ClienteEnderecoDto> getAllByCliente(ClienteDto cliente);

	/**
	 * Metodo para um Endereco pelo Id
	 * @param id - Identificador do Endereco
	 * @return ClienteEnderecoDto - Endereco correspondente ao Id
	 * @throws DomainException - Endereco nao encontrado
	 */
	public ClienteEnderecoDto getById(UUID id);

	/**
	 * Metodo para um Endereco pelo Id
	 * @param clienteDto - Cliente dono do endereco
	 * @param clienteEndereco - Endereco que deve ser criado
	 * @return ClienteEnderecoDto - Endereco correspondente ao Id
	 * @throws BusinessException - Nao e permitido dois enderecos Principais
	 */
	public ClienteEnderecoDto create(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco);

	/**
	 * Método para atualizar um Endereco
	 * @param clienteDto - Cliente dono do endereco
	 * @param clienteEndereco - Endereco com os dados que devem ser atualizados
	 * @return ClienteEnderecoDto - Endereco atualizado
	 * @throws DomainException - Endereco nao encontrado
	 * @throws BusinessException - Nao e permitido dois enderecos Principais
	 */
	public ClienteEnderecoDto update(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco);

	/**
	 * Método para criar multiplos enderecos
	 * @param clienteDto - Cliente dono dos enderecos
	 * @param listEnderecos - Lista de enderecos que sera criada
	 * @return List<ClienteEnderecoDto> - Lista com os enderecos criados
	 * @throws BusinessException - Nao e permitido dois enderecos Principais
	 */
	public List<ClienteEnderecoDto> createAll(ClienteDto clienteDto, List<ClienteEnderecoDto> listEnderecos);

	/**
	 * Metodo para remover um endereco
	 * @param id - Identificador do endereco que sera removido
	 */
	public void delete(UUID id);
	
}
