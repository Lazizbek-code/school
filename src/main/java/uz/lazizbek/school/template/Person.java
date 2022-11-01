package uz.lazizbek.school.template;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.lazizbek.school.entity.Address;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Person extends AbstractEntity{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String patronymic;

    @OneToOne
    private Address address;

}
