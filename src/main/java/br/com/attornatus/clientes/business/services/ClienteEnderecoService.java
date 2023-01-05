package br.com.attornatus.clientes.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;
import br.com.attornatus.clientes.domain.exception.DomainException;
import br.com.attornatus.clientes.domain.repositories.ClienteEnderecoRepository;
import br.com.attornatus.clientes.domain.repositories.ClienteRepository;

@Service
public class ClienteEnderecoService {
		
	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;
	
	@Autowired
	private ModelMapper mapper;

	public List<ClienteEnderecoDto> createAllClienteEndereco(ClienteDto clienteDto, List<ClienteEnderecoDto> listEnderecos) {
		
		ClienteEntity clienteEntity = mapper.map(clienteDto, ClienteEntity.class);
		
		List<ClienteEnderecoEntity> listClienteEndereco = new ArrayList<>();
		
		int countClienteEnderecoPrincipais = 0;

		for( ClienteEnderecoDto clienteEnderecoDto : listEnderecos) {
				
				ClienteEnderecoEntity clienteEnderecoEntity = mapper.map(clienteEnderecoDto, ClienteEnderecoEntity.class);
				
				clienteEnderecoEntity.setCliente( clienteEntity );
				
				listClienteEndereco.add(clienteEnderecoEntity);
				
				if( clienteEnderecoEntity.getPrincipal() )countClienteEnderecoPrincipais++;
				
		}
		// Validacao Multiplos Enderecos Principais
		if(countClienteEnderecoPrincipais > 1) throw new BusinessException("BUS_Valid","business.cliente.endereco.doisprincipais");
		
		clienteEnderecoRepository.removeAllByCliente( clienteEntity  );
		
		return clienteEnderecoRepository.saveAll(listClienteEndereco).stream().map(clienteEnderecoEntity -> mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());
		
	}

	public List<ClienteEnderecoDto> getAllByCliente(ClienteDto cliente) {

		return clienteEnderecoRepository.findByCliente(mapper.map(cliente, ClienteEntity.class)).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado")).stream().map(clienteEnderecoEntity -> mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());

	}

}
