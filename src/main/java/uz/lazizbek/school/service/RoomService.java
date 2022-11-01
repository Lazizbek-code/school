package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.Room;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public ApiResponse add(Room room){
        if (roomRepository.existsByTitle(room.getTitle())){
            return new ApiResponse("Room found", false);
        }
        Room newRoom = new Room();
        newRoom.setTitle(room.getTitle());
        newRoom.setActive(true);
        roomRepository.save(newRoom);
        return new ApiResponse("Successfully added", true);
    }

    public List<Room> getAll(){
        return roomRepository.findAll();
    }

    public List<Room> getAllActiveGroup(){
        return roomRepository.findByActiveTrue();
    }

    public Room getById(Long id){
        Optional<Room> optionalRoom =roomRepository.findById(id);
        return optionalRoom.orElse(null);
    }

    public ApiResponse edit(Long id, Room room){
        if (roomRepository.existsByTitleAndIdNot(room.getTitle(), id)){
            return new ApiResponse("Room found", false);
        }
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return new ApiResponse("Room not found",false);
        }

        Room editGroup =optionalRoom.get();
        editGroup.setTitle(room.getTitle());
        roomRepository.save(editGroup);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse changeActive(Long id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return new ApiResponse("Group not found",false);
        }
        Room editRoom = optionalRoom.get();
        editRoom.setActive(!editRoom.getActive());
        roomRepository.save(editRoom);
        return new ApiResponse("Successfully change active", true);
    }
}
