package br.com.attornatus.clientes.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.api.converters.ClienteConverter;
import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.api.response.ClienteResponse;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.services.ClienteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "cliente")
public class ClientesController {

	@Autowired
	private ClienteService clienteService; 

	@Autowired
	private ClienteConverter converter;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/{id}")
    public ResponseBody<ClienteResponse> getById(@PathVariable UUID id) {

		var clienteDto = clienteService.getById(id);
		
		return new ResponseBody<ClienteResponse>( converter.converterDtoToResponse( clienteDto ) );
    }

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
    public ResponseBody<List<ClienteResponse>> getAll() {
		
		return new ResponseBody<List<ClienteResponse>>( converter.conveterListDtoToListResponse( clienteService.getAll() ) );
    }

	@ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseBody<ClienteResponse> create(@Valid @RequestBody ClienteRequest cliente) {
    	
    	ClienteDto dto =  clienteService.create( converter.converterRequestToDto(cliente) );
    	
        return new ResponseBody<ClienteResponse>( converter.converterDtoToResponse(dto) );
    }

	@ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{id}")
    public ResponseBody<ClienteResponse> update(@PathVariable UUID id, @Valid @RequestBody ClienteRequest cliente) {
    	
    	ClienteDto dto =  clienteService.update(id, converter.converterRequestToDto(cliente) );
    	
        return new ResponseBody<ClienteResponse>( converter.converterDtoToResponse( dto ) );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable UUID id) {
    	
    	clienteService.delete( id );
    }

}
