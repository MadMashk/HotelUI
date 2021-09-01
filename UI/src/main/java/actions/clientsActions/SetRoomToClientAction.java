package actions.clientsActions;

import actions.IAction;
import exeptions.InputException;
import exeptions.NotFoundException;
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
public class SetRoomToClientAction implements IAction {
    @Autowired
    private ClientService clientService;
    @Autowired
    private RoomService roomService;
    private static final Logger logger = LogManager.getLogger(SetRoomToClientAction.class);
    @Autowired
    private Printer printer;
    public SetRoomToClientAction(){


    }

    @Override
    public  void execute(){

        Scanner input= new Scanner(System.in);
        String clientPass;
        String arrivalDate;
        int duration;
        int roomNumber;

            while (true){
                try {
                    printer.print("print client pass");
                    clientPass = input.nextLine();
                    clientService.checkPassAbsence(clientPass);
                    if (clientService.checkPassAbsence(clientPass)){
                        break;
                    }
                }
                catch (NotFoundException e){
                    logger.error("client not found");
                    printer.print("A client with such passport number doesn't exist");
                }
            }

        while (true){
            try {
                printer.print("print room number");
                roomNumber = input.nextInt();
                roomService.roomAbsenceCheck(roomNumber);
                if (roomService.roomAbsenceCheck(roomNumber)){
                    break;
                }
            }
            catch (NotFoundException e){
                logger.error("room not found");
                printer.print("A room with such number doesn't exist");
            }
        }

        while (true){
            try {
                printer.print("print arrival date");
                arrivalDate = input.nextLine();
                clientService.checkDate(arrivalDate);
                if (clientService.checkDate(arrivalDate)){
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
                printer.print("print stay duration (days)");
                duration = input.nextInt();
                clientService.checkDuration(duration);
                if (clientService.checkDuration(duration)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong duration");
                printer.print("duration can't be less then 1 day");
            }
        }

            try {
                clientService.setRoom(clientPass, roomNumber, arrivalDate, duration);
            } catch (ParseException e) {
                    logger.error("ParseException");
                    e.printStackTrace();

            }
        }
    }

