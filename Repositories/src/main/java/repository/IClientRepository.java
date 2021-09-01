package repository;

import model.Client;

import java.util.List;

public interface IClientRepository  {
    void addClientToRepository(Client client);
    List<Client> getAll();

    void update(List<Client> list);
}
