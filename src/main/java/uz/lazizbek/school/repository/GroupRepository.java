package uz.lazizbek.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lazizbek.school.entity.Groups;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);
    List<Groups> findByActiveTrue();
}
