package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.Subject;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Subject subject){
        ApiResponse apiResponse = subjectService.add(subject);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Subject subject){
        ApiResponse apiResponse = subjectService.edit(id,subject);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Subject> subjects = subjectService.getAll();
        return ResponseEntity.status(subjects.size() != 0 ? 200 : 409).body(subjects);
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllActiveSubject() {
        List<Subject> subjects = subjectService.getAllActiveSubject();
        return ResponseEntity.status(subjects.size() != 0 ? 200 : 409).body(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Subject subject = subjectService.getById(id);
        return ResponseEntity.status(subject != null ? 200 : 409).body(subject);
    }

    @PutMapping("/changeActive/{id}")
    public ResponseEntity<?> changeActive(@PathVariable Long id){
        ApiResponse apiResponse = subjectService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
