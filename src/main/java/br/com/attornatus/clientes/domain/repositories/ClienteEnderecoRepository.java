package br.com.attornatus.clientes.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.clientes.domain.entities.ClienteEnderecoEntity;

@Repository
public interface ClienteEnderecoRepository extends JpaRepository<ClienteEnderecoEntity, Long>{}
