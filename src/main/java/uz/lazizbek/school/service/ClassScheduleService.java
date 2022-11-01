package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.ClassSchedule;
import uz.lazizbek.school.entity.Groups;
import uz.lazizbek.school.entity.Room;
import uz.lazizbek.school.entity.Subject;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.ClassScheduleDto;
import uz.lazizbek.school.repository.ClassScheduleRepository;
import uz.lazizbek.school.repository.GroupRepository;
import uz.lazizbek.school.repository.RoomRepository;
import uz.lazizbek.school.repository.SubjectRepository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;


@Service
public class ClassScheduleService {
    @Autowired
    ClassScheduleRepository classScheduleRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse add(ClassScheduleDto classScheduleDto){
        Optional<Groups> optionalGroup = groupRepository.findById(classScheduleDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found", false);
        }
        Groups group = optionalGroup.get();

        Optional<Subject> optionalSubject = subjectRepository.findById(classScheduleDto.getSubjectId());
        if (!optionalSubject.isPresent()){
            return new ApiResponse("Subject not found", false);
        }
        Subject subject = optionalSubject.get();

        Optional<Room> optionalRoom = roomRepository.findById(classScheduleDto.getRoomId());
        if (!optionalRoom.isPresent()){
            return new ApiResponse("Room not found", false);
        }
        Room room = optionalRoom.get();

        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setGroup(group);
        classSchedule.setSubject(subject);
        classSchedule.setRoom(room);
        classSchedule.setStartTime(Time.valueOf(classScheduleDto.getStartTime()));
        classSchedule.setEndTime(Time.valueOf(classScheduleDto.getEndTime()));
        classSchedule.setWeekDay(classScheduleDto.getWeekDay());
        classScheduleRepository.save(classSchedule);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(Long id, ClassScheduleDto classScheduleDto){
        Optional<ClassSchedule> optionalClassSchedule = classScheduleRepository.findById(id);
        if (!optionalClassSchedule.isPresent()){
            return new ApiResponse("Class schedule not found", false);
        }
        ClassSchedule classSchedule = optionalClassSchedule.get();

        Optional<Groups> optionalGroup = groupRepository.findById(classScheduleDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found", false);
        }
        Groups group = optionalGroup.get();

        Optional<Subject> optionalSubject = subjectRepository.findById(classScheduleDto.getSubjectId());
        if (!optionalSubject.isPresent()){
            return new ApiResponse("Subject not found", false);
        }
        Subject subject = optionalSubject.get();

        Optional<Room> optionalRoom = roomRepository.findById(classScheduleDto.getRoomId());
        if (!optionalRoom.isPresent()){
            return new ApiResponse("Room not found", false);
        }
        Room room = optionalRoom.get();

        classSchedule.setGroup(group);
        classSchedule.setSubject(subject);
        classSchedule.setRoom(room);
        classSchedule.setEndTime(Time.valueOf(classScheduleDto.getEndTime()));
        classSchedule.setStartTime(Time.valueOf(classScheduleDto.getStartTime()));
        classSchedule.setWeekDay(classScheduleDto.getWeekDay());
        classScheduleRepository.save(classSchedule);
        return new ApiResponse("Successfully edited", true);
    }


    public List<ClassSchedule> getByGroupIdWeekDay(Long groupId, String weekDay){
        return classScheduleRepository.findByGroupIdAndWeekDay(groupId, weekDay);
    }

    public ClassSchedule getById(Long id){
        Optional<ClassSchedule> optionalClassSchedule = classScheduleRepository.findById(id);
        return optionalClassSchedule.orElse(null);
    }
}
