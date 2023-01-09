package br.com.attornatus.clientes.api.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.attornatus.clientes.api.request.ClienteEnderecoRequest;

import br.com.attornatus.clientes.api.response.ClienteEnderecoResponse;

import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;


@Component
public class ClienteEnderecoConverter {
	
	@Autowired
	private ModelMapper mapper;
	
	public ClienteEnderecoDto converterRequestToDto(ClienteEnderecoRequest request) {
		return mapper.map( request, ClienteEnderecoDto.class );
	}
	
	public ClienteEnderecoResponse converterDtoToResponse(ClienteEnderecoDto response) {
		return mapper.map( response, ClienteEnderecoResponse.class );
	}
	
	public ClienteEnderecoEntity converterDtoToEntity(ClienteEnderecoDto dto) {
		return mapper.map( dto, ClienteEnderecoEntity.class );
	}
	
	public ClienteEnderecoDto converterEntityToDto(ClienteEnderecoEntity entity) {
		return mapper.map( entity, ClienteEnderecoDto.class );
	}


	public List<ClienteEnderecoResponse> converterListDtoToListResponse(List<ClienteEnderecoDto> listDto){

		return listDto.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteEnderecoResponse.class)).collect(Collectors.toList());
	}

	public List<ClienteEnderecoDto> converterListEntityToListDto(List<ClienteEnderecoEntity> listEntity){

		return listEntity.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());
	}

	public List<ClienteEnderecoDto> converterListRequestToListDto(List<ClienteEnderecoDto> listRequest){

		return listRequest.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteEnderecoDto.class)).collect(Collectors.toList());
	}
	
	
}
