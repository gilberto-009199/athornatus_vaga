package br.com.attornatus.clientes.api.controllers;

import java.time.LocalDate;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.attornatus.clientes.api.request.ClienteEnderecoRequest;
import br.com.attornatus.clientes.api.request.ClienteRequest;

import br.com.attornatus.clientes.api.response.ResponseBody;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ClienteEnderecoControllerTests {
	
	/* Componente para interagir com a API */
	@Autowired
	protected TestRestTemplate restAPI;

	// CLIENTE CRUD
	@Test
	void createValid() {

		ClienteRequest requestCliente = new ClienteRequest("Gilberto Ramos de Oliveira", LocalDate.now());
		String path = "/cliente";
		
    	ResponseEntity<ResponseBody> response = restAPI.postForEntity( path, requestCliente, ResponseBody.class);
    	Map<String,String> mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidCliente = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidCliente+"/endereco";
    	
    	ClienteEnderecoRequest request = new ClienteEnderecoRequest("06434-120","Barueri","447","Rua Jose Ilheus", true);
    	
    	response = restAPI.postForEntity( path, request, ResponseBody.class);
    	
    	Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	void createInValid() {

		ClienteRequest requestCliente = new ClienteRequest("Gilberto Ramos de Oliveira", LocalDate.now());
		String path = "/cliente";
		
    	ResponseEntity<ResponseBody> response = restAPI.postForEntity( path, requestCliente, ResponseBody.class);
    	Map mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidCliente = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidCliente+"/endereco";
    	
    	ClienteEnderecoRequest request = new ClienteEnderecoRequest("","Barueri","447","Rua Jose Ilheus", true);
    	
    	response = restAPI.postForEntity( path, request, ResponseBody.class);
    	
    	Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	void updateValid() {

		ClienteRequest requestCliente = new ClienteRequest("Gilberto Ramos de Oliveira", LocalDate.now());
		String path = "/cliente";
		
    	ResponseEntity<ResponseBody> response = restAPI.postForEntity( path, requestCliente, ResponseBody.class);
    	Map mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidCliente = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidCliente+"/endereco";
    	
    	ClienteEnderecoRequest request = new ClienteEnderecoRequest("06434-120","Barueri","447","Rua Jose Ilheus", true);
    	
    	response = restAPI.postForEntity( path, request, ResponseBody.class);
    	mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidEndereco = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidEndereco;

    	request.setCidade("Jandira");
    	
    	restAPI.put( path, request);
    	
    	response = restAPI.getForEntity( path,ResponseBody.class);
    	mapHttp = (Map) response.getBody().getMessage();
    	
    	String cidade = mapHttp.get("cidade").toString();
    	    	
    	Assertions.assertEquals( cidade, request.getCidade() );
	}

	@Test
	void deleteValid() {
		
		ClienteRequest requestCliente = new ClienteRequest("Gilberto Ramos de Oliveira", LocalDate.now());
		String path = "/cliente";
		
    	ResponseEntity<ResponseBody> response = restAPI.postForEntity( path, requestCliente, ResponseBody.class);
    	Map mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidCliente = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidCliente+"/endereco";
    	
    	ClienteEnderecoRequest request = new ClienteEnderecoRequest("06434-120","Barueri","447","Rua Jose Ilheus", true);
    	
    	response = restAPI.postForEntity( path, request, ResponseBody.class);
    	mapHttp = (Map) response.getBody().getMessage();
    	
    	UUID uuidEndereco = UUID.fromString( mapHttp.get("id").toString() );
    	
    	path += "/"+uuidEndereco;
    	
    	restAPI.delete( path );

    	response = restAPI.getForEntity( path, ResponseBody.class);
    	
    	Assertions.assertEquals( response.getStatusCode(), HttpStatus.BAD_REQUEST );
	}

}
