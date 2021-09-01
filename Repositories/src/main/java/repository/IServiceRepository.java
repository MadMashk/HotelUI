package repository;

import model.Service;

import java.util.List;

public interface IServiceRepository {
    void addService(Service service);
    List<Service> getAll();
    void update(List<Service> list);
}
