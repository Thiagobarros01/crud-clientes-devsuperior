package com.thiagosbarros.crudcliente.services;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.entities.Client;
import com.thiagosbarros.crudcliente.mapper.ClientMapper;
import com.thiagosbarros.crudcliente.repository.ClientRepository;
import jakarta.websocket.ClientEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto create(ClientDto clientDto) {
        Client client = new  Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        clientRepository.save(client);
        return ClientMapper.toDto(client);

    }

    public ClientDto update(ClientDto clientDto) {
        return null;
    }

    public void delete(ClientDto clientDto) {

    }

    public ClientDto findById(ClientDto clientDto) {
        return null;
    }
    public List<ClientDto> findAll() {
        return null;
    }


}
