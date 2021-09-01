package actions.roomsActions;

import actions.IAction;
import exeptions.InputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import service.RoomService;
import view.Printer;

import java.text.ParseException;
import java.util.Scanner;
@Component
public class PrintFreeRoomsByDateAction implements IAction {
    @Autowired
    private RoomService roomService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(PrintFreeRoomsByDateAction.class);
    public PrintFreeRoomsByDateAction(){

    }
    @Override
    public void execute(){
        Scanner input=new Scanner(System.in);
        String date;
        int range;
        while (true){
            try {
                printer.print("print arrival date");
                date = input.nextLine();
                clientService.checkDate(date);
                if (clientService.checkDate(date)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong date format");
                printer.print("input the date in the format: dd-mm-yy");
            }
        }
        while (true) {
            try {
                printer.print("print days range");
                range = input.nextInt();
                clientService.checkDuration(range);
                if (clientService.checkDuration(range)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong range");
                printer.print("duration can't be less then 1 day");
            }
        }
        try {
            roomService.checkFreeRoomsByDate(date, range);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("parse exception");
        }
    }
}
