package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.Student;
import uz.lazizbek.school.entity.Teacher;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.StudentDto;
import uz.lazizbek.school.payload.TeacherDto;
import uz.lazizbek.school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody StudentDto studentDto){
        ApiResponse apiResponse = studentService.add(studentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody StudentDto studentDto){
        ApiResponse apiResponse = studentService.edit(id,studentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll(){
        List<Student> students  = studentService.getAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id){
        Student student  = studentService.getById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("students/{groupId}")
    public ResponseEntity<List<Student>> getStudentsByGroup(@PathVariable Long groupId){
        List<Student> students  = studentService.getStudentsByGroup(groupId);
        return ResponseEntity.ok(students);
    }
}
