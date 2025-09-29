package com.TunisBank.services;


import com.TunisBank.dto.ClientDto;
import com.TunisBank.entities.Client;
import com.TunisBank.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    ClientServiceImpl(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createNewClient(ClientDto clientDto) {

        Client client = new Client();
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setBirthday(clientDto.getBirthday());
        client.setTelephone(clientDto.getTelephone());
        client.setEmail(clientDto.getEmail());

        this.clientRepository.save(client);

    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findOne(Long id) {
        return this.clientRepository.getReferenceById(id);
    }
}
