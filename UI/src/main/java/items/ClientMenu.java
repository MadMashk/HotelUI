package items;


import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClientMenu implements IMenu {

    private String name;
    private List <IMenuItem> IMenuItemArrayList;
    public ClientMenu(){

    }


    public void setName(String name) {
        this.name = name;
    }

    public List<IMenuItem> getIMenuItemArrayList() {
        return IMenuItemArrayList;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setIMenuItemArrayList(List<IMenuItem> IMenuItemArrayList) {
        this.IMenuItemArrayList = IMenuItemArrayList;
    }
    @Override
    public List<IMenuItem> getMenuItemArrayList() {
        return IMenuItemArrayList;
    }

}
