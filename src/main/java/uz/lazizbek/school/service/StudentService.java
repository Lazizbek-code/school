package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.Address;
import uz.lazizbek.school.entity.Groups;
import uz.lazizbek.school.entity.Student;
import uz.lazizbek.school.entity.Teacher;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.payload.StudentDto;
import uz.lazizbek.school.payload.TeacherDto;
import uz.lazizbek.school.repository.AddressRepository;
import uz.lazizbek.school.repository.GroupRepository;
import uz.lazizbek.school.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    GroupRepository groupRepository;

    public ApiResponse add(StudentDto studentDto){
        Optional<Groups> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found",false);
        }
        Groups group = optionalGroup.get();
        Address address = new Address();
        address.setCity(studentDto.getCity());
        address.setStreet(studentDto.getStreet());
        address.setHomeNumber(studentDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPatronymic(studentDto.getPatronymic());
        student.setAddress(saveAddress);
        student.setGroup(group);
        studentRepository.save(student);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse edit(Long id,StudentDto studentDto){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return new ApiResponse("Student not found",false);
        }
        Optional<Groups> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found",false);
        }
        Groups group = optionalGroup.get();
        Student student = optionalStudent.get();
        Optional<Address> optionalAddress = addressRepository.findById(student.getAddress().getId());
        if (!optionalAddress.isPresent()){
            return new ApiResponse("Address not found",false);
        }
        Address address = optionalAddress.get();
        address.setCity(studentDto.getCity());
        address.setStreet(studentDto.getStreet());
        address.setHomeNumber(studentDto.getHomeNumber());
        addressRepository.save(address);

        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPatronymic(studentDto.getPatronymic());
        student.setGroup(group);
        studentRepository.save(student);
        return new ApiResponse("Successfully edited", true);
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student getById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    public List<Student> getStudentsByGroup(Long groupId){
        return studentRepository.findByGroupId(groupId);
    }
}
