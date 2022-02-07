package repository;

import model.GotServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class GotServiceRepository implements IGotServiceRepository{

    private List<GotServices> gotServices;

    public GotServiceRepository(){
        gotServices = new ArrayList<GotServices>();
    }

    @Override
    public void addGotServiceToRepository(GotServices gotService){
        gotServices.add(gotService);
    }
    @Override
    public List<GotServices> getAll(){
        return gotServices;
    }
    @Override
    public void update(List<GotServices> list){
        gotServices=list;
    }
}
