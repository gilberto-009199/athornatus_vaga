package br.com.attornatus.clientes.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.clientes.domain.repositories.ClienteRepository;

@RestController
@RequestMapping("cliente")
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping
    public ResponseEntity<?> create() {
        return ResponseEntity.created(null).build();
    }
    
    @PutMapping(path = "/{uuid}")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok(null);
    }
	
	
}
