package br.com.attornatus.clientes.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.api.request.ClienteRequest;
import br.com.attornatus.clientes.business.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("cliente")
public class ClientesController {

	@Autowired
	private ClienteService clienteService; 
		
	@GetMapping
    public ResponseEntity<?> getAll() {
		
		return ResponseEntity.ok().body("oiii");
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClienteRequest cliente) {
    	
    	//clienteService.verify(cliente);
    	
        return ResponseEntity.created(null).build();
    }

    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok(null);
    }

}
