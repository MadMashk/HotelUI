package actions.servicesActions;

import actions.IAction;
import exeptions.InputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ServiceService;
import view.Printer;

import java.util.Scanner;
@Component
public class AddNewServiceAction implements IAction {
    @Autowired
    private ServiceService service;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(AddNewServiceAction.class);
    public AddNewServiceAction(){

    }
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        String nameOfService;
        int priceOfService;
        int index;
        while (true) {
            try {
                printer.print("Print name of new service");
                nameOfService = input.nextLine();
                service.checkServiceName(nameOfService);
                if (service.checkServiceName(nameOfService)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong service name");
                printer.print("The name can't be empty");
            }
        }
        while (true) {
            try {
                printer.print("Print price of new service");
                priceOfService = input.nextInt();
                service.nullCheck(priceOfService);
                if (!service.nullCheck(priceOfService)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong service price");
                printer.print("The price can't be null");
            }
        }



        service.addNewService(nameOfService,priceOfService);
    }
}
