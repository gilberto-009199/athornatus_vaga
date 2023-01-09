package br.com.attornatus.clientes.api.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.api.response.ClienteResponse;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;

@Component
public class ClienteConverter {
	
	@Autowired
	private ModelMapper mapper;
	
	public ClienteDto converterRequestToDto(ClienteRequest request) {
		
		return mapper.map( request, ClienteDto.class );
	}

	public ClienteResponse converterDtoToResponse(ClienteDto response) {

		return mapper.map( response, ClienteResponse.class );
	}

	public ClienteEntity converterDtoToEntity(ClienteDto dto) {

		return mapper.map( dto, ClienteEntity.class );
	}

	public ClienteDto converterEntityToDto(ClienteEntity entity) {

		return mapper.map( entity, ClienteDto.class );
	}

	public List<ClienteResponse> conveterListDtoToListResponse(List<ClienteDto> listDto){

		return listDto.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteResponse.class)).collect(Collectors.toList());
	}

	public List<ClienteDto> conveterListEntityToListDto(List<ClienteEntity> listEntity){

		return listEntity.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteDto.class)).collect(Collectors.toList());
	}

	public List<ClienteDto> conveterListRequestToListDto(List<ClienteRequest> listRequest){

		return listRequest.stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteDto.class)).collect(Collectors.toList());
	}
}
