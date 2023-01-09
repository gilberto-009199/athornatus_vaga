package br.com.attornatus.clientes.business.services;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.clientes.api.converters.ClienteConverter;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;
import br.com.attornatus.clientes.domain.exception.DomainException;
import br.com.attornatus.clientes.domain.repositories.ClienteEnderecoRepository;
import br.com.attornatus.clientes.domain.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteEnderecoService clienteEnderecoService;

	@Autowired
	private ClienteConverter converter;

	public ClienteDto getById(UUID id) {
		
		ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado"));
		
		return converter.converterEntityToDto( clienteEntity );
	}

	public List<ClienteDto> getAll(){
		
		List<ClienteDto> listClienteResponse = converter.conveterListEntityToListDto( clienteRepository.findAll() );
		
		return listClienteResponse;
	}

	public ClienteDto create(ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.save( converter.converterDtoToEntity( cliente ) );
		
		cliente.setId(clienteEntity.getId());
		
		if(!cliente.getEnderecos().isEmpty()) {
			
			clienteEnderecoService.createAllClienteEndereco( cliente, cliente.getEnderecos());
			
			cliente.setEnderecos( clienteEnderecoService.getAllByCliente( cliente ) );
		
		}
		
		return cliente;
	}

	public ClienteDto update(UUID id, ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado"));
		
		cliente.setId(clienteEntity.getId());
		clienteEntity.setNome( cliente.getNome() );
		clienteEntity.setDtNascimento( cliente.getDtNascimento() );
		
		if(!cliente.getEnderecos().isEmpty()) {
			
			clienteEnderecoService.createAllClienteEndereco( cliente, cliente.getEnderecos());
			
			cliente.setEnderecos( clienteEnderecoService.getAllByCliente( cliente ) );
			
		};

		clienteRepository.save( clienteEntity );
		
		return cliente;
	}

	public void delete(UUID id) {
		
		ClienteEntity clienteEntity = clienteRepository.findById( id ).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado"));
		
		clienteRepository.delete( clienteEntity );
	}
}
