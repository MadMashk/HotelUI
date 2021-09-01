package actions;
import hibernate.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.*;
@Component
public class Exit implements IAction {
    @Autowired
    private IRentRepository rentRepository;
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IGotServiceRepository gotServiceRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private Dao dao;
    public void execute(){

        for (int i=0; i<clientRepository.getAll().size(); i++ ){
            dao.save(clientRepository.getAll().get(i));
        }
       for (int i=0; i<roomRepository.getAll().size(); i++ ){
            dao.save(roomRepository.getAll().get(i));
        }
        for (int i=0; i<gotServiceRepository.getAll().size(); i++ ){
            dao.save(gotServiceRepository.getAll().get(i));
        }
        for (int i=0; i<rentRepository.getAll().size(); i++ ){
            dao.save(rentRepository.getAll().get(i));
        }
        for (int i=0; i<serviceRepository.getAll().size(); i++ ){
            dao.save(serviceRepository.getAll().get(i));
        }
         System.exit(0);
    }

}
