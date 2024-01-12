package com.masystems.mrsystemstinto.controller;

import com.masystems.mrsystemstinto.model.Client;
import com.masystems.mrsystemstinto.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "mrsystems/tinto/api/client")
@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<>(clientService.findAllClients().orElse(null), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<Client> findClient(@PathVariable String email){
        return new ResponseEntity<>(clientService.findClient(email).orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> newClient(@RequestBody Client clientToSave){
        try {
            Client _client = clientService.newClient(clientToSave);
            return new ResponseEntity<>(_client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Optional<Client> clientToUpdate = clientService.findClient(client.getEmail());

        if(clientToUpdate.isPresent()) {
            Client _client = clientToUpdate.get();
            _client.setAddress(client.getAddress());
            _client.setFirstName(client.getFirstName());
            _client.setLastName(client.getLastName());
            _client.setMiddleName(client.getMiddleName());
            _client.setPhoneNumber(client.getPhoneNumber());
            return new ResponseEntity<>(clientService.newClient(_client), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> findClientContaining(@RequestParam String email){
        return new ResponseEntity<>(clientService.findClientContaining(email).orElse(null), HttpStatus.OK);
    }
}
