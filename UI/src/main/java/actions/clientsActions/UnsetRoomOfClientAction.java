package actions.clientsActions;

import actions.IAction;
import exeptions.NotFoundException;
import model.constants.RoomStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import service.RoomService;
import view.Printer;

import java.util.Scanner;

@Component
public class UnsetRoomOfClientAction implements IAction {
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    @Autowired
    private RoomService roomService;
    private static final Logger logger = LogManager.getLogger(UnsetRoomOfClientAction.class);
    public UnsetRoomOfClientAction(){

    }
    @Override
    public void execute(){
        Scanner input=new Scanner(System.in);
        String clientPass;
        while (true) {
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

        int room=clientService.getClientByPass(clientPass).getRoom().getNumber();
        roomService.changeStatusRoom(room, RoomStatus.SERVICED);
        clientService.unsetRoom(clientPass);
    }
}
