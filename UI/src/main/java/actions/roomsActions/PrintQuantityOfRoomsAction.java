package actions.roomsActions;

import actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;
@Component
public class PrintQuantityOfRoomsAction implements IAction {
    @Autowired
    private RoomService roomService;
    @Autowired
    private Printer printer;
    public PrintQuantityOfRoomsAction(){


    }
    @Override
    public void execute(){
    printer.print(String.valueOf(roomService.getFreeRoomQuantity()));
    }

}
