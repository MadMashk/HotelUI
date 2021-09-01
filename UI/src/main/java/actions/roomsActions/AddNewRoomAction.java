package actions.roomsActions;

import actions.IAction;
import exeptions.InputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.RoomService;
import view.Printer;

import java.util.Scanner;
@Component
public class AddNewRoomAction implements IAction {
    @Autowired
    private RoomService roomService;
    private static final Logger logger = LogManager.getLogger(AddNewRoomAction.class);
    @Autowired
    private Printer printer;
    public AddNewRoomAction(){

    }
    @Override
    public void execute(){
        Scanner input= new Scanner(System.in);
        int capacityOfNewRoom;
        int starsQuantityOfNewRoom;
        int priceOfNewRoom;

        while (true) {
            try {
                printer.print("Print capacity of new room");
                capacityOfNewRoom = input.nextInt();
                roomService.capacityCheck(capacityOfNewRoom);
                if (roomService.capacityCheck(capacityOfNewRoom)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong capacity");
                printer.print("The capacity must not be less than 1 or more than 9 ");
            }
        }

        while (true) {
            try {
                printer.print("Print stars quantity of new room");
                starsQuantityOfNewRoom = input.nextInt();
                roomService.starsQuantityCheck(starsQuantityOfNewRoom);
                if (roomService.starsQuantityCheck(starsQuantityOfNewRoom)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("Wrong starts quantity");
                printer.print("The stars quantity must not be less than 1 or more than 3");
            }


        }
        while (true) {
            try {
                printer.print("Print price of new room");
                priceOfNewRoom = input.nextInt();
                roomService.priceCheck(priceOfNewRoom,starsQuantityOfNewRoom, capacityOfNewRoom);
                if( roomService.priceCheck(priceOfNewRoom,starsQuantityOfNewRoom, capacityOfNewRoom)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("Wrong price");
                printer.print("Wrong price");
            }
        }
      roomService.addNewRoom(capacityOfNewRoom,starsQuantityOfNewRoom,priceOfNewRoom);
    }
}
