package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.Teacher;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.TeacherDto;
import uz.lazizbek.school.payload.TeacherSubjectDto;
import uz.lazizbek.school.service.TeacherService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse = teacherService.add(teacherDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody TeacherDto teacherDto){
        ApiResponse apiResponse = teacherService.edit(id,teacherDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll(){
        List<Teacher> teachers  = teacherService.getAll();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id){
        Teacher teacher  = teacherService.getById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping("/addSubject")
    public ResponseEntity<?> addSubject(@RequestBody TeacherSubjectDto teacherSubjectDto){
        ApiResponse apiResponse = teacherService.addSubject(teacherSubjectDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
