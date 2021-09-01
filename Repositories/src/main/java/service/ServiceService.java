package service;

import exeptions.AlreadyExistsException;
import exeptions.InputException;
import exeptions.NotFoundException;
import model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IServiceRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private IServiceRepository serviceRepository;
    public ServiceService() {

    }

     public boolean checkService(int index) throws AlreadyExistsException {
         for (int i = 0; i <serviceRepository.getAll().size(); i++) {
             if(serviceRepository.getAll().get(i).getIndex()==index){
                alreadyExistsException();
             }
         }
         return false;
     }
     public boolean checkServiceName(String name) throws InputException {
        if(name.trim().length()==0){
            inputException();
        }
        return true;
     }
     public boolean nullCheck(int number) throws  InputException{
        if(number<=0){
            inputException();
        }
        return false;
     }
     public boolean serviceAbsenceCheck( int index) throws NotFoundException {
         Stream<Service> stream =serviceRepository.getAll().stream();
         boolean match = stream.anyMatch(service -> service.getIndex()==index);
         if(!match){
             notFoundException();
         }
         return match;
     }

     public void serviceByPriceComparator(List<Service> services){
         services.stream().sorted(Comparator.comparing(Service::getPrice).thenComparing(Service::getIndex)).collect(Collectors.toCollection(ArrayList<Service>::new));
     }
    public void serviceByIndexComparator(List<Service> services){
        services.stream().sorted(Comparator.comparing(Service::getIndex).thenComparing(Service::getPrice)).collect(Collectors.toCollection(ArrayList<Service>::new));
    }


    public void  addNewService(String name, int price){ //добавление новой уcлуги
        Service newService= new Service();
        newService.setName(name);
        newService.setPrice(price);
        serviceRepository.addService(newService);
    }
    public void changePrice(int index, int price){
        for (int i = 0; i < serviceRepository.getAll().size(); i++) {
            if(serviceRepository.getAll().get(i).getIndex()==index){
                serviceRepository.getAll().get(i).setPrice(price);
            }

        }
    }

    public IServiceRepository getServiceRepository() {
        return serviceRepository;
    }
    public void alreadyExistsException() throws AlreadyExistsException {
        throw new AlreadyExistsException();
    }
    public void inputException() throws InputException {
        throw new InputException();
    }
    public void notFoundException()throws NotFoundException {
        throw new NotFoundException();
    }
}
