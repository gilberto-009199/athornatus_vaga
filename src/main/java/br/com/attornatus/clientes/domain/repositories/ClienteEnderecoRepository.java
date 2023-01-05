package br.com.attornatus.clientes.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;
import br.com.attornatus.clientes.domain.entities.ClienteEntity;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEnderecoEntity, Long>{
	
	Optional<ClienteEnderecoEntity> findById(UUID id);

	@Query("SELECT e FROM #{#entityName} e WHERE e.cliente=:cliente")
	Optional<List<ClienteEnderecoEntity>> findByCliente(@Param("cliente") ClienteEntity cliente);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM #{#entityName} e WHERE e.cliente=:cliente")
	int removeAllByCliente(@Param("cliente") ClienteEntity cliente);

}
