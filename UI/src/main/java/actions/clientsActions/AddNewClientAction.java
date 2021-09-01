package actions.clientsActions;

import actions.IAction;

import exeptions.AlreadyExistsException;
import exeptions.InputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.Printer;

import java.util.Scanner;
@Component
public class AddNewClientAction implements IAction {
   @Autowired
    private ClientService clientService;
   @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(AddNewClientAction.class);
     public AddNewClientAction(){

     }
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        String nameOfNewClient;
        String passOfNewClient;
        String phoneOfNewClient;

            while (true) {
                try {
                    printer.print("Print name of new client");
                    nameOfNewClient = input.nextLine();
                    clientService.nameCheck(nameOfNewClient);
                    if (clientService.nameCheck(nameOfNewClient)) {
                        break;
                    }
                } catch (InputException e) {
                    logger.error("Wrong name");
                    printer.print("Write the correct name." + "\n" + "Name must not be empty or contain invalid characters.");
                }
            }

        while (true) {
            try {
                printer.print("Print passport number of new client");
                passOfNewClient = input.nextLine();
                clientService.passCheck(passOfNewClient);
                clientService.checkClient(passOfNewClient);
                if (clientService.passCheck(passOfNewClient)&& !clientService.checkClient(passOfNewClient)){
                    break;
                }
                } catch (InputException e){
                logger.error("Wrong pass number");
                printer.print("The length of the passport number must be 9 characters");
            } catch (AlreadyExistsException e){
                logger.error("Client already exists");
                printer.print("A client with such a passport already exists");
            }
        }


        while (true) {
            try {
                printer.print("Print phone number of new client");
                phoneOfNewClient = input.nextLine();
                clientService.phoneCheck(phoneOfNewClient);
                clientService.checkPhoneOfClient(phoneOfNewClient);
                if (clientService.phoneCheck(phoneOfNewClient) && !clientService.checkPhoneOfClient(phoneOfNewClient)) {
                    break;
                }
            }
            catch (InputException e){
                logger.error("Wrong phone");
                printer.print("The length of the phone number must be 11 characters");
            }
            catch (AlreadyExistsException e){
                logger.error("Client already exists");
                printer.print("A client with such phone number already exists");
            }
        }


        clientService.addNewClient(nameOfNewClient, passOfNewClient, phoneOfNewClient);
    }
}


