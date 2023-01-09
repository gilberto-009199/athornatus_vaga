package br.com.attornatus.clientes.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.clientes.api.converters.ClienteConverter;
import br.com.attornatus.clientes.api.converters.ClienteEnderecoConverter;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.business.services.ClienteEnderecoService;
import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;
import br.com.attornatus.clientes.domain.exception.DomainException;
import br.com.attornatus.clientes.domain.repositories.ClienteEnderecoRepository;


@Service
public class ClienteEnderecoServiceImpl implements ClienteEnderecoService{
		
	@Autowired
	private ClienteEnderecoRepository clienteEnderecoRepository;
		
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ClienteEnderecoConverter converterEndereco;
	
	@Autowired
	private ClienteConverter converterCliente;
	
	public List<ClienteEnderecoDto> getAllByCliente(ClienteDto cliente) {

		return clienteEnderecoRepository.findByCliente( converterCliente.converterDtoToEntity(cliente) ).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.naoencontrado")).stream().map(clienteEnderecoEntity -> mapper.map(clienteEnderecoEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());
	}
	
	public ClienteEnderecoDto getById(UUID id) {
		
		ClienteEnderecoEntity clienteEntity = clienteEnderecoRepository.findById(id).orElseThrow(() -> new DomainException("DOM_NotFound","domain.cliente.endereco.naoencontrado"));
		
		return converterEndereco.converterEntityToDto( clienteEntity );
	}
	
	public ClienteEnderecoDto create(ClienteDto clienteDto, ClienteEnderecoDto clienteEndereco){

		ClienteEntity clienteEntity = converterCliente.converterDtoToEntity(clienteDto);
		ClienteEnderecoEntity clienteEnderecoEntity = converterEndereco.converterDtoToEntity( clienteEndereco );
		
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
		
		return converterEndereco.converterEntityToDto(clienteEnderecoEntity);
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

		return converterEndereco.converterEntityToDto( clienteEnderecoEntity );
	}

	public List<ClienteEnderecoDto> createAllClienteEndereco(ClienteDto clienteDto, List<ClienteEnderecoDto> listEnderecos) {
		
		ClienteEntity clienteEntity = converterCliente.converterDtoToEntity( clienteDto );
		
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

		return converterEndereco.converterListEntityToListDto( clienteEnderecoRepository.saveAll(listClienteEndereco) ) ;
	}



	public void delete(UUID id) {
		
		clienteEnderecoRepository.deleteByUUId( id );
	}

}
