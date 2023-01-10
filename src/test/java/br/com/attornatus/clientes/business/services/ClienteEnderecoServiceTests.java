package br.com.attornatus.clientes.business.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.UUID;

import br.com.attornatus.clientes.business.dto.ClienteDto;
import br.com.attornatus.clientes.business.dto.ClienteEnderecoDto;
import br.com.attornatus.clientes.business.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.attornatus.clientes.domain.exception.DomainException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ClienteEnderecoServiceTests {

	@Autowired
	private ClienteEnderecoService clienteEnderecoService;

	@Autowired
	private ClienteService clienteService;

	@Test
	public void createValidTest() {

		ClienteDto cliente = new ClienteDto();

		cliente.setNome("Gilberto Ramos de Oliveira");
		cliente.setDtNascimento(LocalDate.now());

		ClienteDto clienteSave = clienteService.create(cliente);

		ClienteEnderecoDto endereco = new ClienteEnderecoDto();

		endereco.setCep("06434-120");
		endereco.setCidade("Barueri");
		endereco.setLogradouro("Rua jose ilheus");
		endereco.setNumero("447");
		endereco.setPrincipal(true);

		ClienteEnderecoDto enderecoSave = clienteEnderecoService.create(clienteSave, endereco);

		assertEquals(endereco.getCep(), enderecoSave.getCep());
		assertEquals(endereco.getCidade(), enderecoSave.getCidade());
		assertEquals(endereco.getLogradouro(), enderecoSave.getLogradouro());
		assertEquals(endereco.getNumero(), enderecoSave.getNumero());
	}

	@Test
	public void createinValidTest() {

		ClienteDto cliente = new ClienteDto();

		cliente.setNome("Gilberto Ramos de Oliveira");
		cliente.setDtNascimento(LocalDate.now());

		ClienteDto clienteSave = clienteService.create(cliente);

		ClienteEnderecoDto endereco = new ClienteEnderecoDto();

		endereco.setCep("06434-120");
		endereco.setCidade("Barueri");
		endereco.setLogradouro("Rua jose ilheus");
		endereco.setNumero("447");
		endereco.setPrincipal(true);

		clienteEnderecoService.create( clienteSave, endereco );

		Throwable exception = assertThrows(Exception.class, () -> {

			ClienteEnderecoDto endereco2 = new ClienteEnderecoDto();

			endereco2.setCep("06687-120");
			endereco2.setCidade("Barueri");
			endereco2.setLogradouro("Av. Presidente Collor");
			endereco2.setNumero("489");
			endereco2.setPrincipal(true);

			clienteEnderecoService.create( clienteSave, endereco2 );

		});

		assertThat( exception ).isInstanceOf(BusinessException.class).hasMessageContaining("business.cliente.endereco.doisprincipais");

	}
	
	@Test
	public void getByIdInValidTest() {

	    Throwable exception = assertThrows(Exception.class, () -> {
			
	    	clienteEnderecoService.getById( UUID.randomUUID() );
			
		});
	    
	    assertThat( exception ).isInstanceOf(DomainException.class).hasMessageContaining("domain.cliente.endereco.naoencontrado");
	}
	
	
}
