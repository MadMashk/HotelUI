package repository;

import model.Rent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RentRepository implements IRentRepository{
    private List<Rent> rentArrayList;
    public RentRepository (){
        rentArrayList = new ArrayList<Rent>();
    }
    @Override
    public void addRentToRepository(Rent rent){
        rentArrayList.add(rent);
    }
    @Override
    public List<Rent> getAll(){
        return rentArrayList;
    }
    @Override
    public void update(List<Rent> list){
        rentArrayList=list;
    }
}
