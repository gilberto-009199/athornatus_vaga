package br.com.attornatus.clientes.api.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.api.request.ClienteEnderecoRequest;
import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.api.response.ClienteEnderecoResponse;
import br.com.attornatus.clientes.api.response.ClienteResponse;
import br.com.attornatus.clientes.api.response.ResponseBody;
import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.services.ClienteEnderecoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("cliente/{idCliente}/endereco")
public class EnderecosController {
	
	
	@Autowired
	private ClienteEnderecoService clienteEnderecoService; 

	@Autowired
	private ModelMapper mapper;
	
	
	@GetMapping(path = "/{id}")
    public ResponseEntity<?> getById( @PathVariable UUID id) {
		
		var res = new ResponseBody<ClienteEnderecoResponse>();
				
		var endereco = clienteEnderecoService.getById( id );
		
		res.setMessage( mapper.map(endereco, ClienteEnderecoResponse.class) );
		
        return ResponseEntity.ok().body(res);
    }
	
	@GetMapping
    public ResponseEntity<?> getAllByClient(@PathVariable UUID idCliente) {
		
		var res = new ResponseBody<List<ClienteEnderecoResponse>>();
				
		var enderecos = clienteEnderecoService.getAllByCliente( new ClienteDto(idCliente) ).stream().map(clienteEnderecoDto -> mapper.map(clienteEnderecoDto, ClienteEnderecoResponse.class)).collect(Collectors.toList());
		
		res.setMessage( enderecos );
		
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable UUID idCliente, @Valid @RequestBody ClienteEnderecoRequest clienteEndereco) {
    	
    	ClienteEnderecoDto dto =  clienteEnderecoService.create( new ClienteDto(idCliente), mapper.map(clienteEndereco, ClienteEnderecoDto.class) );
    	
    	var res = new ResponseBody<>( mapper.map(dto, ClienteEnderecoResponse.class) );
    	
        return ResponseEntity.created(null).body(res);
    }
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID idCliente, @PathVariable UUID id, @Valid @RequestBody ClienteEnderecoRequest clienteEndereco) {
    	
    	var clienteEnderecoDto = mapper.map(clienteEndereco, ClienteEnderecoDto.class);
    	
    	clienteEnderecoDto.setId(id);
    	
    	ClienteEnderecoDto dto =  clienteEnderecoService.update( new ClienteDto(idCliente), clienteEnderecoDto);
    	
    	var res = new ResponseBody<>( mapper.map(dto, ClienteEnderecoResponse.class) );
    	
        return ResponseEntity.ok(res);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
    	
    	clienteEnderecoService.delete( id );
    	
        return ResponseEntity.ok(null);
    }
	
}
