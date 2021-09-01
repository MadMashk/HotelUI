package actions.roomsActions;

import actions.IAction;
import model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;

import java.util.List;
import java.util.Scanner;
@Component
public class PrintRoomsAction implements IAction {
    @Autowired
    private RoomService roomService;
    @Autowired
    private Printer printer;
    public PrintRoomsAction(){

    }
    public void execute(){
        List<Room> arrayList=roomService.getRoomRepository().getAll();
        Scanner input = new Scanner(System.in);
        printer.print("how do you want to sort it? 1-by price, 2- by capacity, 2-by stars");
        int answer=input.nextInt();
        switch (answer) {
            case 1 -> {
                roomService.roomsByPriceComparator(arrayList);
                printer.printRoomArrayList(arrayList);
            }
            case 2 -> {
                roomService.roomsByCapacityComparator(arrayList);
                printer.printRoomArrayList(arrayList);
            }
            case 3 -> {
                roomService.roomsByStarsComparator(arrayList);
                printer.printRoomArrayList(arrayList);
            }
        }
    }
}