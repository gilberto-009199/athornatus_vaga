package br.com.attornatus.clientes.business.services;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private ModelMapper mapper;
	
	public List<ClienteDto> getAll(){
		
		List<ClienteDto> listClienteResponse = clienteRepository.findAll().stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteDto.class)).collect(Collectors.toList());
		
		return listClienteResponse;
	}
	
	public ClienteDto create(ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.save( mapper.map(cliente, ClienteEntity.class) );
		
		cliente.setId(clienteEntity.getId());
		
		if(!cliente.getListEnderecos().isEmpty()) {
			
			clienteEnderecoService.createAllClienteEndereco( cliente, cliente.getListEnderecos());
			
			cliente.setListEnderecos( clienteEnderecoService.getAllByCliente( cliente ) );
		
		}
		
		return cliente;
	}
	
	public ClienteDto update(UUID id, ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado"));
		
		cliente.setId(clienteEntity.getId());
		clienteEntity.setNome( cliente.getNome() );
		clienteEntity.setDtNascimento( cliente.getDtNascimento() );
		
		if(!cliente.getListEnderecos().isEmpty()) {
			
			clienteEnderecoService.createAllClienteEndereco( cliente, cliente.getListEnderecos());
			
			cliente.setListEnderecos( clienteEnderecoService.getAllByCliente( cliente ) );
			
		};

		clienteRepository.save( clienteEntity );
		
		return cliente;
	}
	
	
}
