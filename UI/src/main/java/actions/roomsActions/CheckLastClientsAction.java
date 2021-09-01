package actions.roomsActions;

import actions.IAction;
import exeptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;

import java.util.Scanner;
@Component
public class CheckLastClientsAction implements IAction {
    @Value("${quantityOfClients}")
    private String prop;
    @Autowired
    private RoomService roomService;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(CheckLastClientsAction.class);
    public CheckLastClientsAction(){
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    @Override
       public void execute() {
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
        int quantity=Integer.parseInt(prop);
        System.out.println(quantity);
        roomService.showLastClientsOfRoom(number,quantity);
    }
    }
