package items;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import view.Printer;
@Component
public class Navigator implements INavigator {

    @Autowired
    private Printer printer;
    private IMenu currentMenu;

   public Navigator() {

    }

    public void setCurrentMenu(IMenu currentMenu) {
        this.currentMenu = currentMenu;
    }



    @Override
    public void printMenu() {
        printer.print(currentMenu.getName());
        for (int i = 0; i < currentMenu.getMenuItemArrayList().size(); i++) {
            printer.print(currentMenu.getMenuItemArrayList().get(i).getTitle());

        }
    }

    @Override
    public void navigate(int index) {
        if (currentMenu.getMenuItemArrayList().get(index).getAction() == null) {
            currentMenu = currentMenu.getMenuItemArrayList().get(index).getNextMenu();
        } else {
            currentMenu.getMenuItemArrayList().get(index).doAction();
        }
    }
}
