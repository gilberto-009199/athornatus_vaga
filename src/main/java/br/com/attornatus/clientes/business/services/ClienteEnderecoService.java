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
	
	public List<ClienteEnderecoDto> getAllByCliente(ClienteDto cliente) {

		return clienteEnderecoRepository.findByCliente(mapper.map(cliente, ClienteEntity.class)).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado")).stream().map(clienteEnderecoEntity -> mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());
	}

	
	
	public ClienteEnderecoDto getById(UUID id) {
		
		ClienteEnderecoEntity clienteEntity = clienteEnderecoRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.endereco.naoencontrado"));
		
		return mapper.map( clienteEntity, ClienteEnderecoDto.class );
	}
	
	public ClienteEnderecoDto create(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco){
		
		
		
		ClienteEntity clienteEntity = mapper.map(clienteDto, ClienteEntity.class);
		ClienteEnderecoEntity clienteEnderecoEntity = mapper.map(clienteEndereco, ClienteEnderecoEntity.class);
		
		clienteEnderecoEntity.setCliente( clienteEntity );
		
		
		if(clienteEnderecoEntity.isPrincipal()) {
			
			List<ClienteEnderecoDto> listClienteEnderecoDto = getAllByCliente(clienteDto);
			
			int countClienteEnderecoPrincipais = 1;

			for( ClienteEnderecoDto clienteEnderecoDto : listClienteEnderecoDto) {
					
					if( clienteEnderecoDto.isPrincipal() )countClienteEnderecoPrincipais++;
					
			}
			// Validacao Multiplos Enderecos Principais
			if(countClienteEnderecoPrincipais > 1) throw new BusinessException("BUS_Valid","business.cliente.endereco.doisprincipais");
			
		} 
		
		clienteEnderecoEntity = clienteEnderecoRepository.save(clienteEnderecoEntity);
		
		return mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class);
	}
	
	public ClienteEnderecoDto update(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco){
		
		ClienteEnderecoEntity clienteEnderecoEntity = clienteEnderecoRepository.findById(clienteEndereco.getId()).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.endereco.naoencontrado"));
		
		clienteEnderecoEntity.setCep( clienteEndereco.getCep() );
		clienteEnderecoEntity.setCidade( clienteEndereco.getCidade() );
		clienteEnderecoEntity.setLogradouro( clienteEndereco.getLogradouro() );
		clienteEnderecoEntity.setNumero( clienteEndereco.getNumero() );
		
		boolean defineComoPrincipal = clienteEndereco.isPrincipal();
		boolean naoEraPrincipalAntes = !clienteEnderecoEntity.isPrincipal();

		if(defineComoPrincipal && naoEraPrincipalAntes) {

			List<ClienteEnderecoDto> listClienteEnderecoDto = getAllByCliente(clienteDto);

			int countClienteEnderecoPrincipais = 1;

			for( ClienteEnderecoDto clienteEnderecoDto : listClienteEnderecoDto) {
					
					if( clienteEnderecoDto.isPrincipal() )countClienteEnderecoPrincipais++;

			}
			// Validacao Multiplos Enderecos Principais
			if(countClienteEnderecoPrincipais > 1) throw new BusinessException("BUS_Valid","business.cliente.endereco.doisprincipais");

		}

		clienteEnderecoEntity.setPrincipal( clienteEndereco.getPrincipal() );

		clienteEnderecoEntity = clienteEnderecoRepository.save(clienteEnderecoEntity);

		return mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class);
	}

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



	public void delete(UUID id) {
		
		clienteEnderecoRepository.deleteByUUId( id );
		
	}

}
