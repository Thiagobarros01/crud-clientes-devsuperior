package com.thiagosbarros.crudcliente.services;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.entities.Client;
import com.thiagosbarros.crudcliente.mapper.ClientMapper;
import com.thiagosbarros.crudcliente.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
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
    @Transactional
    public ClientDto update(Long id,ClientDto clientDto)
    {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not Found"));
        client.setName(clientDto.getName());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        clientRepository.save(client);
        return ClientMapper.toDto(client);
    }

    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Not Found"));
        clientRepository.delete(client);
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long clientId)
    {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"));

        return ClientMapper.toDto(client);

    }

    @Transactional(readOnly = true)
    public List<ClientDto> findAll()
    {
      List<Client> clients = clientRepository.findAll();
      return clients.stream()
              .map(ClientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPage(Pageable pageable)
    {
       Page<Client> clients = clientRepository.finAllPage(pageable);
       return clients.map(ClientMapper::toDto);
    }


}
