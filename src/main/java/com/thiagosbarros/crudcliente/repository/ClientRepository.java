package com.thiagosbarros.crudcliente.repository;

import com.thiagosbarros.crudcliente.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT n FROM Client n ")
    Page<Client> finAllPage(Pageable pageable);
}
