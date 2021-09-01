package actions.servicesActions;

import actions.IAction;
import exeptions.InputException;
import exeptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ServiceService;
import view.Printer;

import java.util.Scanner;
@Component
public class ChangePriceOfServiceAction implements IAction {
    @Autowired
    private ServiceService service;
    @Autowired
    private Printer printer;
    private static final Logger logger = LogManager.getLogger(ChangePriceOfServiceAction.class);
    public ChangePriceOfServiceAction(){

    }
    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        int index;
        int price;
        while (true) {
            try {
                printer.print("print index of service");
                index = input.nextInt();
                service.serviceAbsenceCheck(index);
                if (service.serviceAbsenceCheck(index)){
                    break;
                }
            }
            catch (NotFoundException e){
                logger.error("service not found");
                printer.print("The service with such index doesn't exist");
            }
        }
        while (true) {
            try {
                printer.print("Print price of new service");
                price = input.nextInt();
                service.nullCheck(price);
                if (!service.nullCheck(price)){
                    break;
                }
            }
            catch (InputException e){
                logger.error("wrong service price");
                printer.print("The price can't be null");
            }
        }
        service.changePrice(index, price);
    }
    }