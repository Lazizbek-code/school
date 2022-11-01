package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.Groups;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("api/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Groups group){
        ApiResponse apiResponse = groupService.add(group);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Groups group){
        ApiResponse apiResponse = groupService.edit(id,group);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Groups> groups = groupService.getAll();
        return ResponseEntity.status(groups.size() != 0 ? 200 : 409).body(groups);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveSubject() {
        List<Groups> groups = groupService.getAllActiveGroup();
        return ResponseEntity.status(groups.size() != 0 ? 200 : 409).body(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Groups group = groupService.getById(id);
        return ResponseEntity.status(group != null ? 200 : 409).body(group);
    }

    @PutMapping("/changeActive/{id}")
    public ResponseEntity<?> changeActive(@PathVariable Long id){
        ApiResponse apiResponse = groupService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
