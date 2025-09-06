package com.thiagosbarros.crudcliente.controllers;

import com.thiagosbarros.crudcliente.dto.ClientDto;
import com.thiagosbarros.crudcliente.services.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/clients")
public class ClientController {

     private ClientService clientService;

     public ClientController(ClientService clientService) {
         this.clientService = clientService;
     }

     @PostMapping
     public ResponseEntity<ClientDto> createClient(@RequestBody @Valid ClientDto clientDto){
         ClientDto clienteDto = clientService.create(clientDto);
         return ResponseEntity.status(HttpStatus.CREATED).body(clienteDto);
     }

     @PutMapping("/{id}")
     public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientDto clientDto){
         return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id,clientDto));
     }

     @GetMapping
     public ResponseEntity<List<ClientDto>> getAllClients(){
         List<ClientDto> clients =  clientService.findAll();
         return ResponseEntity.status(HttpStatus.OK).body(clients);
     }

     @GetMapping("/paginado")
     public ResponseEntity<Page<ClientDto>> getAllClientsPaginado(Pageable pageable){
         return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllPage(pageable));
     }

     @GetMapping("/{id}")
     public ResponseEntity<ClientDto> getClientById(@PathVariable @Min(1) Long id){
         ClientDto clientDto = clientService.findById(id);
         return ResponseEntity.status(HttpStatus.OK).body(clientDto);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable @Min(1) Long id){
         clientService.delete(id);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }

}
