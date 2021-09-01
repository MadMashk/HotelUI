package service;

import exeptions.AlreadyExistsException;
import exeptions.InputException;
import exeptions.NotFoundException;
import model.Rent;
import model.Room;
import model.constants.RoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IRentRepository;
import repository.IRoomRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class RoomService {
    @Autowired
    private IRentRepository rentRepository;
    @Autowired
    private IRoomRepository roomRepository;
    public RoomService(){
    }

    public void roomsByPriceComparator(List<Room> rooms){
        rooms.stream().sorted(Comparator.comparing(Room::getPrice).thenComparing(Room::getNumber)).collect(Collectors.toCollection(ArrayList<Room>::new));

    }
    public void roomsByStarsComparator(List<Room> rooms){
        rooms.stream().sorted(Comparator.comparing(Room::getStarsQuantity).thenComparing(Room::getNumber)).collect(Collectors.toCollection(ArrayList<Room>::new));
    }
    public void roomsByCapacityComparator(List<Room> rooms){
        rooms.stream().sorted(Comparator.comparing(Room::getCapacity).thenComparing(Room::getNumber)).collect(Collectors.toCollection(ArrayList<Room>::new));
    }
    public void printRentArrayList(List<Rent> rents) {
        for (Rent rent : rents) {
            System.out.println("Client " + rent.getClient() + " Arrival date " + rent.getArrivalDate() + " Departure date " + rent.getDepartureDate());
        }
    }
    public Date reformat(String date) throws ParseException {
        DateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.parse(date);
    }


    public void addNewRoom(int capacity, int starsQuantity, int price){ //добавление новой комнаты
        Room newRoom =new Room();
        newRoom.setCapacity(capacity);
        newRoom.setStarsQuantity(starsQuantity);
        newRoom.setPrice(price);
        roomRepository.addRoomToRepository(newRoom);
    }



   public int getFreeRoomQuantity(){ //количество свободных номеров
        int quantity=0;
       for (int i = 0; i < roomRepository.getAll().size(); i++) {
           if (roomRepository.getAll().get(i).getStatus() == RoomStatus.FREE) {
               quantity++;
           }
       }
       return quantity;
   }
    public boolean capacityCheck(int capacity) throws InputException {
        if (capacity<1 || capacity>7) {
            inputException();
        }
        return true;
    }
    public  boolean starsQuantityCheck(int starsQuantity) throws InputException {
        if (starsQuantity<1 || starsQuantity>3){
            inputException();
        }
        return true;
    }
    public boolean priceCheck(int priceOfNewRoom, int starsQuantityOfNewRoom, int capacityOfNewRoom) throws InputException{
        if (starsQuantityOfNewRoom==1 && priceOfNewRoom<(1000*capacityOfNewRoom) ||starsQuantityOfNewRoom==2 && priceOfNewRoom<(2000*capacityOfNewRoom) || starsQuantityOfNewRoom==3 && priceOfNewRoom<(3000*capacityOfNewRoom)){
            inputException();
        }
        return true;
    }

    public boolean roomCheck(int number) throws AlreadyExistsException {
        for (int i = 0; i < roomRepository.getAll().size(); i++) { //проверка наличия комнаты
            if (roomRepository.getAll().get(i).getNumber()==number){
                alreadyExistsException();
            }
        }
        return false;
    }
    public  boolean roomAbsenceCheck(int number) throws NotFoundException { //проверка отсутствия комнаты
        Stream<Room> stream = roomRepository.getAll().stream();
        boolean match = stream.anyMatch(room -> room.getNumber()==number);
        if(!match){
            notFoundException();
        }

        return match;
    }

    public Room getRoomPerNumber(int number){ //получение комнаты по номеру
        for (Room room : roomRepository.getAll()) {
            if (room.getNumber() == number) {
                return room;
            }
        }
        return null;
    }


    public void checkFreeRoomsByDate( String searchDate, int dateRange ) throws ParseException { // проверка наличия комнаты по опредленному диапазону дат в будущем
        List<Rent> rentList =rentRepository.getAll();
        List<Room> listRoom= roomRepository.getAll();
        Date todayDate = new Date();
        if (reformat(searchDate).compareTo(todayDate) == 1) {
            for (int i = 0; i < rentList.size(); i++) {
                long difference = rentList.get(i).getDepartureDate().getTime() - rentList.get(i).getArrivalDate().getTime();
                String a = String.format("%d", difference / 86400000);
                int result = Integer.parseInt(a);
                Date[] datesOfRooms = new Date[result];
                fullArr(rentList.get(i).getArrivalDate(), datesOfRooms);
                Date searchDateD = reformat(searchDate);
                Date[] datesOfSearchRooms = new Date[dateRange];
                fullArr(searchDateD, datesOfSearchRooms);
                boolean exit = true;
                for (int k = 0; k < datesOfRooms.length && exit; k++) {
                    for (int l = 0; l < datesOfSearchRooms.length; l++) {
                        if (datesOfRooms[k].compareTo(datesOfSearchRooms[l]) == 0) {
                            exit = false;
                            break;
                        } else exit = true;
                    }
                    if (k == datesOfRooms.length - 1)
                        System.out.println("room № " + rentList.get(i).getRoom().getNumber() + " is free at this range");
                }
            }
            for (int i = 0; i < listRoom.size(); i++) {
                if (!listRoom.get(i).getRentStatus())
                    System.out.println("room № " + listRoom.get(i).getNumber() + " is free at this range");
            }
        } else System.out.println("the date is wrong");
    }
    public void fullArr(Date date, Date[] datesOfRooms) {
        Calendar cal1 = GregorianCalendar.getInstance();
        for (int i = 0; i <= datesOfRooms.length - 1; i++) {
            cal1.setTime(date);
            cal1.add(GregorianCalendar.DAY_OF_MONTH, i);
            datesOfRooms[i] = cal1.getTime();

        }
    }

    public void changeStatusRoom(int roomNumber,RoomStatus status){ //изменить статус комнаты
        Room room=getRoomPerNumber(roomNumber);
        room.setStatus(status);
    }

    public void showLastClientsOfRoom(int roomNumber, int quantity) { //последние  клиенты определенной комнаты и их даты прибывания
        List<Rent> rentArrayList=rentRepository.getAll();
        int number = 0;
        for (int i = rentArrayList.size() - 1; i >= 0 && number < quantity; i--) {
            if (rentArrayList.get(i).getRoom().getNumber() == roomNumber) {
                System.out.println("клиент:" + rentArrayList.get(i).getClient().getName());
                System.out.println("дата въезда: " + rentArrayList.get(i).getArrivalDate());
                System.out.println("дата отъезда: " + rentArrayList.get(i).getDepartureDate());
                number++;
            }
        }
    }

    public void changePrice(int roomNumber, int newPrice){ //изменение цены
        for (int i = 0; i < roomRepository.getAll().size(); i++) {
            if (roomRepository.getAll().get(i).getNumber()==roomNumber){
                roomRepository.getAll().get(i).setPrice(newPrice);
            }
        }
    }

    public IRoomRepository getRoomRepository() {
        return roomRepository;
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


