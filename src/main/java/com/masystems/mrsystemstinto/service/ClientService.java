package com.masystems.mrsystemstinto.service;

import com.masystems.mrsystemstinto.model.Client;
import com.masystems.mrsystemstinto.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Optional<List<Client>> findAllClients(){
        List<Client> resList = new ArrayList<>();
        clientRepository.findAll().forEach(resList::add);
        return Optional.of(resList);
    }

    public Optional<Client> findClient (String email) { return clientRepository.findById(email); }

    public Optional<List<Client>> findClientContaining (String email) {
        List<Client> resList = new ArrayList<>();
        clientRepository.findClientContaining(email).forEach(resList::add);
        return Optional.of(resList);
    }

    public Client newClient (Client client) { return clientRepository.save(client); }

    public void deleteClientByEmail (String email) { clientRepository.deleteById(email); }

}
