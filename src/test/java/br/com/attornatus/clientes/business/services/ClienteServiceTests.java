package br.com.attornatus.clientes.business.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import br.com.attornatus.clientes.domain.exception.DomainException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ClienteServiceTests {

	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void createValidTest() {
		
		ClienteDto cliente = new ClienteDto();
		
		cliente.setDtNascimento(LocalDate.now());
		cliente.setNome("Gilberto Ramos de Oliveira");
		
		ClienteEnderecoDto endereco = new ClienteEnderecoDto();
		
		endereco.setCep("06434-120");
		endereco.setCidade("Barueri");
		endereco.setLogradouro("Rua jose ilheus");
		endereco.setNumero("447");
		endereco.setPrincipal(true);
		
		cliente.getEnderecos().add(endereco);
		
		ClienteDto clienteSave = clienteService.create(cliente);
		
		assertEquals(clienteSave.getNome(), cliente.getNome());
		
	}
	
	@Test
	public void createinValidTest() {
		
		ClienteDto cliente = new ClienteDto();
		
		cliente.setDtNascimento(LocalDate.now());
		cliente.setNome("Gilberto Ramos de Oliveira");
		
		ClienteEnderecoDto endereco = new ClienteEnderecoDto();
		
		endereco.setCep("06434-120");
		endereco.setCidade("Barueri");
		endereco.setLogradouro("Rua jose ilheus");
		endereco.setNumero("447");
		endereco.setPrincipal(true);
		
		cliente.getEnderecos().add(endereco);
		
		ClienteEnderecoDto endereco2 = new ClienteEnderecoDto();
		
		endereco2.setCep("06687-120");
		endereco2.setCidade("Barueri");
		endereco2.setLogradouro("Av. Presidente Collor");
		endereco2.setNumero("489");
		endereco2.setPrincipal(true);
		
		cliente.getEnderecos().add(endereco2);
		
		Throwable exception = assertThrows(Exception.class, () -> {
		
			clienteService.create(cliente);
		
		});
		
		assertThat( exception ).isInstanceOf(BusinessException.class).hasMessageContaining("business.cliente.endereco.doisprincipais");
		
	}

	@Test
	public void getByIdInValidTest() {

	    Throwable exception = assertThrows(Exception.class, () -> {
			
			clienteService.getById( UUID.randomUUID() );
			
		});
	    
	    assertThat( exception ).isInstanceOf(DomainException.class).hasMessageContaining("domain.cliente.naoencontrado");
	}
	
}
