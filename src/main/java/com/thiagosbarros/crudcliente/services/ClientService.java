package com.thiagosbarros.crudcliente.services;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto salvar(ClientDto clientDto) {

    }

}
