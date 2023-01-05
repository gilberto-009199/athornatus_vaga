package br.com.attornatus.clientes.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.api.response.ClienteResponse;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "cliente")
public class ClientesController {

	@Autowired
	private ClienteService clienteService; 

	@Autowired
	private ModelMapper mapper;

	@GetMapping
    public ResponseEntity<?> getAll() {

		var res = new ResponseBody<List<ClienteResponse>>();

		res.setMessage( clienteService.getAllCliente().stream().map(clienteEntity -> mapper.map(clienteEntity, ClienteResponse.class)).collect(Collectors.toList()) );

		return ResponseEntity.ok().body( res );
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClienteRequest cliente) {
    	
    	ClienteDto dto =  clienteService.createCliente( mapper.map(cliente, ClienteDto.class) );
    	
    	var res = new ResponseBody<>( mapper.map(dto, ClienteResponse.class) );
    	
        return ResponseEntity.created(null).body(res);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody ClienteRequest cliente) {
    	
    	ClienteDto dto =  clienteService.updateCliente(id, mapper.map(cliente, ClienteDto.class));
    	
    	var res = new ResponseBody<>( mapper.map(dto, ClienteResponse.class) );
    	
        return ResponseEntity.ok(res);
    }

}
