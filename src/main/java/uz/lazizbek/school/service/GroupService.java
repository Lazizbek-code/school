package uz.lazizbek.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.lazizbek.school.entity.Groups;
import uz.lazizbek.school.payload.ApiResponse;
import uz.lazizbek.school.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    public ApiResponse add(Groups group){
        if (groupRepository.existsByTitle(group.getTitle())){
            return new ApiResponse("Bunday guruh mavjud", false);
        }
        Groups newGroup = new Groups();
        newGroup.setTitle(group.getTitle());
        newGroup.setActive(true);
        groupRepository.save(newGroup);
        return new ApiResponse("Successfully added", true);
    }

    public List<Groups> getAll(){
        return groupRepository.findAll();
    }

    public List<Groups> getAllActiveGroup(){
        return groupRepository.findByActiveTrue();
    }

    public Groups getById(Long id){
        Optional<Groups> optionalGroup =groupRepository.findById(id);
        return optionalGroup.orElse(null);
    }

    public ApiResponse edit(Long id, Groups group){
        if (groupRepository.existsByTitleAndIdNot(group.getTitle(), id)){
            return new ApiResponse("Bunday guruh mavjud", false);
        }
        Optional<Groups> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found",false);
        }

        Groups editGroup =optionalGroup.get();
        editGroup.setTitle(group.getTitle());
        groupRepository.save(editGroup);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse changeActive(Long id){
        Optional<Groups> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()){
            return new ApiResponse("Group not found",false);
        }
        Groups editGroup = optionalGroup.get();
        editGroup.setActive(!editGroup.getActive());
        groupRepository.save(editGroup);
        return new ApiResponse("Successfully change active", true);
    }
}
