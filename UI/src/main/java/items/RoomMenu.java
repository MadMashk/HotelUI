package items;


import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RoomMenu implements IMenu {
    private String name;
    private List<IMenuItem> IMenuItemArrayList;
    public RoomMenu(){

    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setIMenuItemArrayList(List<IMenuItem> IMenuItemArrayList) {
        this.IMenuItemArrayList = IMenuItemArrayList;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IMenuItem> getMenuItemArrayList() {
        return IMenuItemArrayList;
    }
}
