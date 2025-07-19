package com.thiagosbarros.crudcliente.mapper;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.entities.Client;

public class ClientMapper {

    public static ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setChildren(client.getChildren());
        clientDto.setCpf(client.getCpf());
        clientDto.setIncome(client.getIncome());
        return clientDto;
    }

    public static Client toEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        return client;
    }
}
