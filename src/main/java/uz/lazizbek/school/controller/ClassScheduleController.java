package uz.lazizbek.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.lazizbek.school.entity.ClassSchedule;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.ClassScheduleDto;
import uz.lazizbek.school.service.ClassScheduleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/classSchedule")
public class ClassScheduleController {
    @Autowired
    ClassScheduleService classScheduleService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ClassScheduleDto classScheduleDto){
        ApiResponse apiResponse = classScheduleService.add(classScheduleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/class/{groupId}/{weekDay}")
    public ResponseEntity<List<ClassSchedule>> getByGroupIdWeekDay(@PathVariable Long groupId, @PathVariable String weekDay){
        List<ClassSchedule> classSchedule  = classScheduleService.getByGroupIdWeekDay(groupId, weekDay);
        return ResponseEntity.ok(classSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody ClassScheduleDto classScheduleDto){
        ApiResponse apiResponse = classScheduleService.edit(id,classScheduleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedule> getById(@PathVariable Long id){
        ClassSchedule classSchedule = classScheduleService.getById(id);
        return ResponseEntity.ok(classSchedule);
    }
}
