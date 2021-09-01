package actions.clientsActions;

import actions.IAction;
import model.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.Printer;

import java.util.List;
import java.util.Scanner;
@Component
public class PrintListOfClientsAndTheirRoomsAction implements IAction {
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    public PrintListOfClientsAndTheirRoomsAction(){

    }
    @Override
    public void execute(){
        List<Rent> rents=clientService.getRentRepository().getAll();
        Scanner input=new Scanner(System.in);
        printer.print("How do you want to sort it? 1-by alphabet, 2-by departure date");
        int answer= input.nextInt();
        if(answer==1){
           clientService.rentsByClientNameComparator(rents);
           printer.printRentArrayList(rents);
        }
        if (answer==2) {
            clientService.rentsByDepartureDateComparator(rents);
            printer.printRentArrayList(rents);
        }
    }
}
