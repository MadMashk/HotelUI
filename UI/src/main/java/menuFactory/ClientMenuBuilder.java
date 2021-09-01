package menuFactory;


import actions.IAction;
import items.IMenu;
import items.IMenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientMenuBuilder implements IBuilder{
    private  IMenu rootMenu;
    @Autowired
    @Qualifier("addNewClientAction")
    private IAction addNewClientAction;
    @Autowired
    @Qualifier("setRoomToClientAction")
    private IAction setRoomToClientAction;
    @Autowired
    @Qualifier("unsetRoomOfClientAction")
    private IAction unsetRoomOfClientAction;
    @Autowired
    @Qualifier("setServiceToClientAction")
    private IAction setServiceToClientAction;
    @Autowired
    @Qualifier("printListOfClientsAndTheirRoomsAction")
    private IAction printListOfClientsAndTheirRoomsAction;
    @Autowired
    @Qualifier("printListOfServicesOfClientAction")
    private IAction printListOfServicesOfClientAction;
    @Autowired
    @Qualifier("printQuantityOfClientsAction")
    private IAction printQuantityOfClientsAction;
    @Autowired
    @Qualifier("getPriceOfRentOfClientByDateAction")
    private IAction getPriceOfRentOfClientByDateAction;
    @Autowired
    @Qualifier("mainMenu")
    private IMenu mainMenu;
    @Autowired
    @Qualifier("clientMenu")
    private IMenu clientMenu;
    @Autowired
    private IMenuItem addNewClientMenuItem;
    @Autowired
    private IMenuItem setRoomToClientMenuItem;
    @Autowired
    private IMenuItem unsetRoomOfClientMenuItem;
    @Autowired
    private IMenuItem setServiceToClientMenuItem;
    @Autowired
    private IMenuItem printListOfClientsAndTheirRoomsMenuItem;
    @Autowired
    private IMenuItem printListOfServicesOfClientMenuItem;
    @Autowired
    private IMenuItem printQuantityOfClientsMenuItem;
    @Autowired
    private IMenuItem getPriceOfRentOfClientByDateMenuItem;
    @Autowired
    private IMenuItem goToMainMenuMenuItem;

    @Override
    public void buildMenu(){

       List<IMenuItem> menuItemArrayList =new ArrayList<>();
       addNewClientMenuItem.setTitle("1-add new client");
       addNewClientMenuItem.setAction(addNewClientAction);

       setRoomToClientMenuItem.setTitle("2-set room to client");
       setRoomToClientMenuItem.setAction(setRoomToClientAction);

       unsetRoomOfClientMenuItem.setTitle("3-unset room");
       unsetRoomOfClientMenuItem.setAction(unsetRoomOfClientAction);

       setServiceToClientMenuItem.setTitle("4-set service to client");
       setServiceToClientMenuItem.setAction(setServiceToClientAction);

       printListOfClientsAndTheirRoomsMenuItem.setTitle("5-print list of client and their rooms");
       printListOfClientsAndTheirRoomsMenuItem.setAction(printListOfClientsAndTheirRoomsAction);

       printListOfServicesOfClientMenuItem.setTitle("6-print list of client's services");
       printListOfServicesOfClientMenuItem.setAction(printListOfServicesOfClientAction);

       printQuantityOfClientsMenuItem.setTitle("7-clients quantity");
       printQuantityOfClientsMenuItem.setAction(printQuantityOfClientsAction);

       getPriceOfRentOfClientByDateMenuItem.setTitle("8-price of rent by date");
       getPriceOfRentOfClientByDateMenuItem.setAction(getPriceOfRentOfClientByDateAction);

       goToMainMenuMenuItem.setTitle("9-go to main menu");
       goToMainMenuMenuItem.setNextMenu(mainMenu);

       menuItemArrayList.add(addNewClientMenuItem);
       menuItemArrayList.add(setRoomToClientMenuItem);
       menuItemArrayList.add(unsetRoomOfClientMenuItem);
       menuItemArrayList.add(setServiceToClientMenuItem);
       menuItemArrayList.add(printListOfClientsAndTheirRoomsMenuItem);
       menuItemArrayList.add(printListOfServicesOfClientMenuItem);
       menuItemArrayList.add(printQuantityOfClientsMenuItem);
       menuItemArrayList.add(getPriceOfRentOfClientByDateMenuItem);
       menuItemArrayList.add(goToMainMenuMenuItem);

       clientMenu.setIMenuItemArrayList(menuItemArrayList);
       clientMenu.setName("Menu Clients:");
       rootMenu=clientMenu;
    }

    @Override
    public IMenu getRootMenu() {
        return rootMenu;
    }
}
