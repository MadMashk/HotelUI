package items;


import actions.IAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope ("prototype")
public class MenuItem implements IMenuItem {
   private String title;
   private IAction action;
   private IMenu nextMenu;
    public MenuItem(String title, IAction action, IMenu nextMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
    }
    public MenuItem(){

    }
    @Override
    public void doAction(){
        action.execute();
    }
    @Override
    public IAction getAction() {
        return action;
    }

    public String getTitle() {
        return title;
    }
    @Override
    public IMenu getNextMenu() {
        return nextMenu;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public void setNextMenu(IMenu nextMenu) {
        this.nextMenu = nextMenu;
    }
}

