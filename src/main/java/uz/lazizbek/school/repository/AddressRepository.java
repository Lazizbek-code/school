package uz.lazizbek.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.lazizbek.school.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
