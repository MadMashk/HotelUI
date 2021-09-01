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
public class MainMenuBuilder implements IBuilder{
    private IMenu rootMenu;
    @Autowired
    @Qualifier("mainMenu")
    private IMenu mainMenu;
    @Autowired
    @Qualifier("serviceMenu")
    private IMenu serviceMenu;
    @Autowired
    @Qualifier("clientMenu")
    private IMenu clientMenu;
    @Autowired
    @Qualifier("roomMenu")
    private IMenu roomMenu;
    @Autowired
    private IMenuItem goToRoomMenuItem;
    @Autowired
    private IMenuItem goToClientMenuItem;
    @Autowired
    private IMenuItem goToServicesMenuItem;
    @Autowired
    private IMenuItem exitMenuItem;
    @Autowired
    @Qualifier("exit")
    private IAction exit;
    @Override
    public void buildMenu(){

        List<IMenuItem> menuItemArrayList= new ArrayList<>();
        goToRoomMenuItem.setTitle("1-rooms");
        goToRoomMenuItem.setNextMenu(roomMenu);

        goToClientMenuItem.setTitle("2-clients");
        goToClientMenuItem.setNextMenu(clientMenu);

        goToServicesMenuItem.setTitle("3-services");
        goToServicesMenuItem.setNextMenu(serviceMenu);

        exitMenuItem.setTitle("4-exit");
        exitMenuItem.setAction(exit);



        menuItemArrayList.add(goToRoomMenuItem);
        menuItemArrayList.add(goToClientMenuItem);
        menuItemArrayList.add(goToServicesMenuItem);
        menuItemArrayList.add(exitMenuItem);

        mainMenu.setIMenuItemArrayList(menuItemArrayList);
        mainMenu.setName("Main menu:");
        rootMenu=mainMenu;
    }

    @Override
    public IMenu getRootMenu() {
        return rootMenu;
    }
}
