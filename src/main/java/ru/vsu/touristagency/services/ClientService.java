package ru.vsu.touristagency.services;

import ru.vsu.touristagency.domain.Client;
import ru.vsu.touristagency.persistance.ClientRepository;

import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    public Client getClient(Long id) {
        return clientRepository.find(id);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public boolean updateClient(Object... o) {
        Client c = new Client((long)o[0], (String)o[1], (Integer)o[2]);
        return clientRepository.update(c);
    }

    public boolean deleteClient(Long id){
        return clientRepository.delete(new Client(id));
    }

    public boolean createClient(Object... o){
        return clientRepository.create(new Client((String)o[0], (int)o[1]));
    }


}


