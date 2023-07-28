package com.magazin.calculatoare.services;

import com.magazin.calculatoare.dtos.ClientDTO;
import com.magazin.calculatoare.mappers.ClientMapper;
import com.magazin.calculatoare.entities.Client;
import com.magazin.calculatoare.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList.stream()
                .map(ClientMapper.INSTANCE::toDTO)
                .toList();
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        return ClientMapper.INSTANCE.toDTO(client);
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.toEntity(clientDTO);
        return ClientMapper.INSTANCE.toDTO(clientRepository.save(client));
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }
}
