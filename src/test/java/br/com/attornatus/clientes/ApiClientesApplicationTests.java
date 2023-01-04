package br.com.attornatus.clientes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;


@SpringBootTest
class ApiClientesApplicationTests {
	
	/* Componente para interagir com a API */
	@Autowired
	protected TestRestTemplate rest;
	
	// CLIENTE CRUD
	@Test
	void createClienteValid() {
	    
	    Assertions.assertEquals(HttpStatus.CREATED, 201);
	}
	
	@Test
	void updateClienteValid() {
	    
	    Assertions.assertEquals(HttpStatus.OK, 200);
	}
	
	//   ENDERECO CRUD
	@Test
	void createEnderecoClienteValid() {
	    
	    Assertions.assertEquals(HttpStatus.CREATED, 201);
	}
	
	@Test
	void updateEnderecoClienteValid() {
	    
		Assertions.assertEquals(HttpStatus.OK, 200);
	}
	@Test
	void setEnderecoClientePrincipalValid() {
	    
		Assertions.assertEquals(HttpStatus.OK, 200);
	    
	}
	
}



