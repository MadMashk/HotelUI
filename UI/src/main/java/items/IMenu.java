package items;

import java.util.List;

public interface IMenu {
    public List<IMenuItem> getMenuItemArrayList();

    public String getName();

    public void setName(String name);


    public void setIMenuItemArrayList(List<IMenuItem> IMenuItemArrayList);
}
