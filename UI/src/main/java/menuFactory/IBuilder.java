package menuFactory;

import items.IMenu;

public interface IBuilder {
    void  buildMenu();
    IMenu getRootMenu();
}
