package com.TunisBank.services;

import com.TunisBank.dto.ClientDto;
import com.TunisBank.entities.Client;

import java.util.List;

public interface ClientService {

    void createNewClient(ClientDto clientDto);
    List<Client> findAll();

    Client findOne(Long id);
}
