package repository;

import model.GotServices;

import java.util.List;

public interface IGotServiceRepository {
    void addGotServiceToRepository(GotServices gotServices);
    List<GotServices> getAll();
    void update(List<GotServices> list);
}
