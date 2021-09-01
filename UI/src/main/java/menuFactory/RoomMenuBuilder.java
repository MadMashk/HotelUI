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
public class RoomMenuBuilder implements IBuilder{
    private IMenu rootMenu;
    @Autowired
    @Qualifier("addNewRoomAction")
    private IAction addNewRoomAction;
    @Autowired
    @Qualifier("printFreeRoomsAction")
    private IAction printFreeRoomsAction;
    @Autowired
    @Qualifier("printRoomsAction")
    private IAction printRoomsAction;
    @Autowired
    @Qualifier("printQuantityOfRoomsAction")
    private IAction printQuantityOfRoomsAction;
    @Autowired
    @Qualifier("checkLastClientsAction")
    private IAction checkLastClientsAction;
    @Autowired
    @Qualifier("printFreeRoomsByDateAction")
    private IAction printFreeRoomsByDateAction;
    @Autowired
    @Qualifier("showRoomInfoAction")
    private IAction showRoomInfoAction;
    @Autowired
    @Qualifier("changeRoomStatusAction")
    private IAction changeRoomStatusAction;
    @Autowired
    @Qualifier("changeRoomPriceAction")
    private IAction changeRoomPriceAction;
    @Autowired
    @Qualifier("roomMenu")
    private IMenu roomMenu;
    @Autowired
    @Qualifier("mainMenu")
    private IMenu mainMenu;
    @Autowired
    private IMenuItem addNewRoomMenuItem;
    @Autowired
    private IMenuItem printFreeRoomsMenuItem;
    @Autowired
    private IMenuItem printAllRoomsMenuItem;
    @Autowired
    private IMenuItem printQuantityOfRoomsMenuItem;
    @Autowired
    private IMenuItem checkLastClientsMenuItem;
    @Autowired
    private IMenuItem printFreeRoomsByDateMenuItem;
    @Autowired
    private IMenuItem showRoomInfoMenuItem;
    @Autowired
    private IMenuItem changeRoomStatusMenuItem;
    @Autowired
    private IMenuItem changeRoomPriceMenuItem;
    @Autowired
    private IMenuItem goToMainMenuMenuItem;
    @Override
    public void buildMenu(){
       List<IMenuItem> menuItemArrayList =new ArrayList<>();
       addNewRoomMenuItem.setTitle("1-add room");
       addNewRoomMenuItem.setAction(addNewRoomAction);

       printFreeRoomsMenuItem.setTitle("2-print free rooms");
       printFreeRoomsMenuItem.setAction(printFreeRoomsAction);

       printAllRoomsMenuItem.setTitle("3-print rooms");
       printAllRoomsMenuItem.setAction(printRoomsAction);

       printQuantityOfRoomsMenuItem.setTitle("4-print quantity of free rooms");
       printQuantityOfRoomsMenuItem.setAction( printQuantityOfRoomsAction);

       checkLastClientsMenuItem.setTitle("5-show last 3 clients of room");
       checkLastClientsMenuItem.setAction(checkLastClientsAction);

       printFreeRoomsByDateMenuItem.setTitle("6-search free rooms by date");
       printFreeRoomsByDateMenuItem.setAction(printFreeRoomsByDateAction);

       showRoomInfoMenuItem.setTitle("7-show room info");
       showRoomInfoMenuItem.setAction(showRoomInfoAction);

       changeRoomStatusMenuItem.setTitle("8-change status");
       changeRoomStatusMenuItem.setAction(changeRoomStatusAction);

       changeRoomPriceMenuItem.setTitle("9-change room price");
       changeRoomPriceMenuItem.setAction(changeRoomPriceAction);

       goToMainMenuMenuItem.setTitle("10-go to main menu");
       goToMainMenuMenuItem.setNextMenu(mainMenu);

       menuItemArrayList.add(addNewRoomMenuItem);
       menuItemArrayList.add(printFreeRoomsMenuItem);
       menuItemArrayList.add(printAllRoomsMenuItem);
       menuItemArrayList.add(printQuantityOfRoomsMenuItem);
       menuItemArrayList.add(checkLastClientsMenuItem);
       menuItemArrayList.add(printFreeRoomsByDateMenuItem);
       menuItemArrayList.add(showRoomInfoMenuItem);
       menuItemArrayList.add(changeRoomStatusMenuItem);
       menuItemArrayList.add(changeRoomPriceMenuItem);
       menuItemArrayList.add(goToMainMenuMenuItem);

       roomMenu.setIMenuItemArrayList(menuItemArrayList);
       roomMenu.setName("Menu rooms:");
       rootMenu=roomMenu;

    }

    @Override
    public IMenu getRootMenu() {
        return rootMenu;
    }
}
