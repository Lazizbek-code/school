package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.Room;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("api/room")
public class RoomController {
    @Autowired
    RoomService roomService;


    @PostMapping
    public ResponseEntity<?> add(@RequestBody Room room){
        ApiResponse apiResponse = roomService.add(room);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Room room){
        ApiResponse apiResponse = roomService.edit(id,room);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Room> room = roomService.getAll();
        return ResponseEntity.status(room.size() != 0 ? 200 : 409).body(room);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveSubject() {
        List<Room> room = roomService.getAllActiveGroup();
        return ResponseEntity.status(room.size() != 0 ? 200 : 409).body(room);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Room room = roomService.getById(id);
        return ResponseEntity.status(room != null ? 200 : 409).body(room);
    }

    @PutMapping("/changeActive/{id}")
    public ResponseEntity<?> changeActive(@PathVariable Long id){
        ApiResponse apiResponse = roomService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
