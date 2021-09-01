package actions.clientsActions;

import actions.IAction;
import exeptions.NotFoundException;
import model.GotServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.Printer;

import java.util.List;
import java.util.Scanner;
@Component
public class PrintListOfServicesOfClientAction implements IAction {
    private static final Logger logger = LogManager.getLogger(PrintListOfServicesOfClientAction.class);
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    public PrintListOfServicesOfClientAction(){

    }
    @Override
    public void execute(){
        Scanner input= new Scanner(System.in);
        String pass;
        while (true){
            try {
                printer.print("print client pass");
                pass = input.nextLine();
                clientService.checkPassAbsence(pass);
                if (clientService.checkPassAbsence(pass)){
                    break;
                }
            }
            catch (NotFoundException e){
                logger.error("client not found");
                printer.print("A client with such passport number doesn't exist");
            }
        }
        List<GotServices> gotServices=clientService.listGotServicesOfClient(pass);
        printer.print("How do you want to sort it? 1-by price, 2-by date");
        int answer=input.nextInt();
        if (answer==1){
            clientService.gotServicesByPriceComparator(gotServices);
            printer.printGotServicesOfClient(gotServices);
        }
        if (answer==2){
            clientService.gotServicesByDateComparator(gotServices);
            printer.printGotServicesOfClient(gotServices);
        }
        printer.print("total price:");
        printer.print(String.valueOf(clientService.totalPriceOfServices(pass)));
    }
}
