package repository;

import model.Service;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceRepository implements IServiceRepository {

    private List<Service> services;
    public ServiceRepository(){
        services= new ArrayList<Service>();
    }

    @Override
    public void addService(Service service){
        services.add(service);
    }
    @Override
    public List<Service> getAll(){
        return services;
    }
    @Override
    public void update(List<Service> list){
        services=list;
    }
}
