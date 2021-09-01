package actions.servicesActions;

import actions.IAction;
import model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ServiceService;
import view.Printer;

import java.util.List;
import java.util.Scanner;
@Component
public class PrintServicesAction implements IAction {
    @Autowired
    private ServiceService service;
    @Autowired
    private Printer printer;

    public PrintServicesAction() {

    }

    @Override
    public void execute() {
        List<Service> services=service.getServiceRepository().getAll();
        Scanner input = new Scanner(System.in);
        printer.print("How do you want to sort it? 1-by price, 2-by index");
        int answer=input.nextInt();
        if (answer==1){
            service.serviceByPriceComparator(services);
            printer.printServiceArrayList(services);
        }
        if (answer==2) {
            service.serviceByIndexComparator(services);
            printer.printServiceArrayList(services);
        }
    }
}
