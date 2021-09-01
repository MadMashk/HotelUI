package actions.roomsActions;

import actions.IAction;
import exeptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;

import java.util.Scanner;
@Component
public class ShowRoomInfoAction implements IAction {
    @Autowired
    private RoomService roomService;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(ShowRoomInfoAction.class);
    public ShowRoomInfoAction(){

    }
    @Override
    public void execute(){
        Scanner input= new Scanner(System.in);
        int number;
        while (true) {
            try {
                printer.print("print room number");
                number = input.nextInt();
                roomService.roomAbsenceCheck(number);
                if (roomService.roomAbsenceCheck(number)) {
                    break;
                }
            } catch (NotFoundException e) {
                logger.error("Room not found");
                printer.print("A room with such number doesn't exist");
            }
        }
        printer.printRoomInfo(roomService.getRoomPerNumber(number));
    }
}
