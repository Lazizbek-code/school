package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.Address;
import uz.lazizbek.school.entity.Subject;
import uz.lazizbek.school.entity.Teacher;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.TeacherDto;
import uz.lazizbek.school.payload.TeacherSubjectDto;
import uz.lazizbek.school.repository.AddressRepository;
import uz.lazizbek.school.repository.SubjectRepository;
import uz.lazizbek.school.repository.TeacherRepository;

import java.util.*;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse add(TeacherDto teacherDto){
        Address address = new Address();
        address.setCity(teacherDto.getCity());
        address.setStreet(teacherDto.getStreet());
        address.setHomeNumber(teacherDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);
        Teacher teacher = new Teacher();
        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setPatronymic(teacherDto.getPatronymic());
        teacher.setAddress(saveAddress);
        teacherRepository.save(teacher);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(Long id,TeacherDto teacherDto){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (!optionalTeacher.isPresent()){
            return new ApiResponse("Teacher not found",false);
        }

        Teacher teacher = optionalTeacher.get();
        Optional<Address> optionalAddress = addressRepository.findById(teacher.getAddress().getId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        Address address = optionalAddress.get();
        address.setCity(teacherDto.getCity());
        address.setStreet(teacherDto.getStreet());
        address.setHomeNumber(teacherDto.getHomeNumber());
        addressRepository.save(address);

        teacher.setPhoneNumber(teacherDto.getPhoneNumber());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setPatronymic(teacherDto.getPatronymic());
        teacherRepository.save(teacher);
        return new ApiResponse("Successfully edited", true);
    }

    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public Teacher getById(Long id){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElse(null);
    }

    public ApiResponse addSubject(TeacherSubjectDto teacherSubjectDto){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherSubjectDto.getTeacherId());
        if (!optionalTeacher.isPresent()){
            return new ApiResponse("Teacher not found",false);
        }
        Teacher teacher = optionalTeacher.get();
        Set<Subject> set = new HashSet<>();
        for (Long id: teacherSubjectDto.getSubjectId()) {
            Optional<Subject> optionalSubject  = subjectRepository.findById(id);
            if (!optionalSubject.isPresent()){
                return new ApiResponse("Subject not found",false);
            }
            set.add(optionalSubject.get());
        }
        teacher.setSubject(set);
        teacherRepository.save(teacher);
        return new ApiResponse("Successfully added", true);
    }
}
