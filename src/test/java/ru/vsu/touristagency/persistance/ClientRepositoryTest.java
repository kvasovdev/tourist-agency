package ru.vsu.touristagency.persistance;

import org.junit.jupiter.api.Test;

import ru.vsu.touristagency.domain.Client;

import java.util.List;

class ClientRepositoryTest {

    @Test
    void create() {
        Client c = new Client((long)1, "1", 2);
        Client c2 = new Client((long)2, "dfsd", 3);
        IRepository<Client> rep = new ClientRepository();
        rep.create(c);
        rep.create(c2);
    }

    @Test
    void findAll() {
        List<Client> c;
        IRepository<Client> rep = new ClientRepository();
        c = rep.findAll();
        for (Client cl : c){
            System.out.println(cl);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}