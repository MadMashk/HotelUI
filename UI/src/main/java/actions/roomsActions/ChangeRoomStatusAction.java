package actions.roomsActions;

import actions.IAction;
import exeptions.NotFoundException;
import model.constants.RoomStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;

import java.util.Scanner;
@Component
public class ChangeRoomStatusAction implements IAction {
    @Value("${changeRoomStatus}")
    private String prop;
    @Autowired
    private RoomService roomService;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(ChangeRoomStatusAction.class);

    public void setProp(String prop) {
        this.prop = prop;
    }

    public ChangeRoomStatusAction() {


    }

    public void execute() {
        Scanner input = new Scanner(System.in);
        int number;
        System.out.println(prop);
        if (prop.equals("true")) {
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
            printer.print("what status do you want to change the current status to? 1-SERVICED, 2-READY");
            int answer = input.nextInt();
            if (answer == 1) {
                roomService.changeStatusRoom(number, RoomStatus.SERVICED);
            }
            if (answer == 2) {
                roomService.changeStatusRoom(number, RoomStatus.READY);
            }
        }
        else {
            printer.print("Access is denied");

        }
    }
}
