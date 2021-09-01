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
public class ServiceMenuBuilder implements IBuilder {
    private IMenu rootMenu;
    @Autowired
    @Qualifier("addNewServiceAction")
    private IAction addNewServiceAction;
    @Autowired
    @Qualifier("changePriceOfServiceAction")
    private IAction changePriceOfServiceAction;
    @Autowired
    @Qualifier("printServicesAction")
    private IAction printServicesAction;
    @Autowired
    @Qualifier("mainMenu")
    private IMenu mainMenu;
    @Autowired
    @Qualifier("serviceMenu")
    private IMenu serviceMenu;
    @Autowired
    private IMenuItem addNewServiceMenuItem;
    @Autowired
    private IMenuItem changePriceOfServiceMenuItem;
    @Autowired
    private IMenuItem printServicesMenuItem;
    @Autowired
    private IMenuItem goToMainMenuMenuItem;
    @Override
    public void  buildMenu(){
        List<IMenuItem> menuItemArrayList =new ArrayList<>();
        addNewServiceMenuItem.setTitle("1-add new service");
        addNewServiceMenuItem.setAction(addNewServiceAction);

        changePriceOfServiceMenuItem.setTitle("2-change price of service");
        changePriceOfServiceMenuItem.setAction(changePriceOfServiceAction);

        printServicesMenuItem.setTitle("3-print services");
        printServicesMenuItem.setAction(printServicesAction);

        goToMainMenuMenuItem.setTitle("4-go to main menu");
        goToMainMenuMenuItem.setNextMenu(mainMenu);

        menuItemArrayList.add(addNewServiceMenuItem);
        menuItemArrayList.add(changePriceOfServiceMenuItem);
        menuItemArrayList.add(printServicesMenuItem);
        menuItemArrayList.add(goToMainMenuMenuItem);

        serviceMenu.setIMenuItemArrayList(menuItemArrayList);
        serviceMenu.setName("Menu services:");
        rootMenu=serviceMenu;
    }

    @Override
    public IMenu getRootMenu() {
        return rootMenu;
    }
}
