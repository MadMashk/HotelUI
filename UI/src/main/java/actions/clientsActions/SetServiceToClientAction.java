package actions.clientsActions;

import actions.IAction;
import exeptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import service.ServiceService;
import view.Printer;

import java.util.Scanner;
@Component
public class SetServiceToClientAction implements IAction {
    @Autowired
    private ClientService clientService;
    @Autowired
    private Printer printer;
    @Autowired
    private ServiceService serviceService;
    private static final Logger logger = LogManager.getLogger(SetServiceToClientAction.class);
    public SetServiceToClientAction(){

    }
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        String pass;
        int index;
        while (true){
            try {
                printer.print("print client pass");
                pass = input.nextLine();
                if (clientService.checkPassAbsence(pass)){
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
              printer.print("print index of service");
              index = input.nextInt();
              serviceService.serviceAbsenceCheck(index);
              if (serviceService.serviceAbsenceCheck(index)){
                  break;
              }
          }
          catch (NotFoundException e){
              logger.error("service not found");
              printer.print("A service with such index doesn't exist");
          }
      }

            clientService.setService(index,pass);
    }
}
