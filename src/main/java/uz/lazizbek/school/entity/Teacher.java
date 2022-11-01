package uz.lazizbek.school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.lazizbek.school.template.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends Person {

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany
    private Set<Subject> subject;

}
