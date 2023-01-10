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

import br.com.attornatus.clientes.api.converters.ClienteConverter;
import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.api.response.ClienteResponse;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.services.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "cliente")
@Slf4j
public class ClientesController {

	@Autowired
	private ClienteService clienteService; 

	@Autowired
	private ClienteConverter converter;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping(path = "/{id}")
    public ResponseBody<ClienteResponse> getById(@PathVariable UUID id) {

		log.info("stage=init method=ClientesControlller.getById {}",id.toString());
		
		var clienteDto = clienteService.getById(id);

		log.info("stage=end method=ClientesController.getById {}", clienteDto);
		
		return new ResponseBody<>( converter.converterDtoToResponse( clienteDto ) );
    }

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping
    public ResponseBody<List<ClienteResponse>> getAll() {
		
		log.info("stage=init method=ClientesControlller.getAll ");

		var listClienteDto =  clienteService.getAll();

		log.info("stage=end method=ClientesController.getAll {}", listClienteDto);

		return new ResponseBody<>( converter.converterListDtoToListResponse( listClienteDto ) );
    }

	@ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public ResponseBody<ClienteResponse> create(@Valid @RequestBody ClienteRequest cliente) {
    	
		log.info("stage=init method=ClientesControlller.create {}", cliente);
		
    	var dto =  clienteService.create( converter.converterRequestToDto(cliente) );

		log.info("stage=end method=ClientesController.create {}", dto);
    	
        return new ResponseBody<>( converter.converterDtoToResponse(dto) );
    }

	@ResponseStatus(code = HttpStatus.OK)
    @PutMapping(path = "/{id}")
    public ResponseBody<ClienteResponse> update(@PathVariable UUID id, @Valid @RequestBody ClienteRequest cliente) {
		
		
		log.info("stage=init method=ClientesControlller.update {}", cliente);
    	
    	var dto =  clienteService.update(id, converter.converterRequestToDto(cliente) );

		log.info("stage=end method=ClientesController.create {}", dto);
    	
        return new ResponseBody<>( converter.converterDtoToResponse( dto ) );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable UUID id) {
    	
    	log.info("stage=init method=ClientesControlller.delete {}", id.toString());
    	
    	clienteService.delete( id );

		log.info("stage=end method=ClientesControlller.delete {} - {}", id.toString());

    }

}
