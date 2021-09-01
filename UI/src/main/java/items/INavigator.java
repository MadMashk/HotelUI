package items;

public interface INavigator {
    void printMenu();
    void navigate(int index);
    void setCurrentMenu(IMenu currentMenu);
}
