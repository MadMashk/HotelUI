package repository;

import model.Rent;

import java.util.List;

public interface IRentRepository {
    void addRentToRepository(Rent rent);
    List<Rent> getAll();
    void update(List<Rent> list);
}
