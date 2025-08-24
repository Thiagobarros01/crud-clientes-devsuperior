package com.thiagosbarros.crudcliente.controllers;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

     private ClientService clientService;

     public ClientController(ClientService clientService) {
         this.clientService = clientService;
     }

     @PostMapping
     public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto){
         ClientDto clienteDto = clientService.create(clientDto);
         return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
     }

     @GetMapping
     public ResponseEntity<List<ClientDto>> getAllClients(){
         List<ClientDto> clients =  clientService.findAll();
         return ResponseEntity.status(HttpStatus.OK).body(clients);
     }

     @GetMapping("/{id}")
     public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){
         ClientDto clientDto = clientService.findById(id);
         return ResponseEntity.status(HttpStatus.OK).body(clientDto);
     }

}
