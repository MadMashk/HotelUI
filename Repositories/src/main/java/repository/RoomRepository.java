package repository;

import model.Room;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RoomRepository implements IRoomRepository {
    private List<Room> roomArrayList;
    public RoomRepository(){
       roomArrayList = new ArrayList<Room>();
   }
    @Override
    public void addRoomToRepository(Room room){
       roomArrayList.add(room);
    }
    @Override
    public List<Room> getAll(){
       return roomArrayList;
    }
    @Override
    public void update(List<Room> list){
        roomArrayList=list;
    }
}
