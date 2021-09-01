package repository;

import model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ClientRepository implements IClientRepository {
   private List<Client> clients;
   public   ClientRepository(){
       clients = new ArrayList<Client>();
   }
    @Override
   public void addClientToRepository(Client client){
       clients.add(client);
   }
    @Override
    public List<Client> getAll(){
        return clients;
    }
    @Override
    public void update(List<Client> list){
       clients=list;
    }
}
