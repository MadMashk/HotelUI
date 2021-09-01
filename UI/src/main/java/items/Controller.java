package items;
import hibernate.IDao;
import menuFactory.IBuilder;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import repository.*;

import java.util.Scanner;
@org.springframework.stereotype.Controller
public class Controller implements IMenuController{
    @Autowired
    @Qualifier("mainMenuBuilder")
    private IBuilder mainMenuBuilder;
    @Autowired
    @Qualifier("roomMenuBuilder")
    private IBuilder roomMenuBuilder;
    @Autowired
    @Qualifier("clientMenuBuilder")
    private IBuilder clientMenuBuilder;
    @Autowired
    @Qualifier("serviceMenuBuilder")
    private IBuilder serviceMenuBuilder;
    @Autowired
    @Qualifier("navigator")
    private INavigator navigator;
    @Autowired
    private IRentRepository rentRepository;
    @Autowired
    private IClientRepository clientRepository;
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IGotServiceRepository gotServiceRepository;
    @Autowired
    private IServiceRepository serviceRepository;
    @Autowired
    private IDao dao;
    public Controller(){

    }
    @Override
    public void run() {
        System.out.println(clientMenuBuilder);
        clientRepository.update(dao.getAll(Client.class));
        roomRepository.update(dao.getAll(Room.class));
        serviceRepository.update(dao.getAll(Service.class));
        gotServiceRepository.update(dao.getAll(GotServices.class));
        rentRepository.update(dao.getAll(Rent.class));
        Scanner input = new Scanner(System.in);
        mainMenuBuilder.buildMenu();
        roomMenuBuilder.buildMenu();
        serviceMenuBuilder.buildMenu();
        clientMenuBuilder.buildMenu();
        navigator.setCurrentMenu(mainMenuBuilder.getRootMenu());

        while (true) {
            navigator.printMenu();
            int choose = input.nextInt();
            navigator.navigate(choose - 1);
        }
    }
}