package br.com.attornatus.clientes.api.controllers;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.api.converters.ClienteEnderecoConverter;
import br.com.attornatus.clientes.api.request.ClienteEnderecoRequest;

import br.com.attornatus.clientes.api.response.ClienteEnderecoResponse;

import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.services.ClienteEnderecoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("cliente/{idCliente}/endereco")
@Slf4j
public class EnderecosController {

	@Autowired
	private ClienteEnderecoService clienteEnderecoService; 

	@Autowired
	private ClienteEnderecoConverter converter;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/{id}")
    public ResponseBody<ClienteEnderecoResponse> getById( @PathVariable UUID id) {
		
		
		log.info("stage=init method=EnderecosController.getById {}", id.toString());
		
		var endereco = clienteEnderecoService.getById( id );
		
        return new ResponseBody<ClienteEnderecoResponse>( converter.converterDtoToResponse(endereco) );
    }

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
    public ResponseBody<List<ClienteEnderecoResponse>> getAllByClient(@PathVariable UUID idCliente) {
		
		
		log.info("stage=init method=EnderecosController.getAllByClient {}", idCliente.toString());
		
		var enderecos = converter.converterListDtoToListResponse(clienteEnderecoService.getAllByCliente( new ClienteDto(idCliente) ));

        return new ResponseBody<List<ClienteEnderecoResponse>>(enderecos);
    }

	@ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseBody<ClienteEnderecoResponse> create(@PathVariable UUID idCliente, @Valid @RequestBody ClienteEnderecoRequest clienteEndereco) {

		log.info("stage=init method=EnderecosController.create {}", clienteEndereco);

    	ClienteEnderecoDto dto =  clienteEnderecoService.create( new ClienteDto(idCliente), converter.converterRequestToDto(clienteEndereco) );
    	
        return new ResponseBody<ClienteEnderecoResponse>( converter.converterDtoToResponse(dto) );
    }

	@ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{id}")
    public ResponseBody<ClienteEnderecoResponse> update(@PathVariable UUID idCliente, @PathVariable UUID id, @Valid @RequestBody ClienteEnderecoRequest clienteEndereco) {

    	var clienteEnderecoDto = converter.converterRequestToDto(clienteEndereco);
    	
    	clienteEnderecoDto.setId(id);
		
		log.info("stage=init method=EnderecosController.update {}", clienteEndereco);

    	ClienteEnderecoDto dto =  clienteEnderecoService.update( new ClienteDto(idCliente), clienteEnderecoDto);
    	
        return new ResponseBody<ClienteEnderecoResponse>( converter.converterDtoToResponse( dto ) );
    }

	@ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable UUID idCliente, @PathVariable UUID id) {
		
		log.info("stage=init method=EnderecosController.delete {} - {} ", idCliente.toString(), id.toString());
		
    	clienteEnderecoService.delete( id );
    }

}
