package items;


import actions.IAction;

public interface IMenuItem {
    IMenu getNextMenu();
    void doAction();
    String getTitle();
    IAction getAction();
    public void setTitle(String title);

    public void setAction(IAction action);

    public void setNextMenu(IMenu nextMenu);
}
