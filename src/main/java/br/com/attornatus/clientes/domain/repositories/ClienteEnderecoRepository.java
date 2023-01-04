package br.com.attornatus.clientes.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;

@Repository
public interface ClienteEnderecoRepository extends CrudRepository<ClienteEnderecoEntity, Long>{}
