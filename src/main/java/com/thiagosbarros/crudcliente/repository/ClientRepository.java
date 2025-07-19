package com.thiagosbarros.crudcliente.repository;

import com.thiagosbarros.crudcliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
