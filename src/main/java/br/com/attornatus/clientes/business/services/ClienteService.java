package br.com.attornatus.clientes.business.services;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;
import br.com.attornatus.clientes.domain.exception.DomainException;
import br.com.attornatus.clientes.domain.repositories.ClienteEnderecoRepository;
import br.com.attornatus.clientes.domain.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<ClienteDto> getAllCliente(){
		
		List<ClienteDto> listClienteResponse = clienteRepository.findAll().stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteDto.class)).collect(Collectors.toList());
		
		return listClienteResponse;
	}
	
	public ClienteDto createCliente(ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.save( mapper.map(cliente, ClienteEntity.class) );
		
		return mapper.map(clienteEntity, ClienteDto.class);
	}
	
	public ClienteDto updateCliente(UUID id, ClienteDto cliente){
		
		ClienteEntity clienteEntity = clienteRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado"));
		
		
		
		return null;
	}
	
	
}
