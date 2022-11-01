package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.Subject;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse add(Subject subject){
        if (subjectRepository.existsByTitle(subject.getTitle())){
            return new ApiResponse("Bunday fan mavjud", false);
        }
        Subject newSubject = new Subject();
        newSubject.setTitle(subject.getTitle());
        newSubject.setActive(true);
        subjectRepository.save(newSubject);
        return new ApiResponse("Successfully added", true);
    }

    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    public List<Subject> getAllActiveSubject(){
        return subjectRepository.findByActiveTrue();
    }

    public Subject getById(Long id){
        Optional<Subject>optionalSubject =subjectRepository.findById(id);
        return optionalSubject.orElse(null);
    }

    public ApiResponse edit(Long id, Subject subject){
        if (subjectRepository.existsByTitleAndIdNot(subject.getTitle(), id)){
            return new ApiResponse("Bunday fan mavjud", false);
        }
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()){
            return new ApiResponse("Subject not found",false);
        }

        Subject editSubject =optionalSubject.get();
        editSubject.setTitle(subject.getTitle());
        subjectRepository.save(editSubject);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse changeActive(Long id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent()){
            return new ApiResponse("Subject not found",false);
        }
        Subject editSubject = optionalSubject.get();
        editSubject.setActive(!editSubject.getActive());
        subjectRepository.save(editSubject);
        return new ApiResponse("Successfully change active", true);
    }
}
