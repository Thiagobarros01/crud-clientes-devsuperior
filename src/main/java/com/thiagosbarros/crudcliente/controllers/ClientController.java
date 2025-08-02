package com.thiagosbarros.crudcliente.controllers;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
