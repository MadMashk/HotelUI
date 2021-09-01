package actions.clientsActions;

import actions.IAction;
import exeptions.InputException;
import exeptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.Printer;

import java.text.ParseException;
import java.util.Scanner;
@Component
public class GetPriceOfRentOfClientByDateAction implements IAction {
   @Autowired
    private ClientService clientService;
   @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(GetPriceOfRentOfClientByDateAction.class);

    public GetPriceOfRentOfClientByDateAction() {

    }

    public void execute() {
        Scanner input = new Scanner(System.in);
        String clientPass;
        String date;
        while (true) {
            try {
                printer.print("print client pass");
                clientPass = input.nextLine();
                clientService.checkPassAbsence(clientPass);
                if (clientService.checkPassAbsence(clientPass)) {
                    break;
                }
            } catch (NotFoundException e) {
                logger.error("client not found");
                printer.print("A client with such passport number doesn't exist");
            }
        }


        while (true) {
            try {
                printer.print("print arrival date");
                date = input.nextLine();
                clientService.checkDate(date);
                if (clientService.checkDate(date)) {
                    break;
                }
            } catch (InputException e) {
                logger.error("wrong date format");
                printer.print("input the date in the format: dd-mm-yy");
            }

        }
        try {
            clientService.getPriceOfRent(date, clientPass);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

