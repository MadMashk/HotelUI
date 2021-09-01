package repository;

import model.Room;

import java.util.List;

public interface IRoomRepository {
    void addRoomToRepository(Room room);
    List<Room> getAll();
    void update(List<Room> list);
}
