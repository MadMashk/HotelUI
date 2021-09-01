package view;

import model.GotServices;
import model.Rent;
import model.Room;
import model.Service;
import model.constants.RoomStatus;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("printer")
public class Printer {

    public Printer(){
    }

    public void print(String text){
        System.out.println(text);
    }
    public void printRentArrayList(List<Rent> arrayList){
        for (Rent rent : arrayList) {
            print(rent.getClient().getName());
            print(String.valueOf(rent.getRoom().getNumber()));
            print(rent.getDepartureDate().toString());
        }
    }
    public void printGotServicesOfClient(List<GotServices> arrayList) {
        for (GotServices gotServices : arrayList) {
            print(gotServices.getService().getName());
            print(String.valueOf(gotServices.getService().getPrice()));
        }
    }
    public void printRoomArrayList(List<Room> arrayList){
        for (Room room : arrayList) {
            print("room number " + String.valueOf(room.getNumber()));
            print("price " + String.valueOf(room.getPrice()));
            print("capacity " + String.valueOf(room.getCapacity()));
            print("stars quantity " + String.valueOf(room.getStarsQuantity()));
        }
    }
    public void printFreeRooms(List<Room> arrayList){
        for (Room room : arrayList) {
            if (room.getStatus() == RoomStatus.FREE) {
                print("room number " + String.valueOf(room.getNumber()));
                print("price " + String.valueOf(room.getPrice()));
                print("capacity " + String.valueOf(room.getCapacity()));
                print("stars quantity " + String.valueOf(room.getStarsQuantity()));
            }
        }
    }
    public void printRoomInfo(Room room){
        print("Number "+ room.getNumber());
        print("Price "+ room.getPrice());
        print("Capacity "+ room.getCapacity());
        print("Stars quantity "+ room.getStarsQuantity());
    }
    public void printServiceArrayList(List<Service> arrayList){
        for (int i = 0; i < arrayList.size(); i++) {
            print("index "+ arrayList.get(i).getIndex());
            print("name "+ arrayList.get(i).getName());
            print("price "+arrayList.get(i).getPrice());
        }
    }
}
