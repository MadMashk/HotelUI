package actions.clientsActions;

import actions.IAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import view.Printer;
@Component
public class PrintQuantityOfClientsAction implements IAction {
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    public PrintQuantityOfClientsAction(){

    }
    public void execute(){
       printer.print( String.valueOf(clientService.getQuantityOfClients()));
    }
}
