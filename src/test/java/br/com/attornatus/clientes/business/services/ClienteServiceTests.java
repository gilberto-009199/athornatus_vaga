package br.com.attornatus.clientes.business.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.attornatus.clientes.domain.exception.DomainException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ClienteServiceTests {

	@Autowired
	private ClienteService clienteService;

	@Test
	public void getByIdInValidTest() {

	    Throwable exception = assertThrows(Exception.class, () -> {
			
			clienteService.getById( UUID.randomUUID() );
			
		});
	    
	    assertThat( exception ).isInstanceOf(DomainException.class).hasMessageContaining("domain.cliente.naoencontrado");
	}
	
}
